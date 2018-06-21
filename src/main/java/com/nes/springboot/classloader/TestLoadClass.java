package com.nes.springboot.classloader;

/**
 * @author wdq
 * @date 2018-06-21-上午11:09
 */
public class TestLoadClass implements Runnable{

    public static void main(String[] args) {
        new Thread(new TestLoadClass()).start();
    }

    @Override
    public void run() {
        while (true){
            MyFactory.getMyManager().show();
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
