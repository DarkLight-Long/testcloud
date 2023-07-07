package org.testcloud.component.rabbitconsumer.factory;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 一个使用消费者的例子
 */
public class Test {

    public static void main(String[] args) {
        ConnectionFactory factory = new ConnectionFactory();
        try {
            // 除非有严格要求(默认的其实有线程池)，需要手动关闭线程池(默认的不用),否则会阻止jvm终止 Todo
            ExecutorService executorService = Executors.newFixedThreadPool(20);
            // Connection connection = factory.newConnection();
            Connection connection = factory.newConnection(executorService);

            Channel channel = connection.createChannel();
            // 是否自动标记消息已处理 => 详见下
            boolean autoAck = false;

            // push api
            channel.basicConsume("queueName", autoAck, "consumerTag",
                    new DefaultConsumer(channel) {
                        @Override
                        public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                            String routeKey = envelope.getRoutingKey();
                            String contentType = properties.getContentType();
                            long deliveryTag = envelope.getDeliveryTag();
                            // ...
                            // 消息已处理
                            channel.basicAck(deliveryTag, false);
                        }
                    });
            // 取消一个消费者
            channel.basicCancel("consumerTag");

            // pull api
            GetResponse response = channel.basicGet("queueName", autoAck);
            if (Objects.isNull(response)) {
            } else {
                AMQP.BasicProperties props = response.getProps();
                byte[] bytes = response.getBody();
                long deliveryTag = response.getEnvelope().getDeliveryTag();
                // ...
                channel.basicAck(deliveryTag, false);
            }

            // 如果一些消息有绑定了exchange但是没有绑定queue => 感觉应该在publish端配置(doc未写明)
            channel.addReturnListener(new ReturnListener() {
                @Override
                public void handleReturn(int replyCode, String replyText, String exchange, String routingKey, AMQP.BasicProperties properties, byte[] body) throws IOException {
                    // ...
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
