package com.gash.Raft;

import java.io.*;
import java.util.ArrayList;
import java.util.Properties;
import java.net.InetAddress;

public class NetworkConfig {

    public class Ips{
        String ipaddr;
        String port;
    }

    private  static final int NOoFClusterServers =5;
    private  static final int NOoFDataCenters =3;
    private  static final int NOoFNetworkServers =12;

    public ArrayList<Ips> networkIps;
    public ArrayList<Ips> clusterIps;
    public ArrayList<Ips> datacenterIps;


    public ArrayList<String> clusterIpsInStr;
    public ArrayList<String> networkIpsInStr;
    public ArrayList<String> datacentreIpsInStr;

    /*Map<String,Ips> networkIps;
    Map<String,Ips> clusterIps;
    */
    void getMyclusterIps(){
        clusterIps=new ArrayList<Ips>();
        clusterIpsInStr= new ArrayList<String>();
        Properties prop = new Properties();
        try {
            String fileName = "conf/server.conf";


            InputStream is = new FileInputStream(fileName);

            prop.load(is);
            Ips ips;
            for(int i=1;i<NOoFClusterServers+1;i++){
                ips=new Ips();
                ips.ipaddr=prop.getProperty("server"+i+".ip");
                ips.port=prop.getProperty("server"+i+".port");
                clusterIps.add(ips);
                clusterIpsInStr.add(ips.ipaddr+"."+ips.port);
            }

           // System.out.println(clusterIps.get(0).ipaddr+":"+clusterIps.get(0).port);
            System.out.println(clusterIpsInStr.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void getAllNetworkIps(){
        networkIps=new ArrayList<Ips>();
        networkIpsInStr= new ArrayList<String>();
        Properties prop = new Properties();
        try {
            String fileName = "conf/all_server.conf";


            InputStream is = new FileInputStream(fileName);

            prop.load(is);
            Ips ips;
            for(int i=1;i<NOoFNetworkServers+1;i++){
                ips=new Ips();
                ips.ipaddr=prop.getProperty("server"+i+".ip");
                ips.port=prop.getProperty("server"+i+".port");
                networkIps.add(ips);
                networkIpsInStr.add(ips.ipaddr+"."+ips.port);
            }

          //  System.out.println(networkIps.get(0).ipaddr+":"+networkIps.get(0).port);
            System.out.println(networkIpsInStr.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    void getAllDataCenterIps(){
        datacenterIps=new ArrayList<Ips>();
        datacentreIpsInStr= new ArrayList<String>();
        Properties prop = new Properties();
        try {
            String fileName = "conf/data_cente.conf";


            InputStream is = new FileInputStream(fileName);

            prop.load(is);
            Ips ips;
            for(int i=1;i<NOoFDataCenters+1;i++){
                ips=new Ips();
                ips.ipaddr=prop.getProperty("server"+i+".ip");
                ips.port=prop.getProperty("server"+i+".port");
                datacenterIps.add(ips);
                datacentreIpsInStr.add(ips.ipaddr+"."+ips.port);
            }

            //  System.out.println(networkIps.get(0).ipaddr+":"+networkIps.get(0).port);
            System.out.println(datacentreIpsInStr.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        NetworkConfig networkConfig=new NetworkConfig();

        networkConfig.getMyclusterIps();
        networkConfig.getAllNetworkIps();

       // InetAddress.getLocalHost().getHostAddress();
        try {
            System.out.print(InetAddress.getLocalHost().getHostAddress());
        }catch (Exception e){

        }

    }
}
