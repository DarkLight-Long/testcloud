package org.testcloud.moudles.consumer.DemoConsumer;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("testcloud-provider")
@RequestMapping("/nacos")
public interface FeignConsumerApi {

    @GetMapping("/provider/{name}")
    String provider(@PathVariable String name);

}
