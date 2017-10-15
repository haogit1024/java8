package com.czh.stream;

import com.czh.publish.Apple;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

/**
 * yellow只会被filter不会被map
 * black不会被filter和map
 */
public class Stream4_4 {
    public static void main(String[] args) {
        BiFunction<Integer, String, Apple> bf = Apple::new;
        List<Apple> list = new ArrayList<>();
        list.add(bf.apply(300, "red"));
        list.add(bf.apply(400, "green"));
        list.add(bf.apply(100, "yellow"));
        list.add(bf.apply(200, "blue"));
        list.add(bf.apply(200, "black"));
        List<String> colors = list.stream().filter((a) ->{
            System.out.println("filter = "+a.getColor());
            return a.getWeight() > 150;
        }).map((a)->{
            System.out.println("map = " + a.getColor());
            return a.getColor();
        }).limit(3).collect(toList());

    }
}
