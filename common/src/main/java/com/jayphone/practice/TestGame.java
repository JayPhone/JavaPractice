package com.jayphone.practice;

import java.util.Random;
import java.util.Scanner;

/**
 * @author JayPhone
 * @description
 * @date 2023/7/3
 */
public class TestGame {


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean restart = true;
        while (restart) {
            System.out.println("游戏开始");
            game();
            System.out.println("游戏结束");
            System.out.println("是否重新开始游戏，y/n");
            if ("n".equals(scanner.next())) {
                restart = false;
            }
        }

    }

    private static void game() {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        int count = random.nextInt(100);
        boolean result = false;
        while (!result) {
            int input = scanner.nextInt();
            if (input < count) {
                System.out.println("数字过小");
            } else if (input > count) {
                System.out.println("数字过大");
            } else {
                System.out.println("数字正确");
                result = true;
            }
        }
    }
}
