package com.czh.optional;

import java.util.Optional;
import java.util.Properties;

public class Test {
    public static void main(String[] args) {
        Properties props = new Properties();
        props.setProperty("a", "5");
        props.setProperty("b", "true");
        props.setProperty("c", "-3");
        System.out.println(readDuration(props,"a"));
        System.out.println(readDuration(props,"b"));
        System.out.println(readDuration(props,"c"));
    }

    public static int readDuration(Properties props, String name) {
//        return Optional.ofNullable(props.get(name)).flatMap(OptionalUtil::stringToInt).map(i -> i > 0).orElse(0);
        return Optional.ofNullable(props.getProperty(name))
                .flatMap(OptionalUtil::stringToInt)
                .filter(i -> i > 0)
                .orElse(0);
    }
}
