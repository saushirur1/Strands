package com.gash.Raft;

import com.gash.Raft.RaftNodeClient;
import grpc.Chat;

import java.util.ArrayList;
import java.util.List;

public class ListFileSender extends Thread
{
    private RaftNodeClient raftNodeClient;
    private Chat.RequestFileList requestFileList;
    private ListFileSenderCheck listFileSenderCheck;

    ListFileSender(RaftNodeClient raftNodeClient,Chat.RequestFileList requestFileList, ListFileSenderCheck listFileSenderCheck){
 this.raftNodeClient=raftNodeClient;
 this.requestFileList=requestFileList;
 this.listFileSenderCheck=listFileSenderCheck;
    }
    @Override
    public void run() {

        Chat.FileList fileList=raftNodeClient.getBlockingStubdatatransfer().listFiles(requestFileList);
        //Chat.FileLocationInfo fileLocationInfo= raftNodeClient.getBlockingStubdatatransfer().getFileLocation(fileInfo);
        if(fileList!=null) {
            if (fileList.getLstFileNamesCount() > 0) {
                listFileSenderCheck.setFileFound(true);
                List<String> listoffiles = new ArrayList<String>();
                listoffiles = fileList.getLstFileNamesList();
                listFileSenderCheck.appendOtherNodeFiles(listoffiles);
            }
        }
        listFileSenderCheck.setRpcCount( listFileSenderCheck.getRpcCount()+1);
        System.out.println(listFileSenderCheck.getRpcCount());
    }
}
