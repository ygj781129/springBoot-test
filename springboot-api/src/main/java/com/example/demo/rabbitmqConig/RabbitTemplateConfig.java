package com.example.demo.rabbitmqConig;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * Created by fb on 2021/7/7
 */
@Component
public class RabbitTemplateConfig implements RabbitTemplate.ConfirmCallback,RabbitTemplate.ReturnCallback {

        @Autowired
        private RabbitTemplate rabbitTemplate;

//        @PostConstruct
//        public void init() {
//                rabbitTemplate.setConfirmCallback(this);            //指定 ConfirmCallback
//        }
        @PostConstruct
        public void init(){
                rabbitTemplate.setReturnCallback(this);             //指定 ReturnCallback
        }

        @Override
        public void confirm(CorrelationData correlationData, boolean ack, String cause) {
                if (correlationData!=null) {
                        if (ack) {
                                System.out.println("confirm 消息确认成功..." + correlationData.getId());
                        } else {
                                System.out.println("confirm 消息确认失败..." + correlationData.getId() + " cause: " + cause);
                        }
                } else {
                        if (ack) {
                                System.out.println("confirm 消息确认成功..." );
                        } else {
                                System.out.println("confirm 消息确认失败..."+ " cause: " + cause);
                        }
                }
        }



        @Override
        public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
                System.out.println("消息主体 message : "+message);
                System.out.println("消息主体 message : "+replyCode);
                System.out.println("描述："+replyText);
                System.out.println("消息使用的交换器 exchange : "+exchange);
                System.out.println("消息使用的路由键 routing : "+routingKey);
        }
}
