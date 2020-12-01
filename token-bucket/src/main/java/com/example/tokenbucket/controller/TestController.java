package com.example.tokenbucket.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

/**
 * Created by fb on 2020/10/20
 */
@RestController
@RequestMapping("/test")
public class TestController {

        @GetMapping("/t1")
        public Object test1() {
                return Collections.singletonMap("success", "true");
        }

        @GetMapping("/t2")
        public Object test() {
                return Collections.singletonMap("success", "true");
        }
}