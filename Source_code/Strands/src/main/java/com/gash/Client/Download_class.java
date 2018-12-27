package com.gash.Client;

import com.gash.Client.RPC_Implementations;

import java.util.ArrayList;
import java.util.List;

public class Download_class extends Thread{
    private String ips = "";
    private int ports = 0;
    private int maxchunks;
    private static List<RPC_Implementations> channels= new ArrayList<>();
    private String file_download ="";
    private int index_download;
    private int min_chunk ;

    Download_class(String ips_from_main, int ports_from_main,int maxchunks_from_main,String file_name,int index_search,int minimun)
    {
     this.ips = ips_from_main;
     this.ports = ports_from_main;
     this.maxchunks = maxchunks_from_main;
     this.file_download =file_name ;
     this.index_download = index_search;
     this.min_chunk = minimun;
    }
    @Override
    public void run()
    {
        long start  = System.currentTimeMillis();
        RPC_Implementations rf = new RPC_Implementations(ips,ports);
        for(int i=min_chunk;i<maxchunks;i++)
        {
           // System.out.println("ip in download class :" +ips);
            //System.out.println("port in download class :" +ports);
            rf.download_chunk(file_download,maxchunks,i);
        }
        try {
            rf.shutdowncompletely();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        System.out.println("Time chunk:"+file_download +"___"+  maxchunks + "__" + (end - start));
    }
}
