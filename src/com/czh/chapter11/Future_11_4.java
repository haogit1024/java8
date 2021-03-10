package com.czh.chapter11;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/**
 * @author chenzh
 * @date 2021/3/9
 */
public class Future_11_4 {
    private final Executor executor = Executors.newFixedThreadPool(100, r -> {
        Thread t = new Thread(r);
        t.setDaemon(true);
        return t;
    });
    public static void main(String[] args) {
        Thread t = new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("bitch");
        });
        t.setDaemon(true);
        t.start();
        System.out.println("fuck");
    }
}
