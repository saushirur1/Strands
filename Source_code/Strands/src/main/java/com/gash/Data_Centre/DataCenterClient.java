package com.gash.Data_Centre;

import com.google.protobuf.ByteString;
import grpc.Chat;
import grpc.DataCenterServiceGrpc;
import grpc.DataTransferServiceGrpc;
import grpc.Datacentre;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;

import java.util.concurrent.TimeUnit;

public class DataCenterClient {

    public DataCenterServiceGrpc.DataCenterServiceBlockingStub getBlockingStub() {
        return blockingStub;
    }

    private final ManagedChannel channel;
    private final DataCenterServiceGrpc.DataCenterServiceBlockingStub blockingStub;

   /* public DataCenterServiceGrpc.DataCenterServiceBlockingStub getAsyncStub() {
        return asyncStub;
    }

    private final DataCenterServiceGrpc.DataCenterServiceBlockingStub asyncStub;*/

    public String getHostIp() {
        return hostIp;
    }

    public int getHostPort() {
        return hostPort;
    }

    private String hostIp;
    private int hostPort;


    public DataCenterClient(String hostip, int hostport) {

        this(ManagedChannelBuilder.forAddress(hostip, hostport)
                .usePlaintext()
                .build());
        this.hostIp=hostip;
        this.hostPort=hostport;
    }

    DataCenterClient(ManagedChannel channel) {
        this.channel = channel;
        blockingStub = DataCenterServiceGrpc.newBlockingStub(channel);
    }
    public void shutdown() throws InterruptedException {
        channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
    }
    public void shutdowncompletely() throws InterruptedException {
        channel.shutdownNow();
    }


}
