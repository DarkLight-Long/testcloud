package org.testcloud.modules.provider.DemoProvider.controller;

import org.apache.rocketmq.common.message.Message;
import org.springframework.web.bind.annotation.*;
import org.testcloud.modules.provider.DemoProvider.model.MessageProvider;

import java.util.HashMap;

@RestController
@RequestMapping("/message/provider")
public class MessageProviderController {

    public static final HashMap<String, MessageProvider> providerMap = new HashMap<>();

    @GetMapping("/start/{name}")
    public boolean start(@PathVariable String name) {
        MessageProvider messageProvider;
        if (providerMap.containsKey(name)) {
            messageProvider = providerMap.get(name);
        } else {
            messageProvider = new MessageProvider(name);
            try {
                messageProvider.init();
                messageProvider.start();
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
            providerMap.put(name, messageProvider);
        }
        Message message = new Message();
        message.setTopic("normal");
        message.setTags("normal");
        message.setBody("normal message with normal topic and normal tags".getBytes());
        try {
            messageProvider.sendMessage(message);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @GetMapping("/shutdown/{name}")
    public boolean shutdown(@PathVariable String name) {
        if (providerMap.containsKey(name)) {
            providerMap.remove(name).shutDown();
            return true;
        } else {
            return false;
        }
    }

}
