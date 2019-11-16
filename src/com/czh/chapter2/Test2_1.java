package com.czh.chapter2;

import com.czh.publish.Apple;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;

/**
 *
 */
public class Test2_1 {
    private static void printAppleInfo(List<Apple> list, AppleFormatter appleFormatter) {
        for (Apple a : list) {
            String outString = appleFormatter.accept(a);
            System.out.println(outString);
        }
    }

    public static void main(String[] args) {
        // BiFunction<Integer, String, Apple> bf = Apple::new;
        BiFunction<Integer, String, Apple> bf = new BiFunction<Integer, String, Apple>() {
            @Override
            public Apple apply(Integer integer, String s) {
                return new Apple(integer, s);
            }
        };
        List<Apple> list = new ArrayList<>();
        list.add(bf.apply(300, "red"));
        list.add(bf.apply(400, "green"));
        list.add(bf.apply(100, "yellow"));
        list.add(bf.apply(200, "blue"));
        list.add(bf.apply(200, "black"));
        printAppleInfo(list, new AppleFancyFormatter());
        System.out.println("----------");
        printAppleInfo(list, new AppleWeghtFormatter());

    }
}


interface AppleFormatter{
    String accept(Apple apple);
}

class AppleFancyFormatter implements AppleFormatter {
    @Override
    public String accept(Apple apple) {
        String wegitInfo = apple.getWeight() > 150 ? "重" : "轻";
        return "A " + wegitInfo + " " + apple.getColor() + " apple";
    }
}

class AppleWeghtFormatter implements AppleFormatter{
    @Override
    public String accept(Apple apple) {
        int weight = apple.getWeight();
        return "a apple of " + weight + " apple";
    }
}
