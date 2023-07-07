package org.testcloud.modules.consumer.DemoConsumer.model;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.consumer.rebalance.AllocateMessageQueueAveragelyByCircle;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;
import org.testcloud.modules.consumer.DemoConsumer.config.PropertiesConfig;
import org.testcloud.modules.consumer.DemoConsumer.utils.MessageLsitener;

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

    public void init() throws Exception {
        // 此处为push模式消费者
        consumer = new DefaultMQPushConsumer();
        consumer.setConsumerGroup(this.consumerGroupName);
        consumer.setNamesrvAddr(this.nameSrvAddress);
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
//            consumer.subscribe(this.topic, this.tags);
        consumer.subscribe(this.topic, "*");
        // 消费者消费功能
        consumer.registerMessageListener(messageListener);

        // 设置集群消费-> 一组里只有一个消费即可；广播则需要所有人都消费
        consumer.setMessageModel(MessageModel.CLUSTERING);
        // 集群消费时消息分配策略
        consumer.setAllocateMessageQueueStrategy(new AllocateMessageQueueAveragelyByCircle());

        log.debug("consumer" + consumer.getConsumerGroup() + "初始化成功");
    }

    public void start() throws Exception {
        consumer.start();
        log.info("consumer" + consumer.getConsumerGroup() + "启动成功");
    }

    public void shutDown() {
        consumer.shutdown();
        log.info("consumer" + consumer.getConsumerGroup() + "关闭成功");
    }


}
