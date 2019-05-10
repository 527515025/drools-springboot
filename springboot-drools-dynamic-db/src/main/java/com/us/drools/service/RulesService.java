package com.us.drools.service;

import com.us.drools.bean.Message;
import com.us.drools.bean.Rules;
import com.us.drools.dao.RulesDao;
import org.drools.core.ClassObjectFilter;
import org.kie.api.KieBase;
import org.kie.api.KieServices;
import org.kie.api.builder.*;
import org.kie.api.builder.model.KieBaseModel;
import org.kie.api.builder.model.KieModuleModel;
import org.kie.api.io.KieResources;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by yangyibo on 16/12/9.
 */
@Service
public class RulesService {
    @Autowired
    private RulesDao rulesDao;

    public String executeRules(Integer id) {
        String rules = "";
        Rules ru = rulesDao.getById(id);
        if (ru != null && ru.getRules() != null) {
            rules = ru.getRules();
        }

        KieServices kieServices = KieServices.Factory.get();
        KieFileSystem kfs = kieServices.newKieFileSystem();
        kfs.write("src/main/resources/rules/rules.drl", rules.getBytes());
        KieBuilder kieBuilder = kieServices.newKieBuilder(kfs).buildAll();
        Results results = kieBuilder.getResults();
        if (results.hasMessages(org.kie.api.builder.Message.Level.ERROR)) {
            System.out.println(results.getMessages());
            throw new IllegalStateException("### errors ###");
        }
        KieContainer kieContainer = kieServices.newKieContainer(kieServices.getRepository().getDefaultReleaseId());
        KieBase kieBase = kieContainer.getKieBase();
        KieSession ksession = kieBase.newKieSession();

        Message message = new Message();
        message.setStatus("0");
        ksession.insert(message);
        ksession.fireAllRules();

        String result= null;
        for (Object per : ksession.getObjects(new ClassObjectFilter(Message.class))) {
            if (((Message) per).getStatus().equals("0")) {
                result = ((Message) per).getContent();
            }
        }
        return result;
    }


    public void getRules(Integer id) {
        KieServices kieServices = KieServices.Factory.get();
        KieResources resources = kieServices.getResources();
        KieModuleModel kieModuleModel = kieServices.newKieModuleModel();

        KieBaseModel baseModel = kieModuleModel.newKieBaseModel(
                "FileSystemKBase").addPackage("rules");
        baseModel.newKieSessionModel("FileSystemKSession");
        KieFileSystem fileSystem = kieServices.newKieFileSystem();

        String xml = kieModuleModel.toXML();
        System.out.println(xml);
        fileSystem.writeKModuleXML(xml);

        fileSystem.write("src/main/resources/rules/rule.drl", resources
                .newClassPathResource("kiefilesystem/KieFileSystemTest.drl"));

        KieBuilder kb = kieServices.newKieBuilder(fileSystem);
        kb.buildAll();//7
        if (kb.getResults().hasMessages(org.kie.api.builder.Message.Level.ERROR)) {
            throw new RuntimeException("Build Errors:\n"
                    + kb.getResults().toString());
        }
        KieContainer kContainer = kieServices.newKieContainer(kieServices
                .getRepository().getDefaultReleaseId());

        KieSession kSession = kContainer.newKieSession("FileSystemKSession");

        kSession.fireAllRules();
    }
}
