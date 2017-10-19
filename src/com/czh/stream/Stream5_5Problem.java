package com.czh.stream;

import com.czh.publish.Trader;
import com.czh.publish.Transaction;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
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


        problem1(transactions);
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
}
