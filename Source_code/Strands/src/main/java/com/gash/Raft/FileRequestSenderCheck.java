package com.gash.Raft;

import grpc.Chat;

public class FileRequestSenderCheck  {
    public Chat.FileLocationInfo getFileLocationInfo() {
        return fileLocationInfo;
    }

    public void setFileLocationInfo(Chat.FileLocationInfo fileLocationInfo) {
        this.fileLocationInfo = fileLocationInfo;
    }

    public boolean isFileFound() {
        return fileFound;
    }

    public void setFileFound(boolean fileFound) {
        this.fileFound = fileFound;
    }

    public int getRpcCount() {
        return rpcCount;
    }

    public void setRpcCount(int rpcCount) {
        this.rpcCount = rpcCount;
    }

    private Chat.FileLocationInfo fileLocationInfo;
    private boolean fileFound;
     private int rpcCount;

    public int getNoOfNodes() {
        return noOfNodes;
    }

    private int noOfNodes;
    FileRequestSenderCheck(int noOfNodes){
        this.fileFound=false;
        this.rpcCount=0;
        fileLocationInfo=null;
       this.noOfNodes=noOfNodes;
    }

   /* @Override
    public void run() {
        while (!fileFound && rpcCount<noOfNodes){

        }
    }
*/
    }
