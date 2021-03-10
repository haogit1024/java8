package com.czh.chapter11;

import java.util.concurrent.*;

/**
 * @author chenzh
 * @date 2021/3/9
 */
public class Future_11 {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newCachedThreadPool();
        Future<Double> future = executor.submit(() -> 3.14);
        try {
            Double ret = future.get();
            System.out.println(ret);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}
