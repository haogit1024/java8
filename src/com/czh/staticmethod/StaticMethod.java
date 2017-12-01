package com.czh.staticmethod;

import java.util.stream.IntStream;

public class StaticMethod {
    public static void test(){
        System.out.println("this is test static method");
    }

    public static void main(String[] args) {
        IntStream.rangeClosed(1, 100).filter(i -> {
            System.out.println("******");
            return i %2 == 0;
        }).limit(3).forEach(System.out::println);
    }
}
