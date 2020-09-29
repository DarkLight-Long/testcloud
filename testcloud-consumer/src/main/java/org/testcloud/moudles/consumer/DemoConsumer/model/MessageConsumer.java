package org.testcloud.moudles.consumer.DemoConsumer.model;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.testcloud.moudles.consumer.DemoConsumer.config.PropertiesConfig;
import org.testcloud.moudles.consumer.DemoConsumer.utils.MessageLsitener;

@Slf4j
public class MessageConsumer {

    private DefaultMQPushConsumer consumer;
    private String consumerGroupName;
    private String nameSrvAddress;
    private String topic;
    private String tags;
    private MessageListenerConcurrently messageListener;

    public MessageConsumer(String consumerGroupName) {
        this.consumerGroupName = consumerGroupName;
        this.nameSrvAddress = PropertiesConfig.nameSrvAddress;
        this.topic = PropertiesConfig.topic;
        this.tags = PropertiesConfig.tags;
        messageListener = new MessageLsitener();
    }

    public void init() {
        try {
            consumer = new DefaultMQPushConsumer();
            consumer.setConsumerGroup(this.consumerGroupName);
            consumer.setNamesrvAddr(this.nameSrvAddress);
            consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
//            consumer.subscribe(this.topic, this.tags);
            consumer.subscribe(this.topic, "*");
            consumer.registerMessageListener(messageListener);

            log.debug("consumer" + consumer.getConsumerGroup() + "初始化成功");
        }catch (MQClientException e) {
            log.error("consumer" + consumer.getConsumerGroup() + "初始化失败");
            e.printStackTrace();
        }
    }

    public void start() {
        try {
            consumer.start();
            log.info("consumer" + consumer.getConsumerGroup() + "启动成功");
        }catch (MQClientException e) {
            e.printStackTrace();
            log.error("consumer" + consumer.getConsumerGroup() + "启动失败");
        }
    }

    public void shutDown() {
        consumer.shutdown();
        log.info("consumer" + consumer.getConsumerGroup() + "关闭成功");
    }


}
