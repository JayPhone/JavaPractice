package com.jayphone.practice.common;

import java.util.Random;

/**
 * @author JayPhone
 * @description
 * @date 2023/8/10
 */
public class TestJson {
    public static void main(String[] args) {
//        String json = "{\"0\": 95.35, \"1\": 95.70, \"2\": 95.52, \"3\": 94.86, \"4\": 94.39, \"5\": 95.90, \"6\": 93.74, \"7\": 93.35, \"8\": 77.79, \"9\": 69.93, \"10\": 71.39, \"11\": 73.19, \"12\": 74.84, \"13\": 65.95, \"15\": 67.55, \"16\": 65.76, \"17\": 71.68, \"18\": 79.51, \"19\": 89.87, \"20\": 90.26, \"21\": 91.27, \"22\": 91.98, \"23\": 93.28}";
//        Map<String, Double> stringDoubleMap = JSON.parseObject(json, new TypeReference<Map<String, Double>>() {
//
//        });
//        System.out.println(stringDoubleMap);

//        String date = "2023-08-10 12:12:13";
//        String hour = date.substring(11, 13);
//        System.out.println(Integer.valueOf(hour));
//
//        String day = date.substring(8, 10);
//        System.out.println(Integer.valueOf(day));
//
//        String month = date.substring(5, 7);
//        System.out.println(Integer.valueOf(month));

        Random random = new Random();
        double randomValue1 = random.nextDouble() * 100;
        System.out.println(randomValue1);
    }
}
