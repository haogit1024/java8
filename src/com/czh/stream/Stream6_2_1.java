package com.czh.stream;

import com.czh.publish.Dish;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Stream6_2_1 {
    public static void main(String[] args) {
        List<Dish> menu = Dish.getMenu();
        long howManyDish1 = menu.stream().collect(Collectors.counting());
        System.out.println("1 = " + howManyDish1);
        long howManyDish2 = menu.stream().count();
        System.out.println("2 = " + howManyDish2);
        System.out.println("---------------");
        Comparator<Dish> dishCaloriesComparator = Comparator.comparing(Dish::getCalories);
        Optional<Dish> mostCalorieDish = menu.stream().collect(Collectors.maxBy(dishCaloriesComparator));
        System.out.println(mostCalorieDish.get());
        System.out.println("------------");
        stream6_2_2();
        System.out.println("------------");
        stream6_2_3();
    }

    public static void stream6_2_2(){
        List<Dish> menu = Dish.getMenu();
        int totalCalories = menu.stream().collect(Collectors.summingInt(Dish::getCalories));
        System.out.println("totalColories = " + totalCalories);
    }

    private static void stream6_2_3(){
        List<Dish> menu = Dish.getMenu();
        String shortMenu = menu.stream().map(Dish::getName).collect(Collectors.joining());
        System.out.println(shortMenu);
        String shortMenu1 = menu.stream().map(Dish::getName).collect(Collectors.joining(", "));
        System.out.println(shortMenu1);
    }

    private static void stream6_2_4Test6_1(){
        List<Dish> menu = Dish.getMenu();
        String shortMenu1 = menu.stream().map(Dish::getName).collect(Collectors.joining());
        String shortMenu2 = menu.stream().map(Dish::getName).collect(Collectors.reducing( (d1, d2) -> d1 + d2 )).get();
        String shortMenu3 = menu.stream().collect(Collectors.reducing("", Dish::getName, (d1, d2) -> d1 + d2 ));
    }
}
