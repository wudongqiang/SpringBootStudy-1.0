package com.nes.springboot;

/**
 * 死锁案例
 */
public class DealLockTest {

    private static Object lock1 = new Object();
    private static Object lock2 = new Object();

    public static void main(String[] args) {

        new Thread(()->{
            synchronized(lock1) {
                try {Thread.sleep(1000);}catch(Exception e) {}
                synchronized(lock2) {
                    System.out.println("Thread1 over");
                }
            }
        }) .start();
        new Thread(()->{
            synchronized(lock2) {
                try {Thread.sleep(1000);}catch(Exception e) {}
                synchronized(lock1) {
                    System.out.println("Thread2 over");
                }
            }
        }) .start();
    }
}
