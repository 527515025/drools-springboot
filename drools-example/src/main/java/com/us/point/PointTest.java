package com.us.point;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

/**
 * @author  yyb
 */

// 过生日，则加10分，并且将当月交易翻倍后再计算积分
// 2011-01-08 - 2011-08-08每月信用卡还款3次以上，每满3笔赠送30分
// 当月购物总金额100以上，每100元赠送10分
// 当月购物次数5次以上，每五次赠送50分
// 特别的，如果全部满足了要求，则额外奖励100分
// 发生退货，扣减10分
// 退货金额大于100，扣减100分

public class PointTest {
    public static void main(String[] args) {
        point();
    }

    public static void point(){
        // load up the knowledge base
        KieServices ks = KieServices.Factory.get();
        KieContainer kContainer = ks.getKieClasspathContainer();
        KieSession kSession = kContainer.newKieSession("ksession-rules");

        PointDomain pointDomain = new PointDomain();
        pointDomain.setUserName("ko");
        //买了5笔
        pointDomain.setBuyNums(5);
        //每月3笔
        pointDomain.setBillThisMonth(3);
        //过生日
        pointDomain.setBirthDay(true);
        pointDomain.setPoint(0);
        //花了500
        pointDomain.setBuyMoney(500);

        //退货1笔
        pointDomain.setBackNums(1);
        //退货100
        pointDomain.setBackMondy(100);

        // go !
        kSession.insert(pointDomain);
        kSession.fireAllRules();
        System.out.println("积分 ："+pointDomain.getPoint());
    }

    public static void printInfo(PointDomain p){

        System.out.println("-------"+p.getUserName()+"-----的当前积分为"
                +p.getPoint()+"----购买的次数为"
                +p.getBuyNums()+"---购买的金额为"
                +p.getBuyMoney()+"---每月信用卡还款次数"
                +p.getBillThisMonth()+"\n\n");

    }
}
