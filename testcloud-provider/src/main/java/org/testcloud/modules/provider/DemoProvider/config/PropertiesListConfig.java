package org.testcloud.modules.provider.DemoProvider.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 *  ConfigurationProperties 记录
 */
@Data
@ConfigurationProperties(prefix = "config")
@Component
public class PropertiesListConfig {
    private List<Person> list;

    @Data
    static class Person {
        private String name;
        private Integer age;
        private String sex;
    }

}
