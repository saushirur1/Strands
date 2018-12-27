package com.gash.Raft;/*

import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TimerController  {

    private long timerPeriod;
    private long timerDelay;
    private long recentHeartbeatTime;
    private long end;
    private ServerStateImpl serverStateImpl;

    public long getRecentHeartbeatTime() {
        return recentHeartbeatTime;
    }

    public void setRecentHeartbeatTime(long recentHeartbeatTime) {
        this.recentHeartbeatTime = recentHeartbeatTime;
    }


    public  TimerController(long timerDelay,long timerPeriod,ServerStateImpl serverStateImpl){
        this.timerPeriod=timerPeriod;
        this.timerDelay=timerDelay;
        this.serverStateImpl=serverStateImpl;
    }

    public  void startTimer( ) {
        TimerTask task = new TimerTask() {
            public void run() {

                System.out.println("Task performed on: " + new Date() + "n " +
                        "Thread's name: " + Thread.currentThread().getName());

                long now = System.currentTimeMillis();
                if (now - recentHeartbeatTime > timerPeriod){
                    // start candidate part
                    System.out.println("call leader election");

                    serverStateImpl.setCurrentServerState(ServerStateImpl.State.Contender);
                    cancel();

                    System.out.println("cancled Timer");

                }


            }
        };
        Timer timer = new Timer("Timer");

        */
/*long delay = 0;
        long period = 5000L;*//*

        //timer.schedule(task, delay);
        timer.scheduleAtFixedRate(task, timerDelay, timerPeriod);

    }//timerTask.cancel();

    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        String s="";
         ServerStateImpl sce=new ServerStateImpl(3);
       */
/* TimerController tc=new TimerController(1000,250,sce);
        tc.startTimer();
        while(true){
            System.out.println("set heartbeat time");
            sc.nextLine();
            tc.setRecentHeartbeatTime(System.currentTimeMillis());

        }*//*

    }

}
*/

import java.util.Date;

public class TimerController extends Thread {

    private boolean controlFollowerTimer;
    private long timerPeriod;
    private long timerDelay;
    public volatile static boolean changeState;

    private int contenderCount;
    //public volatile static boolean changeContenderState;


    private volatile long recentHeartbeatTime;
    private long end;
    private ServerStateImpl serverStateImpl;


    public long getRecentHeartbeatTime() {
        return recentHeartbeatTime;
    }

    public void setRecentHeartbeatTime(long recentHeartbeatTime) {
        this.recentHeartbeatTime = recentHeartbeatTime;
    }

    TimerController(long timerDelay,long timerPeriod,ServerStateImpl serverStateImpl) {
        this.timerPeriod=timerPeriod;
        this.timerDelay=timerDelay;
        this.serverStateImpl=serverStateImpl;
        controlFollowerTimer = true;
        changeState=false;
        contenderCount=0;
        //changeContenderState=false;
    }

    @Override
    public void run() {

        try {
            Thread.sleep(timerDelay);
        }catch (InterruptedException e){

        }
        while (controlFollowerTimer) {
            while(serverStateImpl.currentServerState==ServerStateImpl.State.Follower) {
                if(changeState){
                    try {
                        Thread.sleep(timerDelay);
                        changeState=false;
                    }catch (InterruptedException e){
                    }

                }
                System.out.println("Task performed on: " + new Date() + "n " +
                        "Thread's name: " + Thread.currentThread().getName());

                long now = System.currentTimeMillis();
                if (now - recentHeartbeatTime > timerPeriod *2) {
                    // start candidate part
                    contenderCount++;
                    if(contenderCount>5) {
                        System.out.println("call leader election");
                        serverStateImpl.setCurrentServerState(ServerStateImpl.State.Contender);
                        changeState = true;
                    }
                   // System.out.println("cancled Timer");

                }
                else{
                    contenderCount=0;
                }
                try {
                    Thread.sleep(timerPeriod);
                } catch (InterruptedException e) {

                }
            }
            while(serverStateImpl.currentServerState==ServerStateImpl.State.Contender) {
                contenderCount=0;
                if(changeState){
                    try {
                        Thread.sleep(timerDelay);
                        changeState=false;
                    }catch (InterruptedException e){

                    }
                    serverStateImpl.startCandidateElection();


                }
            }

            }
    }
    }
