package com.us.person;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

/**
 * @author  yyb
 */
public class PersonAgeTest {
        static KieSession getSession() {


        KieServices ks = KieServices.Factory.get();

        KieContainer kc = ks.getKieClasspathContainer();

        return kc.newKieSession("simpleRuleKSession");
    }
    public static void run() {
            KieSession ks = getSession();

            Person p1 = new Person("白展堂", 2);
            Person p2 = new Person("李大嘴", 11);
            System.out.println("before p1 : " + p1);

            ks.insert(p1);
            ks.insert(p1);

            int count = ks.fireAllRules();
            System.out.println("总执行了" + count + "条规则------------------------------");
            System.out.println("after p1 : " + p1);
            ks.insert(p2);
            ks.fireAllRules();
            ks.dispose();
    }




    public static void main(String[] args) {
        run();
    }
}