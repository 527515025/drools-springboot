package cn.abel.service;

import cn.abel.model.Person;
import org.drools.core.ClassObjectFilter;
import org.kie.api.KieServices;
import org.kie.api.builder.KieScanner;
import org.kie.api.builder.ReleaseId;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * @author yyb
 * @time 2019/4/25
 */
@Service
public class ExecuteService {

    private KieContainer kContainer;

    @PostConstruct
    public void setUp() {
        KieServices ks = KieServices.Factory.get();

        ReleaseId releaseId = ks.newReleaseId("cn.abel", "springboot-drools-dynamic-rules-kjar", "1.0-SNAPSHOT");

        kContainer = ks.newKieContainer(releaseId);
        KieScanner kScanner = ks.newKieScanner(kContainer);

        // Start the KieScanner polling the Maven repository every 10 seconds
        kScanner.start(10000L);
    }

    public Integer execute() {
        KieSession kSession = kContainer.newKieSession("PersonAgeSession");
        kSession.setGlobal("out", System.out);

        kSession.insert(new Person("Dave",100));
        kSession.fireAllRules();

        Integer result = null;

        for (Object per : kSession.getObjects(new ClassObjectFilter(Person.class))) {
            if (((Person) per).getName().equals("abel")) {
                result = ((Person) per).getAge();
            }
        }

        return result;
    }
}
