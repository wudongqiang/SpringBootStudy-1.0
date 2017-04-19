package com.nes.springboot;

import org.junit.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * Created by wdq on 17-2-10.
 */
public class ComplatebleFutrue {

    @Test
    public void testFuture() {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            for (int i = 0; i < 10; i++) {
                System.out.println("i=" + i);
            }
            return "hello";
        });

        try {
            System.out.println(future.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }


    }
}
