package com.czh.stream;

import java.util.stream.IntStream;

public class Stream5_6_2 {
    public static void main(String[] args) {
        IntStream evenNumbers = IntStream.rangeClosed(1, 100).filter(n -> n % 2 == 0);
        System.out.println(evenNumbers.count());
    }
}
