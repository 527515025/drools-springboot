package com.us.drink;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

/**
 * @author  yyb
 */
public class DrinkBeer {
    private static KieSession getSession() {
        KieServices ks = KieServices.Factory.get();
        KieContainer kc = ks.getKieClasspathContainer();
        return kc.newKieSession("DrinkBeerKS");
    }

    private static void drink() {
        KieSession ks = getSession();
        Customer c1 = new Customer("奥巴马", 10);
        ks.insert(c1);
        int count = ks.fireAllRules();
        System.out.println("总执行了" + count + "条规则");
        System.out.println(c1.toString());
    }

    public static void main(String[] args) {
        drink();
    }
}
