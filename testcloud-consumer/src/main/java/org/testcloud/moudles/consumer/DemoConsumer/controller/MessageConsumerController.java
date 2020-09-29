package org.testcloud.moudles.consumer.DemoConsumer.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.testcloud.moudles.consumer.DemoConsumer.model.MessageConsumer;

@RestController
@RequestMapping("/message/consumer")
public class MessageConsumerController {

    @GetMapping("/start/{name}")
    public void start(@PathVariable String name) {
        MessageConsumer messageConsumer = new MessageConsumer(name);
        messageConsumer.init();
        messageConsumer.start();
    }

}
