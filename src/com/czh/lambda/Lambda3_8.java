package com.czh.lambda;

import com.czh.publish.Apple;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;

public class Lambda3_8 {
    public static void main(String[] args) {
        lambda3_8_1();
        System.out.println("---------");
        lambda3_8_2();
        System.out.println("---------");
        lambda3_8_3_1();
        System.out.println("---------");
        lambda3_8_3_2();
        System.out.println("---------");
        lambda3_8_3_3();
        System.out.println("---------");
        lambda3_8_3_4();
    }

    //比较器复合
    private static void lambda3_8_1(){
        BiFunction<Integer, String, Apple> bf = Apple::new;
        List<Apple> list = new ArrayList<>();
        list.add(bf.apply(300, "red"));
        list.add(bf.apply(100, "green"));
        list.add(bf.apply(200, "blue"));
        list.add(bf.apply(200, "green"));

        //对比颜色还有点问题
        list.sort(Comparator.comparing(Apple::getWeight).reversed().thenComparing(Apple::getColor));

        for (Apple a : list) {
            System.out.println(a);
        }

    }

    //谓词复合
    private static void lambda3_8_2(){
        BiFunction<Integer, String, Apple> bf = Apple::new;
        List<Apple> list = new ArrayList<>();
        list.add(bf.apply(300, "red"));
        list.add(bf.apply(100, "red"));
        list.add(bf.apply(100, "green"));
        list.add(bf.apply(200, "blue"));
        list.add(bf.apply(200, "green"));
        Predicate<Apple> redApple = (a) -> a.getColor().equals("red");
        Predicate<Apple> redAndHeavyApple = redApple.and( a -> a.getWeight() > 150);
        Predicate<Apple> redAndHeavyOrGreenApple = redAndHeavyApple.or( a -> a.getColor().equals("green"));
        List<Apple> tagetList = filter(list, redAndHeavyOrGreenApple);
        for (Apple a : tagetList) {
            System.out.println(a);
        }
    }

    private static void lambda3_8_3_1(){
        //g(f(x))
        Function<Integer, Integer> f = x -> x + 1;
        Function<Integer, Integer> g = x -> x * 2;
        Function<Integer, Integer> h = f.andThen(g);
        System.out.println(h.apply(1));
    }

    private static void lambda3_8_3_2(){
        //f(g(x))
        Function<Integer, Integer> f = x -> x + 1;
        Function<Integer, Integer> g = x -> x * 2;
        Function<Integer, Integer> h = f.compose(g);
        System.out.println(h.apply(1));
    }

    private static void lambda3_8_3_3(){
        Function<String, String> ah = new Function<String, String>() {
            @Override
            public String apply(String s) {
                return Letter.addHeader(s);
            }
        };

        Function<String, String> ah1 = (s) -> Letter.addHeader(s);
        Function<String, String> addHeader = Letter::addHeader;
        Function<String, String> transformationPipeline
                = addHeader.andThen(Letter::checkSpelling)
                .andThen(Letter::addFooter);
        System.out.println(transformationPipeline.apply("labda"));
    }

    private static void lambda3_8_3_4(){
        Function<String, String> addHeader = Letter::addHeader;
        Function<String, String> transformationPipeline
                = addHeader.andThen(Letter::addFooter);
        System.out.println(transformationPipeline.apply("labda"));
    }

    private static<T> List<T> filter(List<T> list, Predicate<T> p){
        List<T> l = new ArrayList<>();
        for (T i : list) {
            if (p.test(i)) {
                l.add(i);
            }
        }
        return l;
    }

}

class Letter{
    public static String addHeader(String text){
        return "From Raoul, Mario and Alan: " + text;
    }
    public static String addFooter(String text){
        return text + " Kind regards";
    }
    public static String checkSpelling(String text){
        return text.replaceAll("labda", "lambda");
    }
}
