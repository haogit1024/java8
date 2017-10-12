package com.czh.lambda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

/**
 * java.util.function.Function<T, R> 接口定义了一个叫作 apply 的方法,它接受一个泛型 T 的对象,并返回一个泛型 R 的对象
 */
public class Lambda3_4_3Function {
    public static void main(String[] args) {
        List<Integer> l = map(Arrays.asList("abc","chenzh","hello"), (String s) -> s.length());
        for (int i : l) {
            System.out.println(i);
        }
        System.out.println("-----------------");
        //使用方法的引用改进
        List<Integer> l2 = map(Arrays.asList("abc","chenzh","hello"), String::length);
        for (int i : l2) {
            System.out.println(i);
        }
    }

    private static<T,R> List<R> map(List<T> list, Function<T,R> f) {
        List<R> l = new ArrayList();
        for (T i : list) {
            l.add(f.apply(i));
        }
        return l;
    }
}
