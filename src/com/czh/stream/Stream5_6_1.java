package com.czh.stream;

import com.czh.publish.Dish;

import java.util.List;
import java.util.OptionalInt;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Stream5_6_1 {
    public static void main(String[] args) {
        List<Dish> menu = Dish.getMenu();
        int calories = menu.stream()            //返回Stream<Dish>
                .mapToInt(Dish::getCalories)    //返回一个IntStream
                .sum();
        System.out.println(calories);
        System.out.println();
        IntStream intStream = menu.stream().mapToInt(Dish::getCalories);
        Stream<Integer> stream = intStream.boxed();  //特化流转对象流
        stream.forEach(System.out::println);
        System.out.println();

        OptionalInt maxCalories = menu.stream().mapToInt(Dish::getCalories).max();
        int max = maxCalories.orElse(1);
        System.out.println("max = " + max);
    }
}
