package com.czh.chapter12;

import java.time.Duration;
import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.TemporalField;

/**
 * @author chenzh
 * @date 2021/3/10
 */
public class Duration_12_1_5 {
    public static void main(String[] args) {
        Duration threeMinutes = Duration.ofMinutes(1);
        System.out.println(threeMinutes.get(ChronoUnit.SECONDS));
        System.out.println(threeMinutes.getSeconds());
        Period tenDays = Period.ofDays(10);
        System.out.println(tenDays.getDays());
    }
}
