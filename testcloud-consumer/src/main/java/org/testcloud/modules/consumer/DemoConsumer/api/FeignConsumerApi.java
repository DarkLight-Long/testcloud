package org.testcloud.modules.consumer.DemoConsumer.api;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//@FeignClient(value = "testcloud-provider", path = "nacos") // ribbon有问题待修复
@FeignClient(value = "testcloud-provider", url = "127.0.0.1:8080", path = "nacos")
public interface FeignConsumerApi {

    @GetMapping("/provider/{name}")
    String provider(@PathVariable String name);

}
