package org.testcloud.component.dubbocomsuer.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.testcloud.component.api.IService.ApiService;

import java.util.Optional;

@Slf4j
@Component
public class ConsumerUtils implements CommandLineRunner {

    @DubboReference
    private ApiService apiServiceImpl;

    @Override
    public void run(String... args) throws Exception {
        querySysUerInfo();
        getUserName(1);
    }

    public void querySysUerInfo() {
        try {
            log.info(Optional.of(apiServiceImpl.queryUserInfo()).get().getUserName());
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    public void getUserName(int id) {
        try {
            log.info(apiServiceImpl.getUserName(id));
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }
}
