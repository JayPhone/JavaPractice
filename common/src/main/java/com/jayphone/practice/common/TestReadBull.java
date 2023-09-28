package com.jayphone.practice.common;

import java.util.Random;

/**
 * @author JayPhone
 * @description
 * @date 2023/9/11
 */
public class TestReadBull {
    public static void main(String[] args) {
        int[] redPackages = new int[5];
        redPackages[0] = 9;
        redPackages[1] = 666;
        redPackages[2] = 188;
        redPackages[3] = 520;
        redPackages[4] = 99999;
        Random r = new Random();

        int[] flag = new int[5];
        flag[0] = 0;
        flag[1] = 0;
        flag[2] = 0;
        flag[3] = 0;
        flag[4] = 0;

        for (int i = 0; i < redPackages.length; i++) {
            int index;
            do {
                index = r.nextInt(5);
            }
            while (flag[index] == 1);
            System.out.println("恭喜你抽到" + redPackages[index] + "元");
        }
    }
}
