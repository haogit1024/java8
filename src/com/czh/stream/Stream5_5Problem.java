package com.czh.stream;

import com.czh.publish.Trader;
import com.czh.publish.Transaction;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Stream5_5Problem {
    public static void main(String[] args) {
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario","Milan");
        Trader alan = new Trader("Alan","Cambridge");
        Trader brian = new Trader("Brian","Cambridge");
        List<Transaction> transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );

//
//        problem1(transactions);
//        System.out.println("--------");
//        problem2(transactions);
//        System.out.println("--------");
//        problem3(transactions);
//        System.out.println("--------");
//        problem4(transactions);
//        System.out.println("--------");
        problem5(transactions);
//        System.out.println("--------");
//        problem6(transactions);
//        System.out.println("--------");
//        problem7(transactions);
//        System.out.println("--------");
//        problem8(transactions);
    }

    //(1) 找出2011年发生的所有交易，并按交易额排序（从低到高）
    private static void problem1(List<Transaction> transactions){
        List<Transaction> lt2 = transactions.stream().sorted(new Comparator<Transaction>() {
            @Override
            public int compare(Transaction o1, Transaction o2) {
                int res2 = o1.getValue() > o2.getValue() ? 1 : -1;
                return res2;
            }
        }).collect(Collectors.toList());
        for (Transaction ts : lt2) {
            System.out.println(ts.getValue());
        }
        System.out.println("--------------------");
        List<Transaction> lt1 = transactions.stream().filter(t -> t.getYear() == 2011).sorted(new Comparator<Transaction>() {
            @Override
            public int compare(Transaction o1, Transaction o2) {
                int res1 = Integer.valueOf(o1.getValue()).compareTo(Integer.valueOf(o2.getValue()));
                return res1;
            }
        }).collect(Collectors.toList());
        for (Transaction ts : lt1) {
            System.out.println(ts.getValue());
        }
        System.out.println("-------------");
        List<Transaction> lt3 = transactions.stream().filter(t -> t.getYear() == 2011).sorted(Comparator.comparing(t -> t.getValue())).collect(Collectors.toList());
        for (Transaction ts : lt3) {
            System.out.println(ts.getValue());
        }
        System.out.println("-----------");
        List<Transaction> lt4 = transactions.stream().filter(t -> t.getYear() == 2011).sorted(Comparator.comparing(Transaction::getValue)).collect(Collectors.toList());
        for (Transaction ts : lt4) {
            System.out.println(ts.getValue());
        }
    }

    //(2) 交易员都在哪些不同的城市工作过?
    private static void problem2(List<Transaction> transactions){
        List<String> cites = transactions.stream().map(t -> t.getTrader().getCity()).distinct().collect(Collectors.toList());
        for (String s : cites) {
            System.out.println(s);
        }
    }

    //(3) 查找所有来自于剑桥的交易员,并按姓名排序。
    private static void problem3(List<Transaction> transactions){
        List<Trader> traders = transactions.stream().map(Transaction::getTrader).distinct().filter(t -> t.getCity().equals("Cambridge")).sorted(Comparator.comparing(Trader::getName)).collect(Collectors.toList());
        for (Trader t : traders) {
            System.out.println(t.getName());
        }
    }

    //(4) 返回所有交易员的姓名字符串,按字母顺序排序。
    private static void problem4(List<Transaction> transactions){
        List<String> names = transactions.stream().map(t -> t.getTrader().getName()).distinct().sorted(Comparator.comparing(d -> d)).collect(Collectors.toList());
        for (String n : names) {
            System.out.println(n);
        }
        System.out.println("----");

        String names1 = transactions.stream().map(t -> t.getTrader().getName()).distinct().sorted().reduce("", (a, b) -> a + " " + b + " ");
        System.out.println(names1);
        System.out.println("----");
        //使用joining优化
        String names2 = transactions.stream().map(t -> t.getTrader().getName()).distinct().sorted().collect(Collectors.joining());
        System.out.println(names2);
    }

    //(5) 有没有交易员是在米兰工作的?  Milan
    private static void problem5(List<Transaction> transactions){
        Optional<Trader> haveMilan = transactions.stream().map(Transaction::getTrader).filter(t -> t.getCity().equals("Milan")).findAny();
        boolean flag = haveMilan.isPresent();
        System.out.println(flag);
        System.out.println("---");
        //修改错误
        boolean flag1 = transactions.stream().map(Transaction::getTrader).anyMatch(t -> "Milan".equals(t.getCity()));
        System.out.println(flag1);
        System.out.println("---");
        boolean flag2 = transactions.stream().anyMatch(t -> "Milan".equals(t.getTrader().getCity()));
        System.out.println(flag1);
    }

    //(6) 打印生活在剑桥的交易员的所有交易额。
    private static void problem6(List<Transaction> transactions){
        int sumValue = transactions.stream().filter(t -> t.getTrader().getCity().equals("Cambridge")).map(Transaction::getValue).reduce(0, Integer::sum);
        System.out.println(sumValue);
    }

    //(7) 所有交易中,最高的交易额是多少?
    private static void problem7(List<Transaction> transactions){
        int maxValue = transactions.stream().map(Transaction::getValue).reduce(Integer::max).get();
        System.out.println(maxValue);
    }

    //(8) 找到交易额最小的交易
    private static void problem8(List<Transaction> transactions){
        Transaction t = transactions.stream().reduce((t1, t2) -> t1.getValue() < t2.getValue() ? t1 : t2 ).get();
        System.out.println(t);
    }

}
