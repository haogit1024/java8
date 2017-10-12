package com.czh.lambda;

import java.util.function.DoubleFunction;

public class Lambda3_9math {
    public static void main(String[] args) {
        double d = integrate( (x) -> x + 10, 3.0, 7.0 );
        System.out.println(d);
        System.out.println("----------");
        //这里C是包含静态方法f的一个类。理念就是把f背后的代码传给integrate方法. C.f(x)返回x + 10,所以已上面的lambda一样,idea会提示可以使用方法的引用来代替C.f(x)
        //这里(x) -> C.f(x)任然是一个lambda表达式，表达式的主体是 C.f(x)
        double d2 = integrate( (x) -> C.f(x), 3.0, 7.0);
        System.out.println(d2);
        System.out.println("----------");
        double d3 = integrate(C::f, 3.0, 7.0);
        System.out.println(d3);
    }

    //求f(x) = x + 10 的微分
    private static Double integrate(DoubleFunction<Double> df , Double d1, Double d2) {
        return ( df.apply(d1) + df.apply(d2) ) * (d2 - d1) / 2;
    }
}

class C {
    public static Double f(Double x){
        return x + 10;
    }
}