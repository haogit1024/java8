package com.czh.lambda;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

/**
 * java.util.function.Consumer<T> 定义了一个名叫 accept 的抽象方法,它接受泛型 T的对象,没有返回( void )
 */
public class Lambda3_4_2Consumer {
    public static void main(String[] args) {
        forErch(Arrays.asList(1,2,3,4,5,6), (Integer i) -> System.out.println(i));
    }

    public static<T> void forErch(List<T> list, Consumer<T> c) {
        for (T i : list) {
            c.accept(i);
        }
    }
}
