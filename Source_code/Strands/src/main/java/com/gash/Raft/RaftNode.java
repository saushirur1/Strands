package com.gash.Raft;

import com.gash.Common.RaftClient;
import com.google.protobuf.ByteString;
import grpc.*;
import io.grpc.*;
import io.grpc.stub.StreamObserver;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RaftNode {



    private static final Logger logger = Logger.getLogger(RaftNode.class.getName());

    static List<String> allFilesAvailableList ;
    private Server server;
    protected static TimerController tc;
    protected static ServerStateImpl serverStateImpl;
     public static long lastLScalled;

    public RaftNode(ServerStateImpl serverStateImpl){
        lastLScalled=System.currentTimeMillis();
        allFilesAvailableList = new ArrayList<String>();
        this.serverStateImpl=serverStateImpl;
        tc=new TimerController(100,250,serverStateImpl);
        tc.start();
    }

   // protected static String hostip;
    //protected static int hostport;
    //protected static int id = 1;


    public void start(int localport) throws IOException {
        /* The port on which the server should run */
        server = ServerBuilder.forPort(localport)
                .addService(new RaftServiceImpl())
                .addService(new DataTransferServiceImpl())
                .build()
                .start();
        logger.info("Server started, listening on " + localport);
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                // Use stderr here since the logger may have been reset by its JVM shutdown hook.
                System.err.println("*** shutting down gRPC server since JVM is shutting down");
                RaftNode.this.stop();
                System.err.println("*** server shut down");
            }
        });
    }

    private void stop() {
        if (server != null) {
            server.shutdown();
        }
    }


    public void blockUntilShutdown() throws InterruptedException {
        if (server != null) {
            server.awaitTermination();
        }
    }



    static public class DataTransferServiceImpl extends DataTransferServiceGrpc.DataTransferServiceImplBase
    {



        List<String> allFilesList;
        @Override
        public StreamObserver<Chat.FileUploadData> uploadFile(final StreamObserver<Chat.FileInfo> responseObserver) {

            return new StreamObserver<Chat.FileUploadData>()
            {
                RaftClient rc= new RaftClient("10.0.30.1",11000);


                StreamObserver<Chat.FileInfo> responseObserverclient = new StreamObserver<Chat.FileInfo>() {
                    @Override
                    public void onNext(Chat.FileInfo fileInfo) {
                       // System.out.println(fileInfo.getFileName());
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        System.out.println("error called"+throwable.getMessage());
                    }

                    @Override
                    public void onCompleted() {
                       // System.out.println("Completed");
                        try {
                            rc.shutdowncompletely();

                        }catch (InterruptedException e){

                        }
                    }
                };

                StreamObserver<Chat.FileUploadData> streamingtoNS = rc.getAsyncStub().uploadFile(responseObserverclient);

                @Override
                public void onNext(Chat.FileUploadData fileUploadData) {
                    if((serverStateImpl.getCurrentServerState()==ServerStateImpl.State.Leader) && !allFilesAvailableList.contains(fileUploadData.getFileName()))
                    {
                        allFilesAvailableList.add(fileUploadData.getFileName());
                    }
                    streamingtoNS.onNext(fileUploadData);
                  //  System.out.println(fileUploadData.getFileName());

                   /* try {
                        Thread.sleep(100);
                    }catch (Exception e){

                    }*/

                }

                @Override
                public void onError(Throwable throwable) {
                    System.out.println("error in upload"+throwable.getMessage());
                }

                @Override
                public void onCompleted() {
                    streamingtoNS.onCompleted();
                    responseObserver.onNext(Chat.FileInfo.newBuilder().setFileName("Got")
                            .build());
                    responseObserver.onCompleted();
                  //  System.out.println("sent completed");
                    try {
                        Thread.sleep(2500);
                    }catch (Exception e){

                    }

                }
            };
        }

        @Override
        public void requestFileInfo(Chat.FileInfo request, StreamObserver<Chat.FileLocationInfo> responseObserver)
        {
            RaftClient rc= new RaftClient("10.0.30.1",11000);
            System.out.println("Request File Location : "+request.getFileName());
            Datacentre.NsFileInfo nsFileInfo= Datacentre.NsFileInfo.newBuilder().setFileName(request.getFileName()).build();
            Datacentre.FileResult fileResult=rc.getDcblockingStub().fileExists(nsFileInfo);
            Chat.FileLocationInfo fileLocationInfo;
            try {
                rc.shutdowncompletely();
            }catch (InterruptedException e){
                System.out.println("Name Server channel connection failed");
            }
            if(fileResult.getFileFound()){

                List<Chat.ProxyInfo> proxies= new ArrayList<Chat.ProxyInfo>();

                // proxy server ip and port
                proxies.add(Chat.ProxyInfo.newBuilder().setIp("10.0.30.3").setPort("10000").build());
                proxies.add(Chat.ProxyInfo.newBuilder().setIp("10.0.30.3").setPort("10001").build());
                proxies.add(Chat.ProxyInfo.newBuilder().setIp("10.0.30.4").setPort("10000").build());
                proxies.add(Chat.ProxyInfo.newBuilder().setIp("10.0.30.1").setPort("10000").build());
                proxies.add(Chat.ProxyInfo.newBuilder().setIp("10.0.30.2").setPort("10000").build());



                fileLocationInfo = Chat.FileLocationInfo.newBuilder().addAllLstProxy(proxies).setIsFileFound(true).setMaxChunks(fileResult.getMaxChunks()).setFileName(request.getFileName()).build();

            }
            else
            {

                FileRequestSenderCheck fileRequestSenderCheck=new FileRequestSenderCheck(serverStateImpl.allNetworkClientNodes.size()-1);
                //call 16 nodes
                for (RaftNodeClient r : serverStateImpl.allNetworkClientNodes) {
                    FileRequestSender fileRequestSender=new FileRequestSender(r,request,fileRequestSenderCheck);
                    fileRequestSender.start();
                }
              //  while (! fileRequestSenderCheck.isFileFound() && fileRequestSenderCheck.getRpcCount()<fileRequestSenderCheck.getNoOfNodes());

                try {
                    Thread.sleep(1000);
                }catch (Exception e){

                }
                if(fileRequestSenderCheck.isFileFound()){
                    fileLocationInfo=fileRequestSenderCheck.getFileLocationInfo();
                }
                else {
                    // serverStateImpl.allNetworkClientNodes.contains("");
                    fileLocationInfo = Chat.FileLocationInfo.newBuilder().setIsFileFound(false).build();
                }

            }
            responseObserver.onNext(fileLocationInfo);
            responseObserver.onCompleted();
        }

        @Override
        public void getFileLocation(grpc.Chat.FileInfo request,
                                    io.grpc.stub.StreamObserver<grpc.Chat.FileLocationInfo> responseObserver) {
            if(serverStateImpl.getCurrentServerState()==ServerStateImpl.State.Leader) {
            RaftClient rc= new RaftClient("10.0.30.1",11000);
            System.out.println("Get File Location from my node : "+request.getFileName());
            Datacentre.NsFileInfo nsFileInfo= Datacentre.NsFileInfo.newBuilder().setFileName(request.getFileName()).build();
            Datacentre.FileResult fileResult=rc.getDcblockingStub().fileExists(nsFileInfo);
            Chat.FileLocationInfo fileLocationInfo;
            try {
                rc.shutdowncompletely();
            }catch (InterruptedException e){
                System.out.println("Name Server channel connection failed");
            }
            if(fileResult.getFileFound()){

                List<Chat.ProxyInfo> proxies= new ArrayList<Chat.ProxyInfo>();

                // proxy server ip and port
                proxies.add(Chat.ProxyInfo.newBuilder().setIp("10.0.30.3").setPort("10000").build());
                proxies.add(Chat.ProxyInfo.newBuilder().setIp("10.0.30.3").setPort("10001").build());
                proxies.add(Chat.ProxyInfo.newBuilder().setIp("10.0.30.4").setPort("10000").build());
                proxies.add(Chat.ProxyInfo.newBuilder().setIp("10.0.30.2").setPort("10000").build());
                proxies.add(Chat.ProxyInfo.newBuilder().setIp("10.0.30.1").setPort("10000").build());



                fileLocationInfo = Chat.FileLocationInfo.newBuilder().addAllLstProxy(proxies).setIsFileFound(true).setMaxChunks(fileResult.getMaxChunks()).setFileName(request.getFileName()).build();

            }
            else{
                fileLocationInfo = Chat.FileLocationInfo.newBuilder().setIsFileFound(false).build();
            }

                responseObserver.onNext(fileLocationInfo);
            }
            responseObserver.onCompleted();
        }
        @Override
        public void listFiles(grpc.Chat.RequestFileList request,
                              io.grpc.stub.StreamObserver<grpc.Chat.FileList> responseObserver)
        {

            long currentTime=System.currentTimeMillis();
            if(currentTime-lastLScalled >1000) {

                lastLScalled = System.currentTimeMillis();
                if (request.getIsClient() || serverStateImpl.getCurrentServerState() == ServerStateImpl.State.Leader) {
                    allFilesList = new ArrayList<String>();
                    System.out.println(request.getIsClient() + "sending all file names to client");
                    RaftClient rc = new RaftClient("10.0.30.1", 11000);
                    grpc.Chat.FileList fileList = rc.getBlockingStub().listFiles(request);
                    allFilesList.addAll(fileList.getLstFileNamesList());
                    try {
                        rc.shutdowncompletely();
                    } catch (InterruptedException e) {
                        System.out.println("Name Server channel connection failed");
                    }
                    //grpc.Chat.FileList fileList=grpc.Chat.FileList.newBuilder().addAllLstFileNames(allFilesAvailableList).build();
                    if (request.getIsClient()) {

                        System.out.println("client should receive all Files catalogue");

                        grpc.Chat.RequestFileList requestFileList = Chat.RequestFileList.newBuilder().setIsClient(false).build();
                        ListFileSenderCheck listFileSenderCheck = new ListFileSenderCheck(serverStateImpl.allNetworkClientNodes.size() - 1);
                        //call 16 nodes
                        for (RaftNodeClient r : serverStateImpl.allNetworkClientNodes) {
                            ListFileSender listFileSender = new ListFileSender(r, requestFileList, listFileSenderCheck);
                            listFileSender.start();
                        }
                        try {
                            Thread.sleep(750);
                        } catch (Exception e) {

                        }
                        allFilesList.addAll(listFileSenderCheck.getOtherNodeFiles());

                    }

                }
            }
                grpc.Chat.FileList fileListforclient=grpc.Chat.FileList.newBuilder().addAllLstFileNames(allFilesList).build();
                responseObserver.onNext(fileListforclient);
            responseObserver.onCompleted();


        }
        public void downloadChunk(grpc.Chat.ChunkInfo request,
                                  io.grpc.stub.StreamObserver<grpc.Chat.FileMetaData> responseObserver) {
            List<String> dcIps= new ArrayList<String>();
            String fileName = request.getFileName();
            long chunkId = request.getChunkId();			// Starts from 0 - N
            long startSeqNum = request.getStartSeqNum();
            RaftClient rc= new RaftClient("10.0.30.1",11000);
            Datacentre.NsFileInfo nsFileInfo=Datacentre.NsFileInfo.newBuilder().setFileName(fileName).build();
            Datacentre.FileChunkInfo fileChunkInfo=rc.getDcblockingStub().getChunkDetails(nsFileInfo);

            dcIps.addAll(fileChunkInfo.getChunksList());
            System.out.println("mapping "+dcIps.toString());
            char dcipIndex=dcIps.get((int) chunkId).charAt(0);
            String dcipIndexstr=""+dcipIndex;
            char dcipIndex1=dcIps.get((int) chunkId).charAt(1);
            String dcipIndexstr1=""+dcipIndex1;
            //System.out.println("inside download "+ dcipIndexstr);

            String dcIp=serverStateImpl.datacenterIps[Integer.parseInt(dcipIndexstr)-1];
            //System.out.println("inside download "+ dcIp);
            //System.out.println("inside download port "+(serverStateImpl.dataCenterPorts[Integer.parseInt(dcipIndexstr)-1]));
            RaftClient dc= new RaftClient(dcIp,serverStateImpl.dataCenterPorts[Integer.parseInt(dcipIndexstr)-1]);

            Iterator<Chat.FileMetaData> features;
            try {
                features = dc.getBlockingStub().downloadChunk(request);
                for (int i = 1; features.hasNext(); i++)
                {
                    Chat.FileMetaData feature = features.next();
                    responseObserver.onNext(feature);

                }
            } catch (StatusRuntimeException e) {
                System.out.println("No RPC operation");
            }

            responseObserver.onCompleted();
            try {
                rc.shutdowncompletely();
                dc.shutdowncompletely();
            }catch (InterruptedException e){
                e.printStackTrace();
            }

        }

    }

    static class RaftServiceImpl extends RaftServiceGrpc.RaftServiceImplBase {
        @Override
        public void startcontending(Raft.Candidateinfo candidateinfo,
                                    StreamObserver<Raft.Vote> voteStreamObserver) {

            long  term = candidateinfo.getTerm();
            long  id = candidateinfo.getId();
            long  lastlogindex =candidateinfo.getLastlogindex();
            long lastlogterm= candidateinfo.getLastlogterm();
//Whats LastLog Term???

            if(term>serverStateImpl.serverState.getCurrentTerm()){
                serverStateImpl.setCurrentServerState(ServerStateImpl.State.Follower);
            }
                if (!serverStateImpl.serverState.voteCastTerm.contains(term) && (term > serverStateImpl.serverState.getCurrentTerm()
                        || (term == serverStateImpl.serverState.getCurrentTerm()
                        && lastlogindex > serverStateImpl.serverState.getLastLogIndex()
                        && lastlogterm >= serverStateImpl.serverState.getLastLogTerm()))) {
                    Raft.Vote vote = Raft.Vote.newBuilder()
                            .setTerm(serverStateImpl.serverState.getCurrentTerm())
                            .setVotegranted(true)
                            .build();
                    serverStateImpl.serverState.voteCastTerm.add(term);
                    voteStreamObserver.onNext(vote);
                    voteStreamObserver.onCompleted();

                }
            else {
                    if (!serverStateImpl.serverState.voteCastTerm.contains(term)){
                        serverStateImpl.serverState.voteCastTerm.add(term);
                    }
                Raft.Vote vote=Raft.Vote.newBuilder()
                        .setTerm(serverStateImpl.serverState.getCurrentTerm())
                        .setVotegranted(false)
                        .build();
                voteStreamObserver.onNext(vote);
                voteStreamObserver.onCompleted();

            }


        }

        @Override
        public void appendentries(Raft.UpdateEntry updateEntry,
                                  StreamObserver<Raft.Followerupdate> followerupdateStreamObserver) {
            System.out.println("** Got Leader HB ** ");
            Raft.Followerupdate followerupdate;
            if(serverStateImpl.getCurrentServerState()== ServerStateImpl.State.Leader){
            followerupdate=Raft.Followerupdate.newBuilder()
                                .setId(serverStateImpl.serverState.getId())
                                .setSuccess(false).build();
                followerupdateStreamObserver.onNext(followerupdate);
                followerupdateStreamObserver.onCompleted();
                TimerController.changeState=true;
                //LeaderExecution.LeaderState=false;
                serverStateImpl.setCurrentServerState(ServerStateImpl.State.Follower);
            }
            else {
                tc.setRecentHeartbeatTime(System.currentTimeMillis());
                long leaderterm = updateEntry.getLeaderterm();
                long id = updateEntry.getId();
                String leaderIp=updateEntry.getLeaderIp();
                /*long prevLogIndex = updateEntry.getPrevLogIndex();
                long prevLogTerm = updateEntry.getPrevLogTerm();
                List<Raft.TermEntry> entries = updateEntry.getEntriesList();
                long leaderCommitIndex = updateEntry.getLeaderCommitIndex();
*/
                if (leaderterm != serverStateImpl.serverState.getCurrentTerm()) {
                    serverStateImpl.serverState.setCurrentTerm(leaderterm);
                }
                serverStateImpl.serverState.setLeaderId(id);
                serverStateImpl.serverState.setLeaderIp(leaderIp);
                //tc.setRecentHeartbeatTime(System.currentTimeMillis());
                followerupdate=Raft.Followerupdate.newBuilder()
                        .setId(serverStateImpl.serverState.getId())
                        .setSuccess(true).build();
                followerupdateStreamObserver.onNext(followerupdate);
                followerupdateStreamObserver.onCompleted();
                tc.setRecentHeartbeatTime(System.currentTimeMillis());

            }
        }

    }
}
