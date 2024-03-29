package org.testcloud.modules.provider.DemoProvider.model;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.testcloud.modules.provider.DemoProvider.config.PropertiesConfig;

public class MessageProvider {

    private DefaultMQProducer producer;
    private String producerGroup;
    private String nameSrvAddress;

    public MessageProvider(String producerGroup) {
        this.producerGroup = producerGroup;
        this.nameSrvAddress = PropertiesConfig.namesrvAddress;
    }

    public void init() {
        producer = new DefaultMQProducer();
        producer.setNamesrvAddr(nameSrvAddress);
        producer.setProducerGroup(producerGroup);
    }

    public void start() throws Exception {
        producer.start();
        System.out.println("producer启动成功");
    }

    public void shutDown() {
        producer.shutdown();
        System.out.println("producer关闭成功");
    }

    public void sendMessage(Message message) throws Exception {
        SendResult sendResult = producer.send(message, 20000);
        System.out.println(sendResult.toString());
    }

}
