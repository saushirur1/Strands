package com.gash.Nameserver;

import com.gash.Common.RaftClient;
import com.google.protobuf.ByteString;
import com.sun.prism.shader.Solid_TextureYV12_AlphaTest_Loader;
import grpc.Chat;
import grpc.DataCenterServiceGrpc;
import grpc.DataTransferServiceGrpc;
import grpc.Datacentre;
import io.grpc.Server;
import io.grpc.netty.shaded.io.grpc.netty.NettyServerBuilder;
import io.grpc.stub.ClientCalls;
import io.grpc.stub.StreamObserver;

import java.io.*;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

public class new_NameServer
{
    private Server my_server;
    public static class Config_info1
    {
        String ips;
        String ports;
    }
    static List<Config_info1> clusterIps1;
    static List<String> clusterIpsInStr1;
   //static ConcurrentHashMap<String,List<String>> map = new ConcurrentHashMap<>();
    static ConcurrentHashMap<String,Map<Integer,String>> dataMap = new ConcurrentHashMap<>();

    public new_NameServer()
    {
        clusterIpsInStr1 = new ArrayList<String>();
        clusterIps1= new ArrayList<Config_info1>();
        Properties prop = new Properties();
        final int Servers = 3;
        try {
            String fileName = "conf/data_cente.conf";
            InputStream is = new FileInputStream(fileName);
            prop.load(is);
            Config_info1 ips;
            for (int i = 1; i < Servers + 1; i++)
            {
                ips = new Config_info1();
                ips.ips = prop.getProperty("server" + i + ".ip");
                ips.ports = prop.getProperty("server" + i + ".port");
                clusterIps1.add(ips);
                clusterIpsInStr1.add(ips.ips + "." + ips.ports);
            }
        } catch (Exception e)
        {
        }
        for(Config_info1 f:clusterIps1)
        {
            System.out.println("ips" + f.ips);
            System.out.println("ports" + f.ports);
        }
    }
    private void start()throws IOException
    {
        int port = 11000;
   /* my_server = ServerBuilder.forPort(port).addService(new DataTransferServiceImpl())
            .build().start();
*/
        my_server = NettyServerBuilder.forPort(port)
                .addService(new DataTransferServiceImpl()).addService(new DataCenterService()).
                        build()
                .start();

        System.out.println("Server Started on port "+port);
    }

    private void stop()
    {
        if(my_server!=null)
        {
            my_server.shutdown();
        }
    }

    private void blockUntilshutdown() throws InterruptedException
    {
        if(my_server!=null)
        {
            my_server.awaitTermination();
        }
    }

    public static void main(String[] args) throws IOException,InterruptedException
    {
        new_NameServer p1 = new new_NameServer();
        p1.start();
        p1.blockUntilshutdown();
        System.out.println("Proxy Server Started");
    }

    public static void mapping(String file_name_tomap,long chunk_id_info,int Ids)
    {
        int chk=(int)chunk_id_info-1;
        if(dataMap.containsKey(file_name_tomap))
        {
            Map<Integer,String> chunkMap =dataMap.get(file_name_tomap);

            if(chunkMap.containsKey(chk)){
                String dcIndex=chunkMap.get(chk);
                String to_add=dcIndex+Ids;
                chunkMap.put((int) chunk_id_info-1,to_add);
            }
            else{

                chunkMap.put(chk,""+Ids);
            }
            dataMap.put(file_name_tomap,chunkMap);

        }
        else
        {
            Map<Integer,String> chunkMapnew=new HashMap<>();
            chunkMapnew.put(chk,""+Ids);
            dataMap.put(file_name_tomap, chunkMapnew);
        }
    }

    public static List<Integer> getDatacenterIps()
    {
        Random rand = new Random();
        int count =0;
        int max = 3;
        int min = 1;
        List<Integer> random_select = new ArrayList<>();
        while(count < 2)
        {
            int random = rand.nextInt(max - min + 1) + min;
            if(!random_select.contains(random))
            {
                random_select.add(random);
                System.out.println(random);
                count++;
            }
        }
        return random_select;
    }

