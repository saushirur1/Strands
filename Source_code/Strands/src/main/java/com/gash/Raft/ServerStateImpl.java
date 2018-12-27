package com.gash.Raft;

import com.gash.Raft.RaftNodeClient;
import grpc.Raft;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;

public class ServerStateImpl {


    protected static  ServerState serverState;
    protected static RaftNode raftNode;
    private NetworkConfig networkConfig;
    protected  String serverIpAddr;
    protected  int serverlocalport;
    protected  String serverIpAddrWithPort;
    private static final Logger logger = Logger.getLogger(ServerStateImpl.class.getName());
    protected List<RaftNodeClient> raftNodeClients;
    protected List<RaftNodeClient> allNetworkClientNodes;
    //protected List<RaftNodeClient> allDCNodes;
    private int[] clusterPorts;
    private String[] clusterIps;
    private int[] networkPorts;
    private String[] networkIps;
    public int[] dataCenterPorts;
    public String[] datacenterIps;

    public enum State {
       Leader, Follower,Contender
   }
int idarr[]={1001,2001,3001,4001,5001};

    protected State currentServerState;
    public State getCurrentServerState() {
        return currentServerState;
    }

    public void setCurrentServerState(State currentServerState) {
        this.currentServerState = currentServerState;
    }


    ServerStateImpl(int last_ip_val,int port){
        if(last_ip_val<=0 ||last_ip_val>=idarr.length){
            last_ip_val=1;
        }
        serverlocalport=port;
        try {
            serverIpAddr = InetAddress.getLocalHost().getHostAddress();
            logger.info("My localHost :: "+serverIpAddr);
            serverIpAddrWithPort=serverIpAddr+"."+serverlocalport;
        }catch (UnknownHostException e){
            logger.info("ERR :: Unknown Host Exception");
        }
         serverState=new ServerState();
        raftNodeClients=new ArrayList<RaftNodeClient>();
        allNetworkClientNodes=new ArrayList<RaftNodeClient>();
        //allDCNodes=new ArrayList<RaftNodeClient>();
        serverState.setId(new Random().nextInt(50)+idarr[last_ip_val-1]);
        serverState.setCurrentTerm(0);
        currentServerState=State.Follower;
        startServerNode(this);
        establishClientChannels();
        establishNetworkChannels();
        establishDataCenterChannels();
        System.out.println("");
        try {
            raftNode.blockUntilShutdown();
        }catch (InterruptedException e){
            logger.info("ERR :: Server shutdown ");
        }
    }
   private void findportfromConf(){
        /*try {
            serverIpAddr=InetAddress.getLocalHost().getHostAddress();

        } catch(UnknownHostException e) {
            e.printStackTrace();
            return;
        }*/
/*
       for(String s:networkConfig.clusterIpsInStr){
           if(s.contains(serverIpAddrWithPort)){
               serverlocalport=Integer.parseInt(s.substring(s.lastIndexOf('.')+1));
               break;
           }
       }*/

       if(networkConfig.clusterIpsInStr.contains(serverIpAddr+"."+serverlocalport)){
           networkConfig.clusterIpsInStr.remove(serverIpAddr+"."+serverlocalport);
           logger.info("my system Ip removed fr4om the cluster ip list");
       }
       else{
           logger.info(" ERR: port Or IP miss match for The cluster");
           System.exit(-1);
       }
    }

   private void startServerNode(ServerStateImpl serverStateImpl){
        networkConfig=new NetworkConfig();

        networkConfig.getMyclusterIps();
        networkConfig.getAllNetworkIps();
        networkConfig.getAllDataCenterIps();
        findportfromConf();
        try {
            raftNode=new RaftNode(serverStateImpl);

            if(serverlocalport!=0) {
                raftNode.start(serverlocalport);
            }
            else logger.info("No Port found to start Server. Check Inet Function");
        }catch (IOException e){
            e.printStackTrace();
            return;
        }
        //start();

    }

