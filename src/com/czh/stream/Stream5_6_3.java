package com.czh.stream;

import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Stream5_6_3 {
    public static void main(String[] args) {
        Stream<Stream<int[]>> s1 = IntStream.rangeClosed(1, 100).boxed()
                     //这里会返回一个stream(int[]), map会把a映射成stream(int[])
                .map(a -> IntStream.rangeClosed(a, 100).filter(b -> Math.sqrt(a*a + b*b) % 1 == 0).mapToObj(b ->new int[]{a,b, (int) Math.sqrt(a*a + b*b)}) );
        for (Stream<int[]> s : s1.collect(Collectors.toList())) {
            for (int[] is : s.collect(Collectors.toList())) {
                for (int i : is) {
                    System.out.print(i + "\t");
                }
                System.out.println();
            }
        }
        System.out.println("------------");
        //使用flatMap优化代码
        Stream<int[]> s2 = IntStream.rangeClosed(1, 100).boxed()
                .flatMap(a -> IntStream.rangeClosed(a, 100).filter(b -> Math.sqrt(a*a + b*b) % 1 == 0).mapToObj(b ->new int[]{a,b, (int) Math.sqrt(a*a + b*b)}) );
        for (int[] is : s2.collect(Collectors.toList())) {
            for (int i : is) {
                System.out.print(i + "\t");
            }
            System.out.println();
        }
        System.out.println("---------");
        //上面需要开2次平方根,优化成一次平方根：先生成所有的三元数 (a*a, b*b, a*a+b*b) ,然后再筛选符合条件的
        Stream<int[]> s3 = IntStream.rangeClosed(0, 100).boxed().flatMap(a -> IntStream.rangeClosed(a, 100).mapToObj(b -> new int[]{a, b, a*b + b*b})).filter(t -> Math.sqrt(t[2]) % 1 == 0);
        s3.forEach(i -> System.out.println(i[0] + " " + i[1] + " " + i[2]));
        System.out.println("-----------");
        Stream<double[]> s4 = IntStream.rangeClosed(0, 100).boxed().flatMap(a -> IntStream.rangeClosed(a, 100).mapToObj(b -> new double[]{a, b, Math.sqrt(a*a + b*b)}).filter(t -> t[2] % 1 == 0) );
        s4.forEach(i -> System.out.println(i[0] + " " + i[1] + " " + i[2]));
    }
}