    static public class DataTransferServiceImpl extends DataTransferServiceGrpc.DataTransferServiceImplBase
    {


        ArrayList<String> allFilesAvailableList;

        DataTransferServiceImpl()
        {
            allFilesAvailableList= new ArrayList<String>();
        }

        @Override
        public StreamObserver<Chat.FileUploadData> uploadFile(final StreamObserver<Chat.FileInfo> responseObserver)
        {
            return new StreamObserver<Chat.FileUploadData>()
            {
                List<Integer> datacenterIds=getDatacenterIps();
                long chunk_id_info;
                String file_name_tomap = "";
                int count=0;

                RaftClient rc= new RaftClient(clusterIps1.get(datacenterIds.get(0)-1).ips,Integer.parseInt(clusterIps1.get(datacenterIds.get(0)-1).ports));
                RaftClient rc1= new RaftClient(clusterIps1.get(datacenterIds.get(1)-1).ips,Integer.parseInt(clusterIps1.get(datacenterIds.get(1)-1).ports));

                StreamObserver<Chat.FileInfo> responseObserverclient = new StreamObserver<Chat.FileInfo>() {
                    @Override
                    public void onNext(Chat.FileInfo fileInfo)
                    {
                        System.out.println(fileInfo.getFileName());
                    }

                    @Override
                    public void onError(Throwable throwable)
                    {
                        System.out.println("error called for RC1"+throwable.getMessage());
                    }

                    @Override
                    public void onCompleted()
                    {
                        System.out.println("Completed for RC1");
                        mapping(file_name_tomap,chunk_id_info,datacenterIds.get(0));
                        nameserver_client cl = new nameserver_client("10.0.30.3",11200);
                        cl.updatebackupNS(file_name_tomap+"+"+chunk_id_info, String.valueOf(datacenterIds.get(0)));
                        try {
                            cl.shutdowncompletely();
                            rc.shutdowncompletely();

                        }catch (InterruptedException e){

                        }
                    }
                };

                StreamObserver<Chat.FileInfo> responseObserverclient1 = new StreamObserver<Chat.FileInfo>() {
                    @Override
                    public void onNext(Chat.FileInfo fileInfo) {
                        System.out.println(fileInfo.getFileName());
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        System.out.println("error called for RC2 "+throwable.getMessage());
                    }

                    @Override
                    public void onCompleted() {
                        System.out.println("Completed for RC2");
                        mapping(file_name_tomap,chunk_id_info,datacenterIds.get(1));
                        nameserver_client cl = new nameserver_client("10.0.30.3",11200);
                        cl.updatebackupNS(file_name_tomap+"+"+chunk_id_info, String.valueOf(datacenterIds.get(1)));
                        /*for (Map.Entry<String,Map<Integer,String>> entry : dataMap.entrySet()) {
                            String key = entry.getKey().toString();
                            System.out.print("KEY RC2: " + key + ": ");
                            List<String> print_list = new ArrayList<>();
                            print_list = entry.getValue();
                            for(String s:print_list)
                            {
                                System.out.print("Values RC2:" +  s );
                            }
                            System.out.println();

                        }
                        System.out.println("size of map :"+ map.size());*/

                        System.out.println("size of map :"+ dataMap.size());
                        try {
                            cl.shutdowncompletely();
                            rc1.shutdowncompletely();

                        }catch (InterruptedException e){

                        }
                    }
                };

                StreamObserver<Chat.FileUploadData> streamingtoDc = rc.getAsyncStub().uploadFile(responseObserverclient);
                StreamObserver<Chat.FileUploadData> streamingtoDc1 = rc1.getAsyncStub().uploadFile(responseObserverclient1);

                @Override
                public void onNext(Chat.FileUploadData fileUploadData)
                {
                    chunk_id_info = fileUploadData.getChunkId();
                    file_name_tomap = fileUploadData.getFileName();
                    streamingtoDc.onNext(fileUploadData);
                    streamingtoDc1.onNext(fileUploadData);
                    System.out.println(fileUploadData.getFileName());

                }

                @Override
                public void onError(Throwable throwable)
                {
                    System.out.println("error");
                }

                @Override
                public void onCompleted()
                {
                    streamingtoDc.onCompleted();
                    streamingtoDc1.onCompleted();
                    responseObserver.onNext(Chat.FileInfo.newBuilder().setFileName("Got")
                            .build());
                    responseObserver.onCompleted();
                    System.out.println("sent completed");
                    try {
                        Thread.sleep(1500);
                    }catch (Exception e){

                    }

                }
            };
        }
        @Override
        public void listFiles(grpc.Chat.RequestFileList request,
                              io.grpc.stub.StreamObserver<grpc.Chat.FileList> responseObserver)
        {
            System.out.println(request.getIsClient()+ "sending all file names to client");
            List<String> list_of_files = new ArrayList<>();
            for(Map.Entry<String,Map<Integer,String>> entry : dataMap.entrySet())
            {
                list_of_files.add(entry.getKey());
            }
            grpc.Chat.FileList v=grpc.Chat.FileList.newBuilder().addAllLstFileNames(list_of_files).build();
            if(request.getIsClient())
            {
                responseObserver.onNext(v);
                responseObserver.onCompleted();
            }
            else
            {
                responseObserver.onNext(v);
                responseObserver.onCompleted();
            }

        }


    }
    static public class DataCenterService extends DataCenterServiceGrpc.DataCenterServiceImplBase
    {
        @Override
        public void getChunkDetails(Datacentre.NsFileInfo request, StreamObserver<Datacentre.FileChunkInfo> responseObserver) {
            List<String> file_namevalues_map = new ArrayList<>();
            Map <Integer,String> chukmap;
            Boolean to_set_bool = false;
            if(dataMap.containsKey(request.getFileName()))
            {
                to_set_bool = true;
                chukmap=dataMap.get(request.getFileName());
                for(int i=0;i<chukmap.size();i++){

                    file_namevalues_map.add(chukmap.get(i));
                }

            }
            System.out.println(file_namevalues_map.toString());
            Datacentre.FileChunkInfo f=  Datacentre.FileChunkInfo.newBuilder().addAllChunks(file_namevalues_map).setMaxChunks(file_namevalues_map.size()).setIsFileFound(to_set_bool).build();
            responseObserver.onNext(f);
            responseObserver.onCompleted();
        }

