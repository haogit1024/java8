package com.czh.stream;

import com.czh.publish.Dish;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Stream6_4Partitioning {
    public static void main(String[] args) {
        List<Dish> menu = Dish.getMenu();
        Map<Boolean, List<Dish>> partitionedMenu = menu.stream().collect(Collectors.partitioningBy(Dish::isVegetarian));
//        for (Map.Entry<Boolean, List<Dish>> entry : partitionedMenu.entrySet()) {
//            System.out.println("key = " + entry.getKey());
//            entry.getValue().stream().forEach(System.out::println);
//            System.out.println("--------------------");
//        }
        List<Dish> vegetarianDishes = partitionedMenu.get(true);
        vegetarianDishes.forEach(System.out::println);
        System.out.println("------------");
        List<Dish> vegetarianDishes1 = menu.stream().filter(Dish::isVegetarian).collect(Collectors.toList());
        vegetarianDishes1.forEach(System.out::println);
        Map<Boolean, Map<Dish.Type, List<Dish>>> vegetarianDishesByType = menu.stream().
                collect(Collectors.partitioningBy(Dish::isVegetarian, Collectors.groupingBy(Dish::getType)));

    }
}
