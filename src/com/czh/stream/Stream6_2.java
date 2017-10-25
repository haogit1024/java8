package com.czh.stream;

import com.czh.publish.Dish;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Stream6_2 {
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
        System.out.println("------------");
        stream6_2_4();
        System.out.println("------------");
        stream6_2_4Test();
    }

    //汇总
    private static void stream6_2_2(){
        List<Dish> menu = Dish.getMenu();
        int totalCalories = menu.stream().collect(Collectors.summingInt(Dish::getCalories));
        System.out.println("totalColories = " + totalCalories);
        double avgCalories = menu.stream().collect(Collectors.averagingDouble(Dish::getCalories));
        System.out.println("avgCalories = " + avgCalories);
        IntSummaryStatistics statistics = menu.stream().collect(Collectors.summarizingInt(Dish::getCalories));
        System.out.println("statistics = " + statistics);
    }

    //链接字符串
    private static void stream6_2_3(){
        List<Dish> menu = Dish.getMenu();
        String shortName1 = menu.stream().map(Dish::getName).collect(Collectors.joining());
        System.out.println("shortName1 = " +shortName1);
        String shortName2 = menu.stream().map(Dish::getName).collect(Collectors.joining(", "));
        System.out.println("shortName2 = " +shortName2);
    }

    //广义的归约和汇总
    private static void stream6_2_4(){
        List<Dish> menu = Dish.getMenu();
        int totalCalories1 = menu.stream().collect(Collectors.reducing(0, Dish::getCalories, (i, j) -> Integer.sum(i, j)));
        System.out.println("totalCalories1 = " + totalCalories1);
        int totalCalories2 = menu.stream().collect(Collectors.reducing(0, Dish::getCalories, Integer::sum));
        System.out.println("totalCalories2 = " + totalCalories2);
        Optional<Dish> mostCaloriesDish = menu.stream().reduce((d1, d2) -> d1.getCalories() > d2.getCalories() ? d1 : d2);
        System.out.println( "mostColoriesDish = " + mostCaloriesDish.get());
    }

    private static void stream6_2_4Test(){
        Stream<Integer> stream = Arrays.asList(1, 2, 3, 4, 5, 6).stream();
        List<Integer> intList = stream.reduce(new ArrayList<Integer>(), (List<Integer> l, Integer i) -> {
            System.out.println("i = " + i);
            l.add(i);
            return l;
        },(List<Integer> l1, List<Integer> l2) -> {

            System.out.println("11111111");

            l1.addAll(l2);
            return l1; });

    }
}