        @Override
        public void fileExists(grpc.Datacentre.NsFileInfo request,
                               io.grpc.stub.StreamObserver<grpc.Datacentre.FileResult> responseObserver) {
            Boolean to_set_bool = false;
            long max_chunks = 0;
            if(dataMap.containsKey(request.getFileName()))
            {
                to_set_bool = true;
                max_chunks = dataMap.get(request.getFileName()).size();
            }
            Datacentre.FileResult f1 =    Datacentre.FileResult.newBuilder().setFileFound(to_set_bool).setMaxChunks(max_chunks).build();
            responseObserver.onNext(f1);
            responseObserver.onCompleted();
        }
    }

   /* public static void main(String[] args) {
        NameServer ns=new NameServer();
        NameServer.mapping("ptp",1,1);
        NameServer.mapping("ptp",1,1);
        NameServer.mapping("ptp",2,1);
        NameServer.mapping("ptp",2,1);
        NameServer.mapping("ptp",3,1);
        NameServer.mapping("ptp",3,1);

        List<String> file_namevalues_map = new ArrayList<>();
        Map <Integer,String> chukmap;
        Boolean to_set_bool = false;
        if(dataMap.containsKey("ptp"))
        {

            chukmap=dataMap.get("ptp");
            for(int i=0;i<chukmap.size();i++){

                file_namevalues_map.add(chukmap.get(i));
            }

        }
        System.out.println(file_namevalues_map.toString());

    }
*/
}
