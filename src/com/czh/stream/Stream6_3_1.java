package com.czh.stream;

import com.czh.publish.Dish;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class Stream6_3_1 {
    enum CaloricLevel { DIET, NORMAL, FAT }
    public static void main(String[] args) {
        List<Dish> menu = Dish.getMenu();
        Map<Dish.Type, Map<CaloricLevel, List<Dish>>> dishesByCaloricLevel = menu.stream().collect(Collectors.groupingBy(Dish::getType, Collectors.groupingBy(dish -> {
            if (dish.getCalories() <= 400) return CaloricLevel.DIET;
            else if (dish.getCalories() <= 700) return CaloricLevel.NORMAL;
            else return CaloricLevel.FAT;
        })));

        for (Map.Entry<Dish.Type, Map<CaloricLevel, List<Dish>>> entry : dishesByCaloricLevel.entrySet()) {
            System.out.println("type = " + entry.getKey());
            for (Map.Entry<CaloricLevel, List<Dish>> e : entry.getValue().entrySet()) {
                System.out.println("caloricLevel = " + e.getKey());
                for (Dish d : e.getValue()) {
                    System.out.print("dish name = " + d + "\t");
                }
                System.out.println();
            }
        }
        System.out.println("---------------");
        stream6_3_2();
    }

    private static void stream6_3_2(){
        List<Dish> menu = Dish.getMenu();
        Map<Dish.Type, Long> typesCount = menu.stream().collect(Collectors.groupingBy(Dish::getType, Collectors.counting()));
        for (Map.Entry<Dish.Type, Long> entry : typesCount.entrySet()) {
            System.out.println("type = " + entry.getKey() + "   long = " + entry.getValue());
        }
        Map<Dish.Type, List<Dish>> typesList = menu.stream().collect(Collectors.groupingBy(Dish::getType, Collectors.toList()));
        for (Map.Entry<Dish.Type, List<Dish>> entry : typesList.entrySet()) {
            System.out.println("type = " + entry.getKey() );
            entry.getValue().stream().forEach(dish -> System.out.print(dish + "  "));
            System.out.println();
        }

        Map<Dish.Type, Dish> mostCaloricByType = menu.stream().collect(Collectors.groupingBy(Dish::getType, Collectors.collectingAndThen(Collectors.maxBy(Comparator.comparingInt(Dish::getCalories)), Optional::get)));

    }
}
