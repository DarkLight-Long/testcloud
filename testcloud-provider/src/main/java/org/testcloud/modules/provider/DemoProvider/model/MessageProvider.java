package org.testcloud.modules.provider.DemoProvider.model;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.testcloud.modules.provider.DemoProvider.config.PropertiesConfig;
@Slf4j
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

    public void start() {
        try {
            producer.start();
            log.info("producer启动成功");
        }catch (MQClientException e) {
            e.printStackTrace();
            log.error("producer启动失败");
        }
    }

    public void shutDown() {
        producer.shutdown();
        log.info("producer关闭成功");
    }

    public void sendMessage(Message message) {
        try {
            SendResult sendResult = producer.send(message);
            System.out.println(sendResult.toString());
        }catch (Exception e) {
            e.printStackTrace();
            log.error("producer发送消息失败");
        }
    }

}
