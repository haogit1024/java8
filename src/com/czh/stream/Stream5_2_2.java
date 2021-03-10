package com.czh.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 给定两个数字列表,如何返回所有的数对呢?例如,给定列表[1, 2, 3]和列表[3, 4],应
 * 该返回[(1, 3), (1, 4), (2, 3), (2, 4), (3, 3), (3, 4)]。为简单起见,你可以用有两个元素的数组来代 15
 * 表数对。
 * 使用flatMap方法的效果是，各个数组并不是分别映射成一个流，而是映射成流的内容。所
 * 有使用map(Arrays::stream)时生成的单个流都被合并起来，即扁平化为一个流
 */
public class Stream5_2_2 {
    public static void main(String[] args) {
        // demo1
        String[] wordsArray = {"hello", "world"};
        List<String> words = Arrays.asList(wordsArray);
        Stream<String[]> s1 = words.stream().map(w -> w.split(""));
        Stream<Stream<String>> s2 = s1.map(Arrays::stream);
        // flatMap 将每个Arrays::stream生成的流连接起来，合并成一个流
        // : 一言以蔽之， flatmap方法让你把一个流中的每个值都换成另一个流，然后把所有的流连接起来成为一个流。
        Stream<String> s3 = s1.flatMap(Arrays::stream);
        // demo2
        List<Integer> numbers1 = Arrays.asList(1, 2, 3);
        List<Integer> numbers2 = Arrays.asList(3, 4);
        //1.传统的方法是用连个循环迭代获得对数
        List<int[]> l1 = new ArrayList<>();
        for (int i : numbers1) {
            for (int j : numbers2) {
                l1.add(new int[]{i,j});
            }
        }
        //2.使用连个map,不过这样会返回一个List<Stream<int[]>>
        List<Stream<int[]>> l2 = numbers1.stream().map(i -> numbers2.stream().map(j-> new int[]{i,j}) ).collect(Collectors.toList());

        //3.使用flatMap扁平化输出一个List<int[]>,这里flatMap主要是把上面List<Stream<int[]>>中的每一个Stream<int[]>都转化为一个流,
        //然后把所有的流连起来变成一个流,即把List<Stream<int[]>>中的每一个Stream<int[]>的int[]连起来变成一个Stream<int[]>
        // 相当于把List<Stream<int[]>>转成了Stream<int[]>
        //                                              numbers2.stream().map(j -> new int[]{i,j})返回一个Stream<int[]>,然后被flatMap连起来
        List<int[]> l3 = numbers1.stream().flatMap(i -> numbers2.stream().map(j -> new int[]{i,j})).collect(Collectors.toList());
        //验证
        int k = 1;
        Stream<int[]> s = numbers2.stream().map(j -> new int[]{k,j});
        for (int[] is : l3) {
            System.out.printf("%d,%d", is[0], is[1]);
            System.out.println();
        }
    }
}
