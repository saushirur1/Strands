package com.gash.Client;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Fileupload_class1 extends Thread
{
    private String file_name;
    RPC_Implementations up1;
    String Ips[]={"10.0.30.1","10.0.30.2","10.0.30.3","10.0.30.3","10.0.30.4"};
    int ports[]={10000,10000,10000,10001,10000};

    public Fileupload_class1(String filename, RPC_Implementations up_old)
    {

        this.file_name = filename;
     this.up1 = up_old;
    }
    @Override
    public void run() {
            File f = new File(file_name);
            int partCounter = 1;
            int chunk_id_to_upload = 1;

            int sizeOfFiles = 1024;
            byte[] buffer = new byte[sizeOfFiles];
            List<byte[]> buffer_list = new ArrayList<>();
            String file_name = f.getName();

            try
            {
            FileInputStream fis = new FileInputStream(f);
            BufferedInputStream bis = new BufferedInputStream(fis);

            int bytesAmount = 0;
            int counter=0;
                while ((bytesAmount = bis.read(buffer)) > 0)
                {
                    System.out.println(bytesAmount);
                    if(partCounter == 33)
                    {
                        RPC_Implementations rpc_implementations=new RPC_Implementations(Ips[counter],ports[counter]);
                        //up1.upload_File(file_name,buffer_list,bytesAmount,chunk_id_to_upload,partCounter);
                        Uploadtreadclass uploadtreadclass=new Uploadtreadclass(rpc_implementations,file_name,buffer_list,bytesAmount,chunk_id_to_upload,partCounter);
                        uploadtreadclass.start();
                        chunk_id_to_upload+= 1;
                        partCounter = 1;
                        buffer_list.clear();
                    }
                    partCounter = partCounter+1;
                    buffer_list.add(buffer);
                    buffer = new byte[sizeOfFiles]; // Rectify
                    counter++;
                    if(counter>=Ips.length){
                        counter=0;
                        Thread.sleep(1000);
                    }
                }
                if(!buffer_list.isEmpty())
                {
                    up1.upload_File(file_name,buffer_list,bytesAmount,chunk_id_to_upload,partCounter);
                    buffer_list.clear();
                }
//                     partCounter = -1000;
//                     up1.upload_File(file_name,buffer_list,bytesAmount,chunk_id_to_upload,partCounter);
//                     partCounter = 1;
            }
            catch (IOException e)
            {

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
    }
}
