package com.example.demo.controller;

import com.example.demo.service.RabbitmqServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by fb on 2021/7/5
 */
@RestController
public class RabbitmqController {

        @Autowired
        private RabbitmqServiceImpl rabbitmqService;

        @GetMapping("/sendTopic")
        public String sendTopic() {
                rabbitmqService.sendTopic();
                return "发送成功...";
        }
}
