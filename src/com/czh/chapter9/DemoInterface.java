package com.czh.chapter9;

/**
 * @author chenzh
 * @date 2021/3/2
 */
public interface DemoInterface {
    static void test() {
        System.out.println("fuck");
    }


    default void fuck() {
        System.out.println("fuck bitch");
    }
}
