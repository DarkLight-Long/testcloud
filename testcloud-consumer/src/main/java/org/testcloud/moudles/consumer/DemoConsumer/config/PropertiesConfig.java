package org.testcloud.moudles.consumer.DemoConsumer.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class PropertiesConfig {

    public static String nameSrvAddress;
    @Value("${consumer.nameSrvAddress}")
    private void setNameSrvAddress(String address) {
        nameSrvAddress = address;
    }

    public static String topic;
    @Value("${consumer.topic}")
    private void setTopic(String topic1) {
        topic = topic1;
    }

    public static String tags;
    @Value("${consumer.tags}")
    private void setTags(String tags1) {
        tags = tags1;
    }


}
