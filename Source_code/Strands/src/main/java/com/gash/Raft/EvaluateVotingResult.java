package com.gash.Raft;

public class EvaluateVotingResult extends Thread{

    private Boolean leaderGranted;
    private ContederCheck contederCheck;
    private ServerStateImpl serverStateImpl;

    EvaluateVotingResult(ContederCheck contederCheck,ServerStateImpl serverStateImpl){
        this. contederCheck=contederCheck;
        this.serverStateImpl= serverStateImpl;

        leaderGranted=false;
    }
    @Override
    public void run() {
        while(contederCheck.getVoteCasted()< serverStateImpl.raftNodeClients.size() && !leaderGranted) {
            //System.out.print("rtvotes = "+contederCheck.getVoteCasted()+"  ");
           /* if(contederCheck.getVoteCasted()==1){
                System.out.println("Here 21 ");

            }
            if(contederCheck.getVoteCasted()==2){
                System.out.println("Here 22 ");

            }
*/
           /* if(contederCheck.getVoteCasted()==3){
                System.out.println("Here 23 ");

            }
*/
            if (! contederCheck.isNotEligible() && contederCheck.getNumOfVotes() > 1) {
                //make this leader
                System.out.println("I am Leader Now ");
                serverStateImpl.setCurrentServerState(ServerStateImpl.State.Leader);
                LeaderExecution leaderExecution = new LeaderExecution(1, 50, serverStateImpl);
                leaderExecution.start();
                leaderGranted=true;
            }

        }
       // System.out.println("Here 1 ");
        if(!leaderGranted){
            serverStateImpl.setCurrentServerState(ServerStateImpl.State.Follower);
            System.out.println("Contender Failed Start Again "+ serverStateImpl.serverState.getCurrentTerm());

        }

    }
}
