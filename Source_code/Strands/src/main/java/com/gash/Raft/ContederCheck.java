package com.gash.Raft;

public class ContederCheck {
    public int getNumOfVotes() {
        return numOfVotes;
    }

    public void setNumOfVotes(int numOfVotes) {
        this.numOfVotes = numOfVotes;
    }

    public boolean isNotEligible() {
        return notEligible;
    }

    public void setNotEligible(boolean notEligible) {
        this.notEligible = notEligible;
    }

    public int getVoteCasted() {
        return voteCasted;
    }

    public void setVoteCasted(int voteCasted) {
        this.voteCasted = voteCasted;
    }

    private volatile int numOfVotes=0;
    private volatile boolean notEligible=false;
    private volatile int voteCasted=0;

    public ContederCheck(){
        numOfVotes=0;
        notEligible=false;
        voteCasted=0;
    }

}
