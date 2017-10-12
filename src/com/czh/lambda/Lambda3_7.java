package com.czh.lambda;

import com.czh.publish.Apple;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;

import static java.util.Comparator.comparing;

public class Lambda3_7 {
    public static void main(String[] args) {
        List<Apple> list = new ArrayList<>();
        list.sort(new Comparator<Apple>() {
            @Override
            public int compare(Apple o1, Apple o2) {
                return o1.getWeight().compareTo(o2.getWeight());
            }
        });

        list.sort((Apple a1, Apple a2) -> a1.getWeight().compareTo(a2.getWeight()));
        list.sort((a1,a2) -> a1.getWeight().compareTo(a2.getWeight()));
        list.sort(comparing(new Function<Apple, Integer>() {

            @Override
            public Integer apply(Apple apple) {
                return apple.getWeight();
            }
        }));
        list.sort(comparing((Apple a) -> a.getWeight()));
        //通过静态导入可以不同过类名调用静态方法
        list.sort(comparing(Apple::getWeight));
    }

}
