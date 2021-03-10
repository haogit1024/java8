package com.czh.chapter12;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

/**
 * @author chenzh
 * @date 2021/3/10
 */
public class DateTimeFormatter_12_2_2 {
    public static void main(String[] args) {
        LocalDateTime now = LocalDateTime.now();
        System.out.println(now.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        Map<String, String> map = ZoneId.SHORT_IDS;
        /*map.forEach((k, v) -> {
            System.out.println("k = " + k);
            System.out.println("v = " + v);
            System.out.println("---------");
        });*/
        ZoneId defaultId = TimeZone.getDefault().toZoneId();
        System.out.println(defaultId);
    }
}
