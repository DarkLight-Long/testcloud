package org.testcloud.moudles.consumer.DemoConsumer.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;

@Slf4j
public class MessageLsitener implements MessageListenerConcurrently {

    @Override
    public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext consumeConcurrentlyContext) {
        if (!list.isEmpty()) {
            for (MessageExt messageExt : list) {
                String mag = new String(messageExt.getBody(), StandardCharsets.UTF_8);
                log.info(new Date().toString() + "接收到消息：" + mag);
            }
            log.info("共处理了" + list.size() + "条消息");
            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
        }
        return null;

    }
}
