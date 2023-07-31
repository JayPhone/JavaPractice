package com.jayphone.practice.test;

import java.util.Scanner;

/**
 * @author JayPhone
 * @description
 * @date 2023/7/7
 */
public class TestJudge {
    public static void main(String[] args) {
        System.out.println("陈灼强选手表演已结束，请各位评委开始打分");
        double[] scores = new double[6];
        int index = 0;
        Scanner scanner = new Scanner(System.in);
        while (index < 6) {
            System.out.println("请第" + (index + 1) + "位评委进行打分");
            scores[index] = scanner.nextDouble();
            index++;
        }
        System.out.println("感谢各位评委，打分结束，正在统计平均分...");
        double total = 0;
        for (double score : scores) {
            total += score;
        }
        double avg = total / 6F;
        System.out.println("陈灼强选手的最终分数是：" + avg);
    }
}
