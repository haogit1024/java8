package com.czh.stream;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.BinaryOperator;

public class Stream5_4reduce {
    public static void main(String[] args) {
        List<Integer> someNumbers = Arrays.asList(1, 2, 3, 4, 5);
//        int sum =

        /**
         * 下面是演化步骤,内部类->lambda->使用Integer静态方法的lambda->使用方法的引用
         */
        Optional<Integer> max1 = someNumbers.stream().reduce(new BinaryOperator<Integer>() {
            @Override
            public Integer apply(Integer integer, Integer integer2) {
                return integer > integer2 ? integer : integer2;
            }
        });

        System.out.println(max1.get());
        System.out.println("---");

        Optional<Integer> max2 = someNumbers.stream().reduce((a, b) -> a > b ? a : b);
        System.out.println(max2.get());
        System.out.println("---");

        Optional<Integer> max3 = someNumbers.stream().reduce((a, b) -> Integer.max(a,b));
        System.out.println(max3.get());
        System.out.println("---");

        Optional<Integer> max4 = someNumbers.stream().reduce(Integer::max);
        System.out.println(max4.get());
        System.out.println("------------");

        Optional<Integer> min = someNumbers.stream().reduce(Integer::min);
        System.out.println(min.get());
    }
}
