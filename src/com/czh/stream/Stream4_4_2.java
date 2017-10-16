package com.czh.stream;

import com.czh.publish.Apple;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;

/**
 * 实验使用Apple代替Dish
 */
public class Stream4_4_2 {
    public static void main(String[] args) {
        BiFunction<Integer, String, Apple> bf = Apple::new;
        List<Apple> list = new ArrayList<>();
        list.add(bf.apply(300, "red"));
        list.add(bf.apply(400, "green"));
        list.add(bf.apply(100, "yellow"));
        list.add(bf.apply(200, "blue"));
        list.add(bf.apply(200, "black"));

        list.stream().forEach(apple -> System.out.println(apple));
        System.out.println("----------------");
        list.stream().forEach(System.out::println);
    }
}
