package com.gash.Data_Centre;

import com.google.protobuf.ByteString;
import grpc.Chat;
import grpc.DataTransferServiceGrpc;
import io.grpc.Server;
import io.grpc.netty.shaded.io.grpc.netty.NettyServerBuilder;
import io.grpc.stub.StreamObserver;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DataCenterServer {

    private Server my_server;

    public static String path;
    private int port;
    DataCenterServer(String path,int port){
        this.path=path;
        this.port=port;
    }

    public static int datacenterId;

    private void start()throws IOException
    {
        //int port = 9001;
        // call server Id class and set data center Id
        datacenterId=1;
        my_server = NettyServerBuilder.forPort(port)
                .addService(new DataTransferServiceImpl())
                .build()
                .start();

        System.out.println("Server Started on port "+port);
    }

    private void stop()
    {
        if(my_server!=null)
        {
            my_server.shutdown();
        }
    }

    private void blockUntilshutdown() throws InterruptedException
    {
        if(my_server!=null)
        {
            my_server.awaitTermination();
        }
    }

    public static void main(String args[]) {
DataCenterServer dataCenterServer = new DataCenterServer("D:/CMPE275/dc1/",9000);
       // DataCenterServer dataCenterServer1 = new DataCenterServer("D:/CMPE275/dc2/",9001);
try {
    dataCenterServer.start();
    //dataCenterServer1.start();
    dataCenterServer.blockUntilshutdown();
    //dataCenterServer1.blockUntilshutdown();
}catch(IOException e){

}catch(InterruptedException e){

}


    }
    static public class DataTransferServiceImpl extends DataTransferServiceGrpc.DataTransferServiceImplBase
    {

        @Override
        public StreamObserver<Chat.FileUploadData> uploadFile(final StreamObserver<Chat.FileInfo> responseObserver)
        {
            return new StreamObserver<Chat.FileUploadData>()
            {
                int count=0;
                String filename_in_map = "";
                FileOutputStream out = null;
                @Override
                public void onNext(Chat.FileUploadData fileUploadData)
                {

                  //  String path="D:/CMPE275/dc2/";
                   // System.out.println(path);
                    File f = new File(path);
                    long intfile = fileUploadData.getChunkId();
                    String filePartName = Long.toString(intfile);
                    String[] filename_extension = fileUploadData.getFileName().split("\\.");
                    File newFile = new File(path, filename_extension[0] + filePartName + "." + filename_extension[1]);
                    filename_in_map = filename_extension[0] + filePartName + "." + filename_extension[1];
                    try
                    {
                        out = new FileOutputStream(newFile,true);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    System.out.println(fileUploadData.getSeqNum());
                    try
                    {
                        out.write(fileUploadData.getData().toByteArray(), 0, fileUploadData.getData().toByteArray().length);

                            out.close();

                    }
                    catch (IOException e)
                    {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onError(Throwable throwable)
                {
                    System.out.println("error");
                }

                @Override
                public void onCompleted()
                {


                    responseObserver.onNext(Chat.FileInfo.newBuilder().setFileName("Got")
                            .build());
                    responseObserver.onCompleted();

                }
            };
        }

        @Override
        public void downloadChunk(final grpc.Chat.ChunkInfo request, StreamObserver<Chat.FileMetaData> responseObserver)
        {
           // String filepath = "C:\\Users\\Likhith\\Documents\\junk";
            System.out.println("In DATA Server");
            String [] filename_extension = request.getFileName().split("\\.");
            long chkid=request.getChunkId()+1;
            String file_chunk_tosend = filename_extension[0]+ chkid +"." + filename_extension[1];
            System.out.println("dATA SERVER: "+ file_chunk_tosend);
            File folder = new File(path);
            File[] listOfFiles = folder.listFiles();
            System.out.println(path+file_chunk_tosend);
            File f = new File(path+file_chunk_tosend);
            int sizeOfFiles;
            if(f.length()>1024*1024){
                 sizeOfFiles = 1024* 1024 * 2;
            }
            else{
                sizeOfFiles = (int) f.length();
            }

            byte[] buffer = new byte[sizeOfFiles];
            List<byte[]> buffer_list = new ArrayList<byte[]>();
            try
            {
                FileInputStream fis = new FileInputStream(f);
                BufferedInputStream bis = new BufferedInputStream(fis);

                int bytesAmount = 0;
                while ((bytesAmount = bis.read(buffer)) > 0)
                {
                    buffer_list.add(buffer);
                    buffer = new byte[sizeOfFiles];
                }
                for(int i = (int) request.getStartSeqNum(); i<buffer_list.size(); i++) {
                    System.out.println("Sending data to client");
                    Chat.FileMetaData m = Chat.FileMetaData.newBuilder().setFileName(request.getFileName()).setChunkId(request.getChunkId()).setSeqNum(i)
                            .setSeqMax(buffer_list.size()).setData(ByteString.copyFrom(buffer_list.get(i))).build();
                    responseObserver.onNext(m);
                }
                responseObserver.onCompleted();
                bis.close();
            }
            catch(Exception e)
            {

            }

        }
    }
}

