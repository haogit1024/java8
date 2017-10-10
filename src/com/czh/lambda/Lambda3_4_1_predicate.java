package com.czh.lambda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/**
 * java.util.function.Predicate<T> 接口定义了一个名叫 test 的抽象方法,它接受泛型T 对象,并返回一个 boolean
 */
public class Lambda3_4_1_predicate {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("a","","b","","c");
        Predicate<String> noEmptyString = (str) -> !str.isEmpty();
        List<String> l = filter(list, noEmptyString);
        for (String s : l) {
            System.out.println(s);
        }
    }

    public static<T> List<T> filter(List<T> list, Predicate<T> p){
        List<T> l = new ArrayList<>();
        for (T i : list) {
            if (p.test(i)) {
                l.add(i);
            }
        }
        return l;
    }

}
