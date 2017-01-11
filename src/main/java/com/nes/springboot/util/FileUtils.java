package com.nes.springboot.util;

import com.google.gson.Gson;
import com.nes.springboot.domain.Resource;

import java.io.*;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by wdq on 17-1-9.
 */
public class FileUtils {

    public static String readFile(){

        InputStream inputStream = FileUtils.class.getResourceAsStream("/data.json");
        int len;
        byte[] buffer = new byte[1024];
        try {
            StringBuilder content = new StringBuilder();
            while ((len=inputStream.read(buffer))!=-1){
                content.append(new String(buffer,0,len));
            }
            return content.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(inputStream!=null){
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        //BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        //reader.r
        return null;
    }

    public static void writeFile(String content){
        OutputStream out = null;
        try {
            File file = new File(FileUtils.class.getResource("/data.json").toURI());
            out = new FileOutputStream(file);
            out.write(content.getBytes());
            out.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } finally {
            if(out!=null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /*public static void main(String[] args) {

        List<Resource> list = new ArrayList<>();
        list.add(new Resource(UUID.randomUUID().toString(),"xxxx","aaaa","8080"));
        list.add(new Resource(UUID.randomUUID().toString(),"111","22","8081"));
        list.add(new Resource(UUID.randomUUID().toString(),"cc","dd","8082"));
        Gson gson = new Gson();

        writeFile(gson.toJson(list));
        System.out.println(readFile());
    }*/

}
