package com.us.drink;

import java.util.Scanner;

/**
 * @author  yyb
 */
public class DrinkBeerTest {
    public static void main(String[] args) {

        System.out.println("请输入买啤酒的总金额:");
        Scanner sc = new Scanner(System.in);
        int money = sc.nextInt();
        //drinkSum:喝的瓶数 //blankCup:空瓶数 //cap:瓶盖数
        int drinkSum = 0, blankCup = 0, cap = 0;
        //drinkSum:喝的瓶数
        drinkSum = money / 2;
        //cap:瓶盖数 ，blankCup:空瓶数
        cap = blankCup = drinkSum;
        while (cap / 4 >= 1 || blankCup / 2 >= 1) {
            if (blankCup / 2 >= 1)//计算瓶子
            {
                drinkSum += blankCup / 2;
                cap = cap + blankCup / 2;
                blankCup = blankCup % 2 + blankCup / 2;
            }
            if (cap / 4 >= 1) { //计算瓶盖
                drinkSum += cap / 4;
                blankCup += cap / 4;
                cap = cap % 4 + cap / 4;
            }
        }
        System.out.println("总共喝啤酒数" + drinkSum + " 剩余空瓶数:" + blankCup + " 剩余瓶盖数:" + cap);
    }
}
