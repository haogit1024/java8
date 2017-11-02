package com.czh.stream;

import com.czh.publish.Dish;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Stream6_3 {
    static enum CaloricLevel { DIET, NORMAL, FAT }
    public static void main(String[] args) {
        List<Dish> menu = Dish.getMenu();
        Map<Dish.Type, List<Dish>> dishesByType = menu.stream().collect(Collectors.groupingBy(Dish::getType));
        Map<CaloricLevel, List<Dish>> dishesByCalories = menu.stream().collect(Collectors.groupingBy(dish -> {
            if (dish.getCalories() <= 400) return CaloricLevel.DIET;
            else if (dish.getCalories() <= 700) return CaloricLevel.NORMAL;
            else return CaloricLevel.FAT;
        }));
        for (Map.Entry<CaloricLevel, List<Dish>> entry : dishesByCalories.entrySet()) {
            System.out.println("-------"+entry.getKey()+"-----");
            for (Dish d : entry.getValue()) {
                System.out.println(d);
            }
            System.out.println();
        }
    }
}
