package com.gash.Raft;

import grpc.DataTransferServiceGrpc;
import grpc.RaftServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.util.concurrent.TimeUnit;

public class RaftNodeClient {

    public RaftServiceGrpc.RaftServiceBlockingStub getBlockingStub() {
        return blockingStub;
    }

    private final ManagedChannel channel;
    private final RaftServiceGrpc.RaftServiceBlockingStub blockingStub;

    public DataTransferServiceGrpc.DataTransferServiceBlockingStub getBlockingStubdatatransfer() {
        return blockingStubdatatransfer;
    }

    private final DataTransferServiceGrpc.DataTransferServiceBlockingStub blockingStubdatatransfer;

    public String getHostIp() {
        return hostIp;
    }

    public int getHostPort() {
        return hostPort;
    }

    private String hostIp;
    private int hostPort;


    public RaftNodeClient(String hostip, int hostport) {

        this(ManagedChannelBuilder.forAddress(hostip, hostport)
                .usePlaintext()
                .build());
        this.hostIp=hostip;
        this.hostPort=hostport;
    }

    RaftNodeClient(ManagedChannel channel) {
        this.channel = channel;
        blockingStub = RaftServiceGrpc.newBlockingStub(channel);
        blockingStubdatatransfer= DataTransferServiceGrpc.newBlockingStub(channel);
    }
    public void shutdown() throws InterruptedException {
        channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
    }

}
