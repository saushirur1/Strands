package com.gash.Raft;

import java.util.Date;

public class LeaderExecution extends Thread {

    private  ServerStateImpl serverStateImpl;
    public volatile static boolean LeaderState;
    private long timerPeriod;
    private long timerDelay;

    public long getRecentHeartbeatSentTime() {
        return recentHeartbeatSentTime;
    }

    public void setRecentHeartbeatSentTime(long recentHeartbeatSentTime) {
        this.recentHeartbeatSentTime = recentHeartbeatSentTime;
    }

    private long recentHeartbeatSentTime;


    LeaderExecution(long timerDelay,long timerPeriod,ServerStateImpl serverStateImpl){
        this.timerPeriod=timerPeriod;
        this.timerDelay=timerDelay;
        this.serverStateImpl=serverStateImpl;
        LeaderState=true;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(timerDelay);
        }catch (InterruptedException e){

        }

       // while (LeaderState){

                while(serverStateImpl.currentServerState==ServerStateImpl.State.Leader) {
                    System.out.println(" Leader Task performed on: " + new Date() + "n " +
                            "Thread's name: " + Thread.currentThread().getName());

                    long now = System.currentTimeMillis();
                    if (now - recentHeartbeatSentTime > timerPeriod) {
                        // start candidate part
                        System.out.println("sending Leader HeartBeat");
                        serverStateImpl.sendHBFromLeader();
                        recentHeartbeatSentTime=System.currentTimeMillis();


                    }
                    try {
                        Thread.sleep(timerPeriod);
                    } catch (InterruptedException e) {

                    }
                }

            }

   // }

}


