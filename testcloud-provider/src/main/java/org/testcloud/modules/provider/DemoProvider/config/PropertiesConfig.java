package org.testcloud.modules.provider.DemoProvider.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class PropertiesConfig {

    public static String namesrvAddress;

    @Value("${provider.namesrvAddress}")
    public void setNamesrvAddress(String address) {
        namesrvAddress = address;
    }

}
