package com.gash.Client;

import com.gash.Client.RPC_Implementations;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Fileupload_class extends Thread
{
    private String file_name;
    RPC_Implementations up1;
    public Fileupload_class(String filename, RPC_Implementations up_old)
    {
     this.file_name = filename;
     this.up1 = up_old;
    }
    @Override
    public void run() {
            long start =System.currentTimeMillis();
            File f = new File(file_name);
            int partCounter = 1;
            int chunk_id_to_upload = 1;
            int sizeOfFiles;
            if(f.length() > 1024 * 1024)
            {
                sizeOfFiles = 1024 * 1024 * 2;
            }
            else
            {
                sizeOfFiles = (int) f.length();
            }
            byte[] buffer = new byte[sizeOfFiles];
            List<byte[]> buffer_list = new ArrayList<>();
            String file_name = f.getName();

            try
            {
            FileInputStream fis = new FileInputStream(f);
            BufferedInputStream bis = new BufferedInputStream(fis);

            int bytesAmount = 0;
                while ((bytesAmount = bis.read(buffer)) > 0)
                {
                    System.out.println(bytesAmount);
                    if(partCounter == 33)
                    {
                        up1.upload_File(file_name,buffer_list,bytesAmount,chunk_id_to_upload,partCounter);
                        chunk_id_to_upload+= 1;
                        partCounter = 1;
                        buffer_list.clear();
                    }
                    partCounter = partCounter+1;
                    buffer_list.add(buffer);
                    buffer = new byte[sizeOfFiles]; // Rectify
                }
                if(!buffer_list.isEmpty())
                {
                    up1.upload_File(file_name,buffer_list,bytesAmount,chunk_id_to_upload,partCounter);
                    buffer_list.clear();
                }
                long end =System.currentTimeMillis();
                System.out.println(end - start);
                System.out.println("Upload is complete");
//                     partCounter = -1000;
//                     up1.upload_File(file_name,buffer_list,bytesAmount,chunk_id_to_upload,partCounter);
//                     partCounter = 1;
            }
            catch (IOException e)
            {

            }
    }
}
