package com.gash.Client;

import com.gash.Client.Fileupload_class;
import com.gash.Client.RPC_Implementations;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

import static java.lang.System.currentTimeMillis;

public class DFSMain
{
    public static class Config_info
    {
        String ips;
        String ports;

    }
    public static void main(String[] args) throws IOException, InterruptedException
    {
        Scanner sc = new Scanner(System.in);
        Scanner sc1 = new Scanner(System.in);
        System.out.println("In main class");
        List<List<String>> ip_to_downloadfrom = new ArrayList<List<String>>();
        List<List<Integer>> pots_to_downloadfrom = new ArrayList<>();
        List<String> file_names = new ArrayList<String>();
        List<Config_info>clusterIps=new ArrayList<Config_info>();
        List<String> clusterIpsInStr= new ArrayList<String>();
        List<Integer> max_chunks_todownload = new ArrayList<>();
        Properties prop = new Properties();
        final int Servers = 4;
        try {
            String fileName = "conf/find_leader.conf";


            InputStream is = new FileInputStream(fileName);

            prop.load(is);
            Config_info ips;
            for (int i = 1; i < Servers + 1; i++) {
                ips = new Config_info();
                ips.ips = prop.getProperty("server" + i + ".ip");
                ips.ports = prop.getProperty("server" + i + ".port");
                clusterIps.add(ips);
                clusterIpsInStr.add(ips.ips + "." + ips.ports);
            }
        }
        catch (Exception e)
        {

        }
//        for(Config_info iw:clusterIps)
//        {
//            System.out.println("======");
//            System.out.println(iw.ips);
//            System.out.println(iw.ports);
//            System.out.println("======");
//        }
            while (!false)
            {
            int case_input = sc.nextInt();
            int flag = 1;
            RPC_Implementations up = new RPC_Implementations("10.0.30.3",10000);
                //Integer.parseInt(clusterIps.get(0).ports);
            switch (case_input)
            {
                case 1:
                    //Upload File
                    System.out.println("Enter the file directory");
                    String filename = "";
                    try {
                        filename = sc1.nextLine();
                    }
                    catch (Exception e)
                    {
                        System.out.println("Enter Proper File Directory");
                    }
                    Fileupload_class cl = new Fileupload_class(filename,up);
                    cl.start();
                    break;
                case 2:
                    //Download File
                    System.out.println("Enter the file to download : ");
                    String filename_to_download = sc1.nextLine();
                    //System.out.println("Download");
                   // for(int j=0;j<1000;j++) {
                    int index = file_names.indexOf(filename_to_download);
                    int max_chunks = max_chunks_todownload.get(index);
                    List<Integer> each_proxy_count = new ArrayList<>();
                    int extra_count = 0;
                    int number_equal = ip_to_downloadfrom.get(index).size();
                    long startTime = System.nanoTime();
                    int num_equal1 = max_chunks / number_equal;
                    int rem = max_chunks % number_equal;
                    int max_sequence = num_equal1;
                    int min = 0;
                        for (int i = 0; i < ip_to_downloadfrom.get(index).size(); i++) {
                            System.out.println("min:" + min);
                            System.out.println("max_sequence :" + max_sequence);
                            Download_class dc = new Download_class(ip_to_downloadfrom.get(index).get(i), pots_to_downloadfrom.get(index).get(i), max_sequence, filename_to_download, index, min);
                           // Download_class dc = new Download_class(ip_to_downloadfrom.get(index).get(0),pots_to_downloadfrom.get(index).get(0),max_chunks,filename_to_download,index,min);
                              min = min + num_equal1;
                              max_sequence += num_equal1;
                            if (i == ip_to_downloadfrom.get(index).size() - 2) {
                                max_sequence = max_sequence + rem;
                            }
                              dc.start();
                        }
                  // }
                    //  System.out.println("min outside loop:" + min);
                    //  System.out.println("max_sequence outside loop:" + max_sequence);
                    break;
                case 3:
                    //List Files RPC
                   // System.out.println("List files");
                    up.List_Files();
                    break;
                case 4:
                    //Request File Location
                   // System.out.println("Request file-Location within team");
                    System.out.println("Enter file name");
                    String file_to_search = sc1.nextLine();
                    up.Request_File_Info(file_to_search,ip_to_downloadfrom,pots_to_downloadfrom,file_names,max_chunks_todownload);
                    break;
                case 5:
                    //To merge file chunks
                    //System.out.println("Merge filechunks");
                    String file_to_merge = "";
                    String file_name = "";
                    try {
                        file_to_merge= sc1.nextLine();
                        System.out.println("Enter the first_name of the file");
                        file_name = sc1.nextLine();
                    }
                    catch (Exception e)
                    {
                        System.out.println("Incorrect filename to merge/ Cant merge");
                    }
                    Merge_FileChunks merge_file = new Merge_FileChunks(file_to_merge,file_name);
                    merge_file.mergeFiles();
                    break;
                case 6:
                    //Upload Files in parallel
                  //  System.out.println("Parallel upload");
                    System.out.println("Enter the file directory");
                    String filename1 = "";
                    try {
                        filename = sc1.nextLine();
                    }
                    catch (Exception e)
                    {
                        System.out.println("Enter Proper File Directory");
                    }
                    Fileupload_class1 cl1 = new Fileupload_class1(filename1,up);
                    cl1.start();
                    break;
                case 7:
                    //List Files 1000 times
                    long start =System.currentTimeMillis();
                    for(int i=0;i<1000;i++)
                    {
                        System.out.println(i);
                        up.List_Files();
                    }
                    long end = System.currentTimeMillis();
                    System.out.println(String.format("time: %d", end - start));
                    break;
                case 8:
                    //Download in parallel even when one of the proxy servers are down
                    System.out.println("Enter the file to download : ");
                    String filename_to_download1 = sc1.nextLine();
                    System.out.println("Download");
                    int index1 = file_names.indexOf(filename_to_download1);
                    int max_chunks1 = max_chunks_todownload.get(index1);
                    List<Integer> each_proxy_count1 = new ArrayList<>();
                    int extra_count1 = 0;
                    int number_equal1 = ip_to_downloadfrom.get(index1).size();
                    long startTime1 = System.nanoTime();
                    int num_equal11 = max_chunks1 / number_equal1;
                    int rem1 = max_chunks1 % number_equal1;
                    int max_sequence1 = num_equal11;
                    int min1 = 0;
//                    for(int j=0;j<1000;j++) {
                    for (int i = 0; i < ip_to_downloadfrom.get(index1).size(); i++) {
                        System.out.println("min:" + min1);
                        System.out.println("max_sequence :" + max_sequence1);
                        Download_class_handling_proxy dc1 = new Download_class_handling_proxy(ip_to_downloadfrom.get(index1).get(i), pots_to_downloadfrom.get(index1).get(i), max_sequence1, filename_to_download1, index1, min1, ip_to_downloadfrom.get(index1),pots_to_downloadfrom.get(index1));
                        // Download_class dc = new Download_class(ip_to_downloadfrom.get(index).get(0),pots_to_downloadfrom.get(index).get(0),max_chunks,filename_to_download,index,min);
                        min1 = min1 + num_equal11;
                        max_sequence1 += num_equal11;
                        if (i == ip_to_downloadfrom.get(index1).size() - 2) {
                            max_sequence1 = max_sequence1 + rem1;
                        }
                        dc1.start();
                    }
                    // }
                    //  System.out.println("min outside loop:" + min);
                    //  System.out.println("max_sequence outside loop:" + max_sequence);
                    break;
                case 9:
                    //Download file 1000 times
                 //   System.out.println("Dowload 1000 times");
                  //  System.out.println("Case when one proxy in other server is down");
                    System.out.println("Enter the file to download : ");
                    String filename_to_download12 = sc1.nextLine();
                    System.out.println("Download");
                    int index12 = file_names.indexOf(filename_to_download12);
                    int max_chunks12 = max_chunks_todownload.get(index12);
                    List<Integer> each_proxy_count12 = new ArrayList<>();
                    int extra_count12 = 0;
                    int number_equal12 = ip_to_downloadfrom.get(index12).size();
                    long startTime12 = System.nanoTime();
                    int num_equal112 = max_chunks12 / number_equal12;
                    int rem12 = max_chunks12 % number_equal12;
                    int max_sequence12 = num_equal112;
                    int min12 = 0;
                    for(int j=0;j<1000;j++)
                    {
                        System.out.println(j);
                        for (int i = 0; i < ip_to_downloadfrom.get(index12).size(); i++)
                        {
                            System.out.println("min:" + min12);
                            System.out.println("max_sequence :" + max_sequence12);
                            Download_class dc1 = new Download_class(ip_to_downloadfrom.get(index12).get(i), pots_to_downloadfrom.get(index12).get(i), max_sequence12, filename_to_download12, index12, min12);
                            // Download_class dc = new Download_class(ip_to_downloadfrom.get(index).get(0),pots_to_downloadfrom.get(index).get(0),max_chunks,filename_to_download,index,min);
                            min12 = min12 + num_equal112;
                            max_sequence12 += num_equal112;
                            if (i == ip_to_downloadfrom.get(index12).size() - 2) {
                                max_sequence12 = max_sequence12 + rem12;
                            }
                            dc1.start();
                        }
                    }
                    break;
                case 10:
                    //Exit Client Application
                  //  System.out.println("EXIT");
                    flag = 0;
                    break;
            }
            if(flag == 0)
            {
                break;
            }
        }
        }
        }

