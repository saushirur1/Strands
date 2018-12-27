package com.gash.Raft;

import grpc.Raft;
import io.grpc.StatusRuntimeException;

public class Contender extends Thread {

    private ServerStateImpl serverStateImpl;
    private RaftNodeClient raftNodeClient;
    private grpc.Raft.Candidateinfo candidateinfo;
    private ContederCheck  contederCheck;
    public Contender(ServerStateImpl serverStateImpl, RaftNodeClient raftNodeClient,
                     grpc.Raft.Candidateinfo candidateinfo,ContederCheck  contederCheck){
        this.raftNodeClient=raftNodeClient;
        this.serverStateImpl=serverStateImpl;
        this.candidateinfo=candidateinfo;
        this.contederCheck=contederCheck;
    }
   /* public Contender(ServerStateImpl serverStateImpl,ContederCheck  contederCheck){
        this.serverStateImpl=serverStateImpl;
        this.contederCheck=contederCheck;
    }
   */

   @Override
    public void run() {
        Raft.Vote vote;
        long nowthetime = System.currentTimeMillis();
        try {
            vote = raftNodeClient.getBlockingStub().startcontending(candidateinfo);
            if (vote.getVotegranted()) {
                contederCheck.setNumOfVotes(contederCheck.getNumOfVotes()+1);// numOfVotes++;
            } else if (vote.getTerm() >= serverStateImpl.serverState.getCurrentTerm()) {
                contederCheck.setNotEligible(true);
               // ServerStateImpl.notEligible = true;
            }
        }catch (StatusRuntimeException e)
        {
            System.out.print("A Server is Down ==> " +raftNodeClient.getHostIp()+"."+raftNodeClient.getHostPort() );
        }
        finally {
           // ServerStateImpl.voteCasted++;
            contederCheck.setVoteCasted(contederCheck.getVoteCasted()+1);
            long timeTaken=System.currentTimeMillis()-nowthetime;
            System.out.println(serverStateImpl.raftNodeClients.size()+" and Delayed by "+timeTaken + " vote count = " + contederCheck.getVoteCasted());
        }
    }
    /*public  class EvaluateVotingResult extends Thread {
        private Boolean leaderGranted;
        EvaluateVotingResult(){
            leaderGranted=false;
        }
        @Override
        public void run() {
            while(contederCheck.getVoteCasted()< serverStateImpl.raftNodeClients.size() && !leaderGranted) {
             //System.out.print("rtvotes = "+contederCheck.getVoteCasted()+"  ");
                if(contederCheck.getVoteCasted()==1){
                    System.out.println("Here 21 ");

                }
                if(contederCheck.getVoteCasted()==2){
                    System.out.println("Here 22 ");

                }

                if(contederCheck.getVoteCasted()==3){
                    System.out.println("Here 23 ");

                }

                if (! contederCheck.isNotEligible() && contederCheck.getNumOfVotes() >= 2) {
                    //make this leader
                    serverStateImpl.setCurrentServerState(ServerStateImpl.State.Leader);
                    LeaderExecution leaderExecution = new LeaderExecution(1, 50, serverStateImpl);
                    leaderExecution.start();
                    leaderGranted=true;
                }

            }
            System.out.println("Here 1 ");
            if(!leaderGranted){
                serverStateImpl.setCurrentServerState(ServerStateImpl.State.Follower);
                System.out.println("Here ");

            }

        }
    }
    */

}

