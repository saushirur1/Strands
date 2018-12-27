package com.gash.Client;

import com.google.protobuf.ByteString;
import grpc.Chat;
import grpc.DataTransferServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;

import static grpc.Chat.FileInfo.newBuilder;

import grpc.Chat.FileUploadData;
import grpc.Chat.FileInfo;
import io.grpc.stub.StreamObserver;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class RPC_Implementations
{
    public int port_tocheck;
    public String ip_tocheck;
    private final ManagedChannel channel;

    public DataTransferServiceGrpc.DataTransferServiceStub getFutureStub() {
        return futureStub;
    }

    private final DataTransferServiceGrpc.DataTransferServiceStub futureStub;
    private final DataTransferServiceGrpc.DataTransferServiceBlockingStub blockingStub;

    public RPC_Implementations(String ip, int port)
    {
        this(ManagedChannelBuilder.forAddress(ip, port).usePlaintext().perRpcBufferLimit(1024).build());
        this.port_tocheck = port;
        this.ip_tocheck = ip;
    }

    RPC_Implementations(ManagedChannel channel)
    {
        this.channel = channel;
        this.futureStub = DataTransferServiceGrpc.newStub(channel);
        this.blockingStub = DataTransferServiceGrpc.newBlockingStub(channel);
    }

    public void shutdown() throws InterruptedException {
        channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
    }

    public void shutdowncompletely() throws InterruptedException {
        channel.shutdownNow();
    }

    public void upload_File(String file_name,List<byte[]> bytes_of_file,int Bytes_amount,int chunk_id, int sequence_number)
    {
     //   System.out.println("Sending");
      //  System.out.println("RPC SIZE " + bytes_of_file.length);
        StreamObserver<FileInfo> responseObserver = new StreamObserver<FileInfo>() {
            @Override
            public void onNext(FileInfo fileInfo)
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
        StreamObserver<FileUploadData> streaming = futureStub.uploadFile(responseObserver);
        if(sequence_number > 0)
        {
            for (int i = 0; i < bytes_of_file.size(); i++) {
                FileUploadData msg = FileUploadData.newBuilder().setFileName(file_name).setChunkId(chunk_id).setData(ByteString.copyFrom(bytes_of_file.get(i))).setSeqNum(i + 1).build();
                try
                {
                    streaming.onNext(msg);
                    Thread.sleep(450);
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
            }
        }
             streaming.onCompleted();
    }

    public void download_chunk (String file_name_todownload,long max_chunks_todownload,int chunk_number)
    {
        Chat.ChunkInfo ch = Chat.ChunkInfo.newBuilder().setFileName(file_name_todownload).setChunkId(chunk_number).setStartSeqNum(0).build();
        Iterator<Chat.FileMetaData> features;
        try {
            features = blockingStub.downloadChunk(ch);
            FileOutputStream out = null;
            for (int i = 1; features.hasNext(); i++) {
                Chat.FileMetaData feature = features.next();
                String[] filename_extension = file_name_todownload.split("\\.");
                File f = new File("/Users/saurabhshirur/desktop/Avengers movie/");
                File newFile = new File("/Users/saurabhshirur/desktop/Avengers movie/" + filename_extension[0] + String.valueOf(chunk_number + 1) + "." + filename_extension[1]);
                try {
                    out = new FileOutputStream(newFile, true);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                try {
                    out.write(feature.getData().toByteArray(), 0, feature.getData().toByteArray().length);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                out.close();
            }
            }
         catch (StatusRuntimeException e) {
            System.out.println("No RPC operation at Download_chunk - RPC-Implementations -Mathew server");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void Request_File_Info(String name, List<List<String>> ip_to_downloadfrom , List<List<Integer>> ports_to_downloadfrom, List<String> filenames_tosend,List<Integer>max_chunks)
    {
        //System.out.println("Sending just a message/ Request_File_Info");
        FileInfo information = FileInfo.newBuilder().setFileName(name).build();
        Chat.FileLocationInfo returing_info;
        List<Chat.ProxyInfo> proxyinfo = new ArrayList<Chat.ProxyInfo>();
        List<String> ips_to_send = new ArrayList<String>();
        List<Integer> ports_to_use = new ArrayList<>();
        try
        {
            returing_info = blockingStub.requestFileInfo(information);
            proxyinfo = returing_info.getLstProxyList();
            System.out.println(returing_info.getFileName());
            System.out.println(returing_info.getMaxChunks());
            long Max_chunks = returing_info.getMaxChunks();
            for(int i=0;i<proxyinfo.size();i++)
            {
                System.out.println(proxyinfo.get(i).getIp());
                ips_to_send.add(proxyinfo.get(i).getIp());
                System.out.println(proxyinfo.get(i).getPort());
                ports_to_use.add(Integer.valueOf(proxyinfo.get(i).getPort()));
            }
            ip_to_downloadfrom.add(ips_to_send);
            ports_to_downloadfrom.add(ports_to_use);
            filenames_tosend.add(name);
            max_chunks.add((int) returing_info.getMaxChunks());
        }
        catch (StatusRuntimeException e)
        {
            System.out.println("No RPC operation at Request-file- RPC_Implementations");
        }
    }

    public void List_Files()
    {
        List<String> listoffiles = new ArrayList<String>();
      //  System.out.println("Requesting file information");
        Chat.RequestFileList requesting_info = Chat.RequestFileList.newBuilder().setIsClient(true).build();
        Chat.FileList list_obtained;
        try
        {
            list_obtained = blockingStub.listFiles(requesting_info);
            listoffiles = list_obtained.getLstFileNamesList();
            System.out.println(listoffiles.size());
            System.out.println(listoffiles.toString());
        }
        catch (StatusRuntimeException e)
        {
            System.out.println("No RPC operation at List_files at RPC_Implementations");
        }
    }
}
