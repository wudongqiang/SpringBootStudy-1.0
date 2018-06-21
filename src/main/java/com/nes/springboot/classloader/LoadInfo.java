package com.nes.springboot.classloader;

/**
 * @author wdq
 * @date 2018-06-21-上午10:37
 */
public class LoadInfo {

    private MyManager myManager;
    private MyClassLoader classLoader;

    //加载类的时间
    private long loadTime;


    public MyManager getMyManager() {
        return myManager;
    }

    public void setMyManager(MyManager myManager) {
        this.myManager = myManager;
    }

    public long getLoadTime() {
        return loadTime;
    }

    public void setLoadTime(long loadTime) {
        this.loadTime = loadTime;
    }

    public MyClassLoader getClassLoader() {
        return classLoader;
    }

    public void setClassLoader(MyClassLoader classLoader) {
        this.classLoader = classLoader;
    }
}
