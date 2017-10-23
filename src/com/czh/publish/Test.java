package com.czh.publish;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.czh.staticmethod.StaticMethod.test;

/**
 * 通过静态导入可以不同过类名调用静态方法
 */
public class Test {
    public static void main(String[] args) {
        test();
        System.out.println("----------------");
        Map<String, List<String>> map = new HashMap<>();
        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        map.put("char", list);
        list.add("d");
        list.add("e");
        list.add("f");
        list.add("g");
        List<String> list2 = map.get("char");
        for (String s : list2) {
            System.out.println(s);
        }
    }
}