    private void establishClientChannels(){
        logger.info("Establishing Channel ");
        int j=0;
        clusterIps=new String[networkConfig.clusterIpsInStr.size()];
        clusterPorts=new int[networkConfig.clusterIpsInStr.size()];
        for(String s :networkConfig.clusterIpsInStr){
            clusterIps[j]=s.substring(0,s.lastIndexOf('.'));
            clusterPorts[j]=Integer.parseInt(s.substring(s.lastIndexOf('.')+1));
                RaftNodeClient raftNodeClient=new RaftNodeClient(clusterIps[j],clusterPorts[j]);
                raftNodeClients.add(raftNodeClient);
                j++;

        }

    }
    private void establishNetworkChannels(){
        logger.info("Establishing all N/w Channels ");
        int j=0;
        networkIps=new String[networkConfig.networkIpsInStr.size()];
        networkPorts=new int[networkConfig.networkIpsInStr.size()];
        for(String s :networkConfig.networkIpsInStr){
            networkIps[j]=s.substring(0,s.lastIndexOf('.'));
            networkPorts[j]=Integer.parseInt(s.substring(s.lastIndexOf('.')+1));
            RaftNodeClient raftNodeClient=new RaftNodeClient(networkIps[j],networkPorts[j]);
            allNetworkClientNodes.add(raftNodeClient);
            j++;

        }

    }
    private void establishDataCenterChannels(){
        logger.info("Establishing all N/w Channels ");
        int j=0;
        datacenterIps=new String[networkConfig.datacentreIpsInStr.size()];
        dataCenterPorts=new int[networkConfig.datacentreIpsInStr.size()];
        for(String s :networkConfig.datacentreIpsInStr){
            datacenterIps[j]=s.substring(0,s.lastIndexOf('.'));
            dataCenterPorts[j]=Integer.parseInt(s.substring(s.lastIndexOf('.')+1));
            //RaftNodeClient raftNodeClient=new RaftNodeClient(datacenterIps[j],dataCenterPorts[j]);
            //allDCNodes.add(raftNodeClient);
            j++;

        }

    }
//should be called for each timeout
    public void updateServer(){

    }

    public void sendHBFromLeader(){
        /*boolean update=true;
        int updateCount=0;
        */
        Raft.UpdateEntry updateEntry= Raft.UpdateEntry.newBuilder()
                .setId(serverState.getId())
                .setLeaderterm(serverState.getCurrentTerm())
                .setLeaderIp(serverIpAddrWithPort)
                .build();
//        Raft.Followerupdate  followerupdate;
//        while(update) {
//
//
            for (RaftNodeClient r : raftNodeClients) {
                LeaderUpdater leaderUpdater=new LeaderUpdater(r,updateEntry);
                leaderUpdater.start();
            }
                /*try {
                    followerupdate = r.getBlockingStub().appendentries(updateEntry);

                    if(followerupdate.getSuccess()){
                        updateCount++;
                    }
                } catch (StatusRuntimeException e) {
                    System.out.println("A Server is Down");
                }

            }
*/
            /*if(updateCount>2){
                update=false;
            }
        }*/
    }

   public void startCandidateElection(){
        //int numOfVotes=0;
        //boolean notEligible=false;

       serverState.setCurrentTerm(serverState.getCurrentTerm()+1);
       serverState.voteCastTerm.add(serverState.getCurrentTerm());

       Raft.Candidateinfo candidateinfo=Raft.Candidateinfo.newBuilder()
                .setId(serverState.getId())
                .setTerm(serverState.getCurrentTerm())
                .setLastlogindex(serverState.getLastLogIndex())
                .setLastlogterm(serverState.getLastLogTerm())
                .build();
        //Raft.Vote vote;
       ContederCheck contederCheck= new ContederCheck();
      // Contender contender=new Contender(this,contederCheck) ;

       // Contender.EvaluateVotingResult evaluateVotingResult=new
        for (RaftNodeClient r:raftNodeClients) {
            Contender contenders=new Contender(this,r,candidateinfo,contederCheck);
            contenders.start();

        }
       EvaluateVotingResult evaluateVotingResult=new EvaluateVotingResult(contederCheck,this);
       evaluateVotingResult.start();


    }
    public static void main(String[] args) {
        ServerStateImpl sce=new ServerStateImpl(1,10000);

    }

}
