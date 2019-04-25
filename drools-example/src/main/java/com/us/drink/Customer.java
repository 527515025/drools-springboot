package com.us.drink;

/**
 * @author  yyb
 *
 * 饮酒客户
 */
public class Customer {
    private String name;
    private int money;
    private int drinkSum;
    private int blankCup;
    private int cap;

    public Customer(String name, int money) {
        super();
        this.name = name;
        this.money = money;
        this.drinkSum = 0;
        this.blankCup = 0;
        this.cap = 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getDrinkSum() {
        return drinkSum;
    }

    public void setDrinkSum(int drinkSum) {
        this.drinkSum = drinkSum;
    }

    public int getBlankCup() {
        return blankCup;
    }

    public void setBlankCup(int blankCup) {
        this.blankCup = blankCup;
    }

    public int getCap() {
        return cap;
    }

    public void setCap(int cap) {
        this.cap = cap;
    }

    @Override
    public String toString() {

        return  this.name+"同学还有"
                +this.money+"元钱,喝了"+this.drinkSum+"瓶酒, 有"
                +this.getBlankCup()+"个空瓶和"
                +this.getCap()+"个盖子 \n";
    }
}
