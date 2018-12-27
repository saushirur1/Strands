package com.gash.Client;

import com.google.protobuf.ByteString;
import grpc.Chat;
import io.grpc.stub.StreamObserver;

import java.util.List;

public class Uploadtreadclass extends Thread {

    String file_name;
    List<byte[]> bytes_of_file;
    int Bytes_amount;int chunk_id;
    int sequence_number;
    RPC_Implementations r;
    Uploadtreadclass(RPC_Implementations r,String file_name, List<byte[]> bytes_of_file, int Bytes_amount, int chunk_id,
                     int sequence_number){

        this.r=r;
        this.file_name=file_name;
        this.Bytes_amount=Bytes_amount;
        this.bytes_of_file=bytes_of_file;
        this.chunk_id=chunk_id;
        this.sequence_number=sequence_number;

    }
    @Override
    public void run() {



        //   System.out.println("Sending");
        //  System.out.println("RPC SIZE " + bytes_of_file.length);
        StreamObserver<Chat.FileInfo> responseObserver = new StreamObserver<Chat.FileInfo>() {
            @Override
            public void onNext(Chat.FileInfo fileInfo)
            {
                System.out.println(fileInfo.getFileName());
            }

            @Override
            public void onError(Throwable throwable)
            {
                System.out.println("error");
            }

            @Override
            public void onCompleted()
            {
                System.out.println("Completed");
            }
        };
        StreamObserver<Chat.FileUploadData> streaming = r.getFutureStub().uploadFile(responseObserver);
        if(sequence_number > 0)
        {
            for (int i = 0; i < bytes_of_file.size(); i++) {
                Chat.FileUploadData msg = Chat.FileUploadData.newBuilder().setFileName(file_name).setChunkId(chunk_id).setData(ByteString.copyFrom(bytes_of_file.get(i))).setSeqNum(i + 1).build();
                try {
                    streaming.onNext(msg);
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        streaming.onCompleted();
        try {
            r.shutdown();
        }catch (InterruptedException e){

        }
    }
}
