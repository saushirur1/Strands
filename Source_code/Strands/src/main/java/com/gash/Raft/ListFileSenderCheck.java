package com.gash.Raft;

import grpc.Chat;

import java.util.ArrayList;
import java.util.List;

public class ListFileSenderCheck {
   /* public Chat.FileLocationInfo getFileLocationInfo() {
        return fileLocationInfo;
    }*/

   /* public void setFileLocationInfo(Chat.FileLocationInfo fileLocationInfo) {
        this.fileLocationInfo = fileLocationInfo;
    }*/

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

   // private Chat.FileLocationInfo fileLocationInfo;
    private boolean fileFound;
     private int rpcCount;

    public List<String> getOtherNodeFiles() {
        return otherNodeFiles;
    }

    public void appendOtherNodeFiles(List<String> otherNodeFilesgot) {
        otherNodeFiles.addAll(otherNodeFilesgot);
    }

    private List<String> otherNodeFiles;

    public int getNoOfNodes() {
        return noOfNodes;
    }

    private int noOfNodes;
    ListFileSenderCheck(int noOfNodes){
        this.fileFound=false;
        this.rpcCount=0;
        otherNodeFiles=new ArrayList<String>();
       this.noOfNodes=noOfNodes;
    }

   /* @Override
    public void run() {
        while (!fileFound && rpcCount<noOfNodes){

        }
    }
*/
    }
