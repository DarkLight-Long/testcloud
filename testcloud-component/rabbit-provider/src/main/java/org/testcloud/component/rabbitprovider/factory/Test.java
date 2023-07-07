package org.testcloud.component.rabbitprovider.factory;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;

/**
 * 一个使用rabbit发布者的例子
 */
public class Test {

    public static void main(String[] args) {
        // "guest"/"guest" by default, limited to localhost connections
        ConnectionFactory factory = new ConnectionFactory();
        // 默认有值
//        factory.setUsername("guest");
//        factory.setPassword("guest");
//        factory.setVirtualHost();
//        factory.setHost();
//        factory.setPort();
        try {
            Connection connection = factory.newConnection();
            // 多节点选择
//            Connection connection = factory.newConnection(new CommonAddressResolver());
            Channel channel = connection.createChannel();
            // 需要反馈结果的（消息的状态）
            channel.exchangeDeclare("exchangeName", "direct", true);
            channel.queueDeclare("queueName", true, false, false, null);
            channel.queueBind("queueName", "exchangeName", "routekey");

            // 临时的
            AMQP.Queue.DeclareOk response = channel.queueDeclarePassive("queueName");
            response.getMessageCount();
            response.getConsumerCount();

            // 不需要反馈结果的（消息的状态）
            channel.queueDeclareNoWait("queueName", true, false, false, null);

            // 删除queue
            channel.queueDelete("queueName", false, true);
            // 清空queue
            channel.queuePurge("queueName");

            // 发送消息到exchange
            byte[] message = "hello world".getBytes(StandardCharsets.UTF_8);
            channel.basicPublish("exchangeName", "routeKey", null, message);

            // 使用预设的消息属性发送消息
            boolean mandatory = false;
            channel.basicPublish("exchangeName", "routeKey", mandatory,
                    MessageProperties.PERSISTENT_TEXT_PLAIN, message);
            // 这将发送传递模式为 2（持久）、优先级为 1 的消息 和内容类型“文本/纯文本” 过期时间600
            channel.basicPublish("exchangeName", "routeKey",
                    new AMQP.BasicProperties.Builder().contentType("text/plain")
            .deliveryMode(2).priority(1).userId("bob").expiration("600").build(), message);

            // 发送一个带headers的消息
            Map<String, Object> headers = new HashMap<String, Object>();
            headers.put("latitude",  51.5252949);
            headers.put("longitude", -0.0905493);
            channel.basicPublish("exchangeName", "routingKey",
                    new AMQP.BasicProperties.Builder()
                            .headers(headers)
                            .build(),
                    message);

            // 三个状态 open closing(waiting for closed 即等待下边的事件) closed
            // 处理关闭事件

            CommonShutDownListener commonShutDownListener = new CommonShutDownListener();
            connection.addShutdownListener(commonShutDownListener);
            connection.removeShutdownListener(commonShutDownListener);
            channel.addShutdownListener(commonShutDownListener);
            channel.removeShutdownListener(commonShutDownListener);
            // 获取关闭原因
            connection.getCloseReason();
            channel.getCloseReason();

            // 是否正常状态 不建议使用（会有误差 详见doc）
            connection.isOpen();
            channel.isOpen();
            // 替换

            try {
                //...
                channel.basicQos(1);
            } catch (ShutdownSignalException e) {
                // possibly check if channel was closed
                // by the time we started action and reasons for
                // closing it
            }
            catch (IOException ioe) {
                // check why connection was closed
            }

            // 通知关闭以及原因(code未找到(不知对不对))
            connection.close(AMQP.CONNECTION_FORCED, "closeMessage");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static class CommonShutDownListener implements ShutdownListener {
        @Override
        public void shutdownCompleted(ShutdownSignalException cause) {
            // 是否是connection或者channel error
            // 其他异常通过cause.getCause()获取
            if (cause.isHardError()) {
                Connection connection = (Connection) cause.getReference();
                if (!cause.isInitiatedByApplication()) {
                    Method reason = cause.getReason();
                    //
                }
                //
            } else {
                Channel channel = (Channel) cause.getReference();
                //
            }
        }
    }

    // 官方附带的 DnsSrvRecordAddressResolver(使用注册中心) 和 DnsRecordIpAddressResolver(dns且另外有负载均衡)
    public static class CommonAddressResolver implements AddressResolver {

        @Override
        public List<Address> getAddresses() throws IOException {
            return Collections.emptyList();
        }
    }

}
