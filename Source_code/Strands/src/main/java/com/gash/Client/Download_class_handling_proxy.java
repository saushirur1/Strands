package com.gash.Client;

import com.gash.Client.RPC_Implementations;

import java.util.ArrayList;
import java.util.List;

public class Download_class_handling_proxy extends Thread{
    private String ips = "";
    private int ports = 0;
    private int maxchunks;
    private static List<RPC_Implementations> channels= new ArrayList<>();
    private String file_download ="";
    private int index_download;
    private int min_chunk ;
    private List<String> ips_backup = new ArrayList<>();
    private List<Integer> port_backup = new ArrayList<>();

    Download_class_handling_proxy(String ips_from_main, int ports_from_main, int maxchunks_from_main, String file_name, int index_search, int minimun,List<String> temp,List<Integer> temp1)
    {
     this.ips = ips_from_main;
     this.ports = ports_from_main;
     this.maxchunks = maxchunks_from_main;
     this.file_download =file_name ;
     this.index_download = index_search;
     this.min_chunk = minimun;
     this.ips_backup = temp;
     this.port_backup = temp1;
    }
    @Override
    public void run()
    {
        long start  = System.currentTimeMillis();
        RPC_Implementations_for_proxyhandling rf1 = new RPC_Implementations_for_proxyhandling(ips,ports,ips_backup,port_backup);
        for(int i=min_chunk;i<maxchunks;i++)
        {
           // System.out.println("ip in download class :" +ips);
            //System.out.println("port in download class :" +ports);
            rf1.download_chunk(file_download,maxchunks,i);
        }
        try {
            rf1.shutdowncompletely();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        System.out.println("Time :" + (end - start));
    }
}
