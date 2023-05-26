package com.example.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;

public class DemoControoler {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @GetMapping("/demo")
    public void demoGet(){
        logger.info("Hitting get endpoint");
    }
}
