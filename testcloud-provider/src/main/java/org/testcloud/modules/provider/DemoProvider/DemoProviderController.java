package org.testcloud.modules.provider.DemoProvider;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/nacos")
@RestController
public class DemoProviderController {

    @GetMapping("/provider/{name}")
    private String provider(@PathVariable String name) {
        return "holle,wolcome to sever named \n" + name;
    }

}
