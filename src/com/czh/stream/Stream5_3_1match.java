package com.czh.stream;

import com.czh.publish.Dish;

import java.util.List;

/**
 * anyMatch 匹配一个
 * allMatch 匹配所有
 * noneMatch 匹配所有
 */
public class Stream5_3_1match {
    public static void main(String[] args) {
        List<Dish> menu = Dish.getMenu();
        if (menu.stream().anyMatch(d -> d.isVegetarian())) {
            System.out.println("The menu is (somewhat) vegetarian friendly!!");
        }

        if (menu.stream().anyMatch(Dish::isVegetarian)) {
            System.out.println("The menu is (somewhat) vegetarian friendly!!");
        }
        System.out.println("-------------------");

        boolean isHealthy = menu.stream().allMatch(d -> d.getCalories() < 1000);
        System.out.println(isHealthy);

        System.out.println("-------------------");
        boolean isNonHealthy = menu.stream().noneMatch(d -> d.getCalories() > 1000);
        System.out.println(isNonHealthy);
    }
}
