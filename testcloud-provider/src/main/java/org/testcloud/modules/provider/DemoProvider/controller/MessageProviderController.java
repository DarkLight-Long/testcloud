package org.testcloud.modules.provider.DemoProvider.controller;

import org.apache.rocketmq.common.message.Message;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.testcloud.modules.provider.DemoProvider.model.MessageProvider;

@RestController
@RequestMapping("/message/provider")
public class MessageProviderController {

    @GetMapping("/start/{name}")
    public void start(@PathVariable String name) {
        MessageProvider messageProvider = new MessageProvider(name);
        messageProvider.init();
        messageProvider.start();
        Message message = new Message();
        message.setTopic("normal");
        message.setTags("normal");
        message.setBody("normal message with normal topic and normal tags".getBytes());
        messageProvider.sendMessage(message);
    }

}
