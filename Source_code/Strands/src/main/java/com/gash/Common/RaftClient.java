package com.gash.Common;

import com.google.protobuf.ByteString;
import grpc.Chat;
import grpc.DataCenterServiceGrpc;
import grpc.DataTransferServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;

import java.util.concurrent.TimeUnit;

public class RaftClient {

    public DataTransferServiceGrpc.DataTransferServiceBlockingStub getBlockingStub() {
        return blockingStub;
    }

    private final ManagedChannel channel;
    private final DataTransferServiceGrpc.DataTransferServiceBlockingStub blockingStub;

    public DataCenterServiceGrpc.DataCenterServiceBlockingStub getDcblockingStub() {
        return dcblockingStub;
    }

    private final DataCenterServiceGrpc.DataCenterServiceBlockingStub dcblockingStub;
    public DataTransferServiceGrpc.DataTransferServiceStub getAsyncStub() {
        return asyncStub;
    }

    private final DataTransferServiceGrpc.DataTransferServiceStub asyncStub;

    public String getHostIp() {
        return hostIp;
    }

    public int getHostPort() {
        return hostPort;
    }
    private String hostIp;
    private int hostPort;


    public RaftClient(String hostip, int hostport) {

        this(ManagedChannelBuilder.forAddress(hostip, hostport)
                .usePlaintext()
                .build());
        this.hostIp=hostip;
        this.hostPort=hostport;
    }

    RaftClient(ManagedChannel channel) {
        this.channel = channel;
        dcblockingStub=DataCenterServiceGrpc.newBlockingStub(channel);
        blockingStub = DataTransferServiceGrpc.newBlockingStub(channel);
        asyncStub= DataTransferServiceGrpc.newStub(channel);
    }
    public void shutdown() throws InterruptedException {
        channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
    }
    public void shutdowncompletely() throws InterruptedException {
        channel.shutdownNow();
    }
    public void is_file_Available_In_NS(){

    }
    public void upload_File_To_NameServer(String file_name,byte[] bytes_of_file,int Bytes_amount,int chunk_id, int sequence_number)
    {
        //   System.out.println("Sending");
        //  System.out.println("RPC SIZE " + bytes_of_file.length);
        StreamObserver<Chat.FileInfo> responseObserver = new StreamObserver<Chat.FileInfo>() {
            @Override
            public void onNext(Chat.FileInfo fileInfo) {
                System.out.println(fileInfo.getFileName());
            }

            @Override
            public void onError(Throwable throwable) {
                System.out.println("error");
            }

            @Override
            public void onCompleted() {
                System.out.println("Completed");
            }
        };
        StreamObserver<Chat.FileUploadData> streaming = asyncStub.uploadFile(responseObserver);
        if(sequence_number > 0) {
            Chat.FileUploadData msg = Chat.FileUploadData.newBuilder().setFileName(file_name).setChunkId(chunk_id).setData(ByteString.copyFrom(bytes_of_file)).setSeqNum(sequence_number).build();
            try {
                streaming.onNext(msg);
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if(sequence_number == -1000)
        {
            streaming.onCompleted();
        }
    }
}

