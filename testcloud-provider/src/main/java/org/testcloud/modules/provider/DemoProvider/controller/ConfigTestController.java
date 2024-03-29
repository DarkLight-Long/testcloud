package org.testcloud.modules.provider.DemoProvider.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.testcloud.modules.provider.DemoProvider.config.PropertiesListConfig;

import java.util.List;

@RestController
@RequestMapping("/configTest")
public class ConfigTestController {

    @Autowired
    private PropertiesListConfig propertiesListConfig;
    @Autowired
    private RedisTemplate redisTemplate;

    @GetMapping("/getList")
    public List getList() {
        return propertiesListConfig.getList();
    }


}
