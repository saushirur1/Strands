package com.gash.Raft;

import com.gash.Raft.RaftNodeClient;
import grpc.Raft;
import io.grpc.StatusRuntimeException;

public class LeaderUpdater extends Thread {
    private RaftNodeClient raftNodeClient;
    Raft.UpdateEntry updateEntry;
public LeaderUpdater(RaftNodeClient raftNodeClient,Raft.UpdateEntry updateEntry){
    this.raftNodeClient=raftNodeClient;
    this.updateEntry=updateEntry;
}

    @Override
    public void run() {
        Raft.Followerupdate  followerupdate;
        try {

            followerupdate = raftNodeClient.getBlockingStub().appendentries(updateEntry);
            System.out.println("Leader Update Sent");
        }
        catch (StatusRuntimeException e)
        {
            System.out.print(" Leader cant Update as this Server is Down ==> " +raftNodeClient.getHostIp()+"."+raftNodeClient.getHostPort() );
        }
    }
}