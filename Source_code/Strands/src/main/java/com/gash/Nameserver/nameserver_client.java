package com.gash.Nameserver;

import grpc.DataCenterServiceGrpc;
import grpc.Datacentre;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
public class nameserver_client {
    public int port_tocheck;
    public String ip_tocheck;
    private final ManagedChannel channel;

    public DataCenterServiceGrpc.DataCenterServiceStub getFutureStub() {
        return futureStub;
    }

    private final DataCenterServiceGrpc.DataCenterServiceStub futureStub;
    private final DataCenterServiceGrpc.DataCenterServiceBlockingStub blockingStub;

    public nameserver_client(String ip, int port) {
        this(ManagedChannelBuilder.forAddress(ip, port).usePlaintext().perRpcBufferLimit(1024).build());
        this.port_tocheck = port;
        this.ip_tocheck = ip;
    }

    nameserver_client(ManagedChannel channel) {
        this.channel = channel;
        this.futureStub = DataCenterServiceGrpc.newStub(channel);
        this.blockingStub = DataCenterServiceGrpc.newBlockingStub(channel);
    }

    public void shutdown() throws InterruptedException {
        channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
    }

    public void shutdowncompletely() throws InterruptedException {
        channel.shutdownNow();
    }
    public void updatebackupNS(String key, String value)
    {
        try {
            Datacentre.UpdateMap up = Datacentre.UpdateMap.newBuilder().setFileName(key).setDataCenters(value).build();
            Datacentre.NameServerAprooved approved =  blockingStub.updateBackUpNS(up);
        }
        catch (StatusRuntimeException e)
        {
            System.out.println("No RPC operation");
        }
    }
}