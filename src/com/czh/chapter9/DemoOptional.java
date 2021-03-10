package com.czh.chapter9;

import java.util.Optional;

/**
 * @author chenzh
 * @date 2021/3/4
 */
public class DemoOptional {
    public static void main(String[] args) {
        Optional<String> stringOptional = Optional.of("fuck");
        Optional<Integer> integerOptional = Optional.empty();
        System.out.println(stringOptional.isPresent());
        System.out.println(integerOptional.isPresent());

    }
}
