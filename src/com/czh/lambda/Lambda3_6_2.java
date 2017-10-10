package com.czh.lambda;

import com.czh.publish.Apple;

import java.util.function.Function;
import java.util.function.Supplier;

/**
 * 你可以把方法引用看作针对仅仅涉及单一方法的Lambda的语法糖,因为你表达同样的事情时要写的代码更少了。
 */
public class Lambda3_6_2 {
    public static void main(String[] args) {
        Supplier<Apple> s = new Supplier<Apple>() {
            @Override
            public Apple get() {
                return new Apple();
            }
        };

        Supplier<Apple> s1 = () -> new Apple();
        Supplier<Apple> s2 = Apple::new;

        System.out.println(s.get().getColor());
        System.out.println(s1.get().getColor());
        System.out.println(s2.get().getColor());
        System.out.println("-----------------");

        Function<Integer, Apple> f = new Function<Integer, Apple>() {
            @Override
            public Apple apply(Integer integer) {
                return new Apple(integer);
            }
        };

        Function<Integer, Apple> f1 = (Integer i) -> new Apple(i);
        Function<Integer, Apple> f2 = Apple::new;

        System.out.println(f.apply(50).getWeight());
        System.out.println(f1.apply(50).getWeight());
        System.out.println(f2.apply(50).getWeight());
        System.out.println("-----------------");

        
    }
}


