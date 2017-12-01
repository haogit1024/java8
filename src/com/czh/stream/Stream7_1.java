package com.czh.stream;

import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class Stream7_1 {

    public static void main(String[] args) {
        /**
         * 该实验得到并行版本耗时要比顺序版本多很多,原因主要有两个
         * 1.iterate生成的是装箱的对象，必须拆箱成数字才能求和
         * 2.我们很难把iterate分成多个独立块来并行执行,iterate函数每次都要依赖前一次应用的结果
         */
        long startTime = System.currentTimeMillis();
        long result = 0;
        for (long i = 1L; i <= 1000000L; i++) {
            result += i;
        }
        System.out.println(result);
        long endTime = System.currentTimeMillis();

        System.out.println("start: " + startTime + "  end: " + endTime + "  consuming: " + (endTime - startTime));

        System.out.println("----------------------------");

        long parallelStartTime = System.currentTimeMillis();
        long sum = Stream.iterate(1L, i -> i + 1).limit(1000000).parallel().reduce(0L, Long::sum);
        long parallelEndTime = System.currentTimeMillis();
        System.out.println(sum);
        System.out.println("parallel start: " + parallelStartTime + "  end: " + parallelEndTime + "  consuming: " + (parallelEndTime - parallelStartTime));

        System.out.println("----------------------------");

        /**
         * 当累加的数量比较大时,并行才会有优势,当累加的数量大于一定数量时并行才会比顺序高效
         */
        long rangedStartTime = System.currentTimeMillis();
        System.out.println(LongStream.rangeClosed(1, 100000000).reduce(0L, Long::sum));
        long rangedEndTime = System.currentTimeMillis();
        System.out.println("range start: " + rangedStartTime + "  end: " + rangedEndTime + "  consuming: " + (rangedEndTime - rangedStartTime));
        System.out.println("----------------------------");

        long parallelRangedStartTime = System.currentTimeMillis();
        System.out.println(LongStream.rangeClosed(1, 100000000).parallel().reduce(0L, Long::sum));
        long parallelRangedEndTime = System.currentTimeMillis();
        System.out.println("parallelRange start: " + parallelRangedStartTime + "  end: " + parallelRangedEndTime + "  consuming: " + (parallelRangedEndTime - parallelRangedStartTime));
    }
}
