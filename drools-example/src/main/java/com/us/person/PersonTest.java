package com.us.person;

import org.kie.api.KieServices;
import org.kie.api.definition.type.FactType;
import org.kie.api.io.ResourceType;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.Agenda;
import org.kie.api.runtime.rule.FactHandle;
import org.kie.api.runtime.rule.QueryResults;
import org.kie.api.runtime.rule.QueryResultsRow;


/**
 * @author  yyb
 */
public  class PersonTest {
    static KieSession getSession() {
        KieServices ks = KieServices.Factory.get();
        KieContainer kc = ks.getKieClasspathContainer();
        return kc.newKieSession("simpleRuleKSession");
    }


    public static void run() {
        int i = 3;
        while (i > 0) {

            try
            {
                Thread.sleep(5000);
            }
            catch(InterruptedException e)
            {
                System.out.println("休眠被中断。");
            }
            KieSession ks = getSession();

             //设置agenda-group 的auto-focus 使其执行
//            ks.getAgenda().getAgendaGroup("group1").setFocus();

//            QueryResults queryResults=ks.getQueryResults("query age");
//            for(QueryResultsRow qr:queryResults){
//                Person p=(Person)qr.get("person");
//                //打印查询结果
//                System.out.println("customer name :"+p.getName());
//            }



            Person p1 = new Person("白展堂", 68);
            Person p2 = new Person("李大嘴", 32);
            Person p3 = new Person("佟湘玉", 18);
            Person p4 = new Person("郭芙蓉", 8);
            Person p5 = new Person("祝无双", 66);

            System.out.println("before p1 : " + p1);
            System.out.println("before p2 : " + p2);
            System.out.println("before p3 : " + p3);
            System.out.println("before p4 : " + p4);
            ks.insert(p1);
            ks.insert(p2);
            ks.insert(p3);
            ks.insert(p4);
            ks.insert(p5);

            int count = ks.fireAllRules();

            //使用规则名来对要执行的规则做一个过滤,执行规则名匹配的规则
//            TestAgendaFilter filter= new TestAgendaFilter("activa");
//            int count = ks.fireAllRules(filter);

            System.out.println("总执行了" + count + "条规则------------------------------");
            System.out.println("after p1 : " + p1);
            System.out.println("after p2 : " + p2);
            System.out.println("after p3 : " + p3);
            System.out.println("after p4 : " + p4);
            System.out.println("after p4 : " + p5);
            ks.dispose();
            i--;
        }
    }




    public static void main(String[] args) {
        run();
    }
}
