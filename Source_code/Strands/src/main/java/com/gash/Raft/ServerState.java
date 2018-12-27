package com.gash.Raft;

import java.util.ArrayList;
import java.util.List;

public class ServerState {

    public ServerState(){
        voteCastTerm= new ArrayList<Long>();
    }
    private long id;
    private long currentTerm;
    private long votedFor;
    public List<Long> voteCastTerm;
    public long getLeaderId() {
        return leaderId;
    }

    public void setLeaderId(long leaderId) {
        this.leaderId = leaderId;
    }

    private long leaderId;

    public String getLeaderIp() {
        return leaderIp;
    }

    public void setLeaderIp(String leaderIp) {
        this.leaderIp = leaderIp;
    }

    private String leaderIp;
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    private long leaderTerm;
    private long log[];
    private long lastLogTerm;
    private int lastLogIndex=1;

    private volatile long highestCommit;

    //only for leader
    private volatile long nextIndex[];
    private volatile long matchIndex[];


    public void logUpdate(long logValue){
        log[lastLogIndex]=logValue;
        lastLogTerm=log[lastLogIndex];
        lastLogIndex++;

    }
    public long getCurrentTerm() {
        return currentTerm;
    }

    public void setCurrentTerm(long currentTerm) {
        this.currentTerm = currentTerm;
    }

    public long getVotedFor() {
        return votedFor;
    }

    public void setVotedFor(long votedFor) {
        this.votedFor = votedFor;
    }


    public long getLeaderTerm() {
        return leaderTerm;
    }

    public void setLeaderTerm(long leaderTerm) {
        this.leaderTerm = leaderTerm;
    }

    public long[] getLog() {
        return log;
    }

    public void setLog(long[] log) {
        this.log = log;
    }

    public long getLastLogTerm() {
        return lastLogTerm;
    }

    public void setLastLogTerm(long lastLogTerm) {
        this.lastLogTerm = lastLogTerm;
    }

    public long getLastLogIndex() {
        return lastLogIndex;
    }

    public void setLastLogIndex(int lastLogIndex) {
        this.lastLogIndex = lastLogIndex;
    }

    public long getHighestCommit() {
        return highestCommit;
    }

    public void setHighestCommit(long highestCommit) {
        this.highestCommit = highestCommit;
    }

    public long[] getNextIndex() {
        return nextIndex;
    }

    public void setNextIndex(long[] nextIndex) {
        this.nextIndex = nextIndex;
    }

    public long[] getMatchIndex() {
        return matchIndex;
    }

    public void setMatchIndex(long[] matchIndex) {
        this.matchIndex = matchIndex;
    }
}
