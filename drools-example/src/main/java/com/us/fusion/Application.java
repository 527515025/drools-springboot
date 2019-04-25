package com.us.fusion;

import com.us.model.Person;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import java.util.Date;

/**
 * Created by yangyibo on 17/1/3.
 * @author yangyibo
 */
public class Application {
    private static KieSession getSession() {
        KieServices ks = KieServices.Factory.get();
        KieContainer kc = ks.getKieClasspathContainer();
        return kc.newKieSession("fusionAgeKS");
    }

    public static void run() {

        KieSession ks = getSession();
        Person p1 = new Person("白展堂", 52,new Date());
        Person p2 = new Person("佟湘玉", 57,new Date());
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            System.out.println(e);
        }
        Person p3 = new Person("李大嘴", 56,new Date());

        ks.insert(p1);
        ks.insert(p2);
        ks.insert(p3);

        int count = ks.fireAllRules();
        System.out.println("总执行了" + count + "条规则------------------------------");

        ks.dispose();
    }


    public static void main(String[] args) {
        run();
    }

}
