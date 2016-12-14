package com.us.drink;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

/**
 * Created by yangyibo on 16/12/13.
 */
public class DrinkBeer {
    private static KieSession getSession() {
        KieServices ks = KieServices.Factory.get();
        KieContainer kc = ks.getKieClasspathContainer();
        return kc.newKieSession("DrinkBeerKS");
    }

    private static void drink() {
        KieSession ks = getSession();
        //今年贝拉克·侯赛因·奥巴马55岁了,但很不幸他失业了,在前八年的工作中他的职位没有任何的晋升,
        // 而且他没有明显的专业技能,尽管他能一小段JavaScript代码。
        //但是他仍然很难再次找到工作,怀着惆怅的心情奥巴马来到酒吧,想借酒浇愁,一醉方休。
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
