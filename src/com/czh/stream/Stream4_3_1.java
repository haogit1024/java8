package com.czh.stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class Stream4_3_1 {
    public static void main(String[] args) {
        List<String> title = Arrays.asList("Java8", "In", "Action");
        Stream<String> s = title.stream();
        s.forEach(System.out::println);
        System.out.println("---------");
        //这里会被提示流已操作或已关闭
        s.forEach(System.out::println);
    }
}
