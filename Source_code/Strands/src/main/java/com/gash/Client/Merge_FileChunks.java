package com.gash.Client;

import com.google.common.io.Files;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Merge_FileChunks
{
    List<String> file_list = new ArrayList<String>();
    File merging_file = new File("/Users/saurabhshirur/desktop/New_merged_file_afterproxy_newmerged");
    String file_merged = "";
    Merge_FileChunks(String File_path,String filename)
    {
        File folder = new File(File_path);
        File[] listOfFiles = folder.listFiles();
        for (int i = 0; i < listOfFiles.length; i++)
        {
            this.file_merged = filename;
            if (listOfFiles[i].isFile())
            {
                if(listOfFiles[i].getName().startsWith(filename))
                {
                      String to_add_tolist = File_path +"/" + listOfFiles[i].getName();
                      file_list.add(to_add_tolist);
                }
            }
            else if (listOfFiles[i].isDirectory())
            {
                System.out.println(listOfFiles[i].getName());
            }
        }
        Collections.sort(file_list);
    }
    public void mergeFiles() throws IOException
    {
        long startTime = System.nanoTime();
        FileOutputStream fos = new FileOutputStream(merging_file + file_merged);
        BufferedOutputStream mergingStream = new BufferedOutputStream(fos);
            for (String f : file_list) {
               Files.copy(new File(f), mergingStream);
            }
            fos.close();
            mergingStream.close();
        long end = System.nanoTime();
        long duration = (end- startTime);
        System.out.println("Merging is done,Safe to open file now , time needed : " + duration);
        }
    }

