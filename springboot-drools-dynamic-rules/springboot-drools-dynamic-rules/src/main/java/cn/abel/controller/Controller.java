package cn.abel.controller;

import cn.abel.service.ExecuteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yyb
 * @time 2019/4/25
 */
@RestController
public class Controller {
    @Autowired
    private ExecuteService executeService;

    @GetMapping("/dynamicRule")
    public String test() {
        return executeService.execute().toString();
    }
}
