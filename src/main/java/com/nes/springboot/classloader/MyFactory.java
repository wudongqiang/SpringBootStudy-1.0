package com.nes.springboot.classloader;

import java.io.File;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author wdq
 * @date 2018-06-21-上午10:32
 */
public class MyFactory {

    private final static Map<String, LoadInfo> loadData = new ConcurrentHashMap<>();


    private static String class_path = "/home/wdq/mywork/project/SpringBootStudy-1.0/target/classes/";
    private static String class_name = "com.nes.springboot.classloader.MyManagerImpl";

    public static MyManager getMyManager() {
       String name = class_name;
        File file = new File(class_path + name.replaceAll("\\.", "/") + ".class");
        long time = file.lastModified();

        if (loadData.get(name) == null) {
            return init(name, time);
        } else if (loadData.get(name).getLoadTime() < time) {
            return update(loadData.get(name), name, time);
        } else {
            return loadData.get(name).getMyManager();
        }
    }

    private static MyManager update(LoadInfo info, String name, long time) {
        try {
            MyManager myManager = getMyManager(info.getClassLoader(), name);
            info.setLoadTime(time);
            info.setMyManager(myManager);
            loadData.put(name, info);
            return myManager;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static MyManager init(String name, long time) {
        MyClassLoader myClassLoader = new MyClassLoader(class_path);
        MyManager myManager = null;
        try {
            myManager = getMyManager(myClassLoader, name);
            LoadInfo info = new LoadInfo();
            info.setLoadTime(time);
            info.setMyManager(myManager);
            info.setClassLoader(myClassLoader);
            loadData.put(name, info);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return myManager;
    }

    private static MyManager getMyManager(MyClassLoader myClassLoader, String name) throws Exception {
        Class<?> aClass = myClassLoader.loadClass(name);
        return (MyManager) aClass.getConstructor(new Class[]{}).newInstance();
    }
}
