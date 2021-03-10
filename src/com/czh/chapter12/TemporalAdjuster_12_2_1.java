package com.czh.chapter12;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;

/**
 * @author chenzh
 * @date 2021/3/10
 */
public class TemporalAdjuster_12_2_1 {
    public static void main(String[] args) {
        LocalDate date = LocalDate.now();
        System.out.println(date);
        System.out.println(date.with(TemporalAdjusters.nextOrSame(DayOfWeek.MONDAY)));
        System.out.println(date.with(TemporalAdjusters.lastDayOfMonth()));
    }
}
