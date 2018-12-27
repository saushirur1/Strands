package com.gash.Raft;

import grpc.Chat;

public class FileRequestSender extends Thread
{
    private RaftNodeClient raftNodeClient;
    private Chat.FileInfo fileInfo;
    private FileRequestSenderCheck fileRequestSenderCheck;

    FileRequestSender(RaftNodeClient raftNodeClient,Chat.FileInfo fileInfo,FileRequestSenderCheck fileRequestSenderCheck){
 this.raftNodeClient=raftNodeClient;
 this.fileInfo=fileInfo;
 this.fileRequestSenderCheck=fileRequestSenderCheck;
    }
    @Override
    public void run() {

        Chat.FileLocationInfo fileLocationInfo= raftNodeClient.getBlockingStubdatatransfer().getFileLocation(fileInfo);
        if(fileLocationInfo!=null) {
            if (fileLocationInfo.getIsFileFound()) {
                fileRequestSenderCheck.setFileFound(true);
                fileRequestSenderCheck.setFileLocationInfo(fileLocationInfo);
            }
        }
        fileRequestSenderCheck.setRpcCount( fileRequestSenderCheck.getRpcCount()+1);
    }
}
