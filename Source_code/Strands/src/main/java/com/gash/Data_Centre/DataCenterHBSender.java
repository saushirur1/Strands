package com.gash.Data_Centre;

import com.gash.Data_Centre.DataCenterClient;
import grpc.Datacentre;

import java.util.Date;

public class DataCenterHBSender extends Thread {

    private DataCenterClient dataCenterClient;
    private long timerPeriod;
    private long timerDelay;
    private volatile long recentHeartbeatTime;
    private long end;

    public boolean isControlTimer() {
        return controlTimer;
    }

    public void setControlTimer(boolean controlTimer) {
        this.controlTimer = controlTimer;
    }

    private boolean controlTimer;

    public long getRecentHeartbeatTime() {
        return recentHeartbeatTime;
    }

    public void setRecentHeartbeatTime(long recentHeartbeatTime) {
        this.recentHeartbeatTime = recentHeartbeatTime;
    }

    DataCenterHBSender(long timerDelay,long timerPeriod,DataCenterClient dataCenterClient) {
        this.timerPeriod=timerPeriod;
        this.timerDelay=timerDelay;
        this.dataCenterClient=dataCenterClient;
    }

    @Override
    public void run() {

        try {
            Thread.sleep(timerDelay);
        }catch (InterruptedException e){

        }
        while (controlTimer) {
                System.out.println("Sending HB From DC on: " + new Date() + "n " +
                        "Thread's name: " + Thread.currentThread().getName());

            send_Data_center_HB();
                try {
                    Thread.sleep(timerPeriod);
                } catch (InterruptedException e) {

                }



        }
    }

    public void send_Data_center_HB(){
        Datacentre.DataCenterID dataCenterID=Datacentre.DataCenterID.newBuilder().setDcId(DataCenterServer.datacenterId).build();
        dataCenterClient.getBlockingStub().dataCenterHB(dataCenterID);


    }

}
