package com.jayphone.practice.common;

/**
 * @author JayPhone
 * @description
 * @date 2023/9/18
 */
public class TestNumber {
    public static void main(String[] args) {
        for (int i = 101; i <= 200; i++) {
            int num = i;
            boolean isPrime = true;
            for (int j = 1; j <= i; j++) {
                if (num % j == 0 && j != 1 && j != i) {
                    isPrime = false;
                    break;
                }
            }

            if (isPrime) {
                System.out.println(num + "是素数");
            }
        }
    }
}
