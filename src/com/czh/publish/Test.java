package com.czh.publish;

import static com.czh.staticmethod.StaticMethod.test;

/**
 * 通过静态导入可以不同过类名调用静态方法
 */
public class Test {
    public static void main(String[] args) {
        test();
    }
}
