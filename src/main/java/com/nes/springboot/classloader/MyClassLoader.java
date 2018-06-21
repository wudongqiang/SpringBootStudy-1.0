package com.nes.springboot.classloader;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;

/**
 * @author wdq
 * @date 2018-06-21-上午10:18
 */
public class MyClassLoader extends ClassLoader {

    //类路径
    private String classPath;

    public MyClassLoader(String classPath) {
        this.classPath = classPath;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {

        byte[] data = getClassData(name);
        return this.defineClass(name, data, 0, data.length);
    }

    private byte[] getClassData(String name) {
        try {
            name = name.replace(".","//");
            FileInputStream fs = new FileInputStream(new File(classPath + name+".class"));
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            int b = 0;
            byte[] buffer=new byte[2048];
            while ((b = fs.read()) != -1) {
                bos.write(buffer,0,b);
            }
            fs.close();
            return bos.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
