package com.jayphone.practice.test;

import java.util.Random;
import java.util.Scanner;

/**
 * @author JayPhone
 * @description
 * @date 2023/7/10
 */
public class TestRandom {
    public static void main(String[] args) {
        int count = 5;
        int[] process = new int[count];
        Scanner scanner = new Scanner(System.in);

        int index = 0;
        while (index < count) {
            System.out.println("请输入第" + (index + 1) + "位开发人员进度");
            index++;
        }
        Random random = new Random();
    }
}
