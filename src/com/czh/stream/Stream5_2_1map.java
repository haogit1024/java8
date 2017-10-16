package com.czh.stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 使用 flatMap 方法的效果是,各个数组并不是分别映射成一个流,而是映射成流的内容。所
 * 有使用 map(Arrays::stream) 时生成的单个流都被合并起来,即扁平化为一个流
 * flatmap 方法让你把一个流中的每个值都换成另一个流,然后把所有的流连接起来成为一个流
 */
public class Stream5_2_1map {
    public static void main(String[] args) {
        List<String> words = Arrays.asList("hello", "world");
        List<Integer> list = words.stream().map(String::length).collect(Collectors.toList());
        System.out.println(list);

        List<String[]> l = words.stream().map(word -> word.split("")).distinct().collect(Collectors.toList());

        List<Stream<String>> listOfStream = words.stream().map(word -> word.split("")).map(strings -> Arrays.stream(strings)).collect(Collectors.toList());
        List<Stream<String>> listOfStream1 = words.stream().map(word -> word.split("")).map(Arrays::stream).collect(Collectors.toList());
        for (Stream<String> ss : listOfStream1) {
            System.out.println(ss.collect(Collectors.toList()));
        }
        System.out.println("-------------------");

        List<String> uniqueCharacters = words.stream().map(word -> word.split("")).flatMap(Arrays::stream).distinct().collect(Collectors.toList());
        for (String c : uniqueCharacters) {
            System.out.println(c);
        }
    }
}
