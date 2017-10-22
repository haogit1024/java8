package com.czh.stream;

import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * 通过值,数组,文件,函数创建流
 * 文件来源是算法第四版三sum 提供的资料,这个path还有问题
 * http://algs4.cs.princeton.edu/14analysis/1Kints.txt
 */
public class Stream5_7 {
    public static void main(String[] args) {
        Stream<String> strStream = Stream.of("java8 ", "lambda ", "in ", "action");
        strStream.map(String::toUpperCase).forEach(System.out::println);
        int[] numbers = {2, 3, 5, 7, 11, 13};
        IntStream intStream = Arrays.stream(numbers);
        int sum = intStream.sum();
        System.out.println("sum = " + sum);

        long uniqueWords = 0;
        try(Stream<String> lines = Files.lines(Paths.get("/text1.txt"), Charset.defaultCharset())) {
            //读取一行->以空格为分割符分割成数组->Arrays.stream用数组创建流->flatMap将多个Stream<String>合成一个Stream<String>->distinct->count
            uniqueWords = lines.flatMap(line -> Arrays.stream(line.split(""))).distinct().count();
            System.out.println(uniqueWords);
        } catch (Exception e) {
//            e.printStackTrace();
        }
        System.out.println("-------");

        /**
         * iterate 方法接受一个初始值(在这里是 0 ),还有一个依次应用在每个产生的新值上的
         * Lambda( UnaryOperator<t> 类型)。这里,我们使用Lambda n -> n + 2 ,返回的是前一个元
         * 素加上2。因此, iterate 方法生成了一个所有正偶数的流:流的第一个元素是初始值 0
         */
        Stream.iterate(0, n -> n + 2).limit(10).forEach(System.out::println);
        System.out.println("---------");
        //生成斐波纳契元组序列前20个元素
        Stream<int[]> fibonacciArray = Stream.iterate(new int[]{0,1}, i -> new int[]{i[1], i[0] + i[1]}).limit(20);
        fibonacciArray.forEach(i -> System.out.println(i[0] + "  " + i[1]));
    }
}
