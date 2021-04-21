package com.czh.chapter12;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;

/**
 * @author chenzh
 * @date 2021/3/10
 */
public class TemporalAdjuster_12_2_1 {
    public static void main(String[] args) {
        LocalDateTime localDateTime = LocalDateTime.now();
        LocalDateTime monday = localDateTime.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
        LocalDateTime sunday = localDateTime.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY));
        System.out.println(monday);
        System.out.println(sunday);
    }
}
