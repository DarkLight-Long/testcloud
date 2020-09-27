package org.testcloud.moudles.consumer.DemoConsumer;

import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RequestMapping("/nacos/consumer")
@RestController
public class DemoConsumerController {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private FeignConsumerApi feignConsumerApi;
    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @GetMapping("/rest/{name}")
    public String rest(@PathVariable String name) {
        ServiceInstance serviceInstance = loadBalancerClient.choose("testcloud-provider");
        System.out.println( serviceInstance.getHost() + "\n" + serviceInstance.getPort());
        String url = String.format("http://%s:%s/nacos/provider/%s",
                serviceInstance.getHost(), serviceInstance.getPort(), name);
        System.out.println( "url => " + url);
        return restTemplate.getForObject(url, String.class);
    }

    @GetMapping("/feign/{name}")
    public String feign(@PathVariable String name) {
        return feignConsumerApi.provider(name);
    }

    @GetMapping("/ribbon/{name}")
    public String ribbon(@PathVariable String name) {
        return restTemplate.getForObject("http://testcloud-provider/nacos/provider/" +
                name, String.class);
    }

}
