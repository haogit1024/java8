package com.czh.stream;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * findFirst找到第一个元素在并行上限制更多。如果你不关心返回的元素是哪个,请使用 findAny ,因为它在使用并行流时限制较少.
 */
public class Stream5_3_4Find {
    public static void main(String[] args) {
        List<Integer> someNumbers = Arrays.asList(1, 2, 3, 4, 5);
        Optional<Integer> firstSquareDivisibleByThree = someNumbers.stream().map( x -> x * x).filter(x -> x % 3 == 0).findFirst();
        System.out.println(firstSquareDivisibleByThree.get());
    }
}
