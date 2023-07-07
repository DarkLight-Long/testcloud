package org.testcloud.modules.consumer.DemoConsumer.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.testcloud.modules.consumer.DemoConsumer.model.MessageConsumer;

import java.util.HashMap;

@RestController
@RequestMapping("/message/consumer")
public class MessageConsumerController {

    public static final HashMap<String, MessageConsumer> messageMap = new HashMap<>();

    @GetMapping("/start/{name}")
    public boolean start(@PathVariable String name) {
        MessageConsumer messageConsumer = new MessageConsumer(name);
        try {
            messageConsumer.init();
            messageConsumer.start();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        messageMap.put(name, messageConsumer);
        return true;
    }

    @GetMapping("/shutdown/{name}")
    public boolean shutdown(@PathVariable String name) {
        if (messageMap.containsKey(name)) {
            messageMap.remove(name).shutDown();
            return true;
        } else {
            return false;
        }
    }

}
