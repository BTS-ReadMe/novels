package com.readme.novels.testController;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user-service")
public class test {

    @RequestMapping("/test")
    public String test() {
        return "hi";
    }

}
