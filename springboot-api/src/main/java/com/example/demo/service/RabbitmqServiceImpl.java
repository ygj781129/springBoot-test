package com.example.demo.service;

import com.example.demo.pojo.Student;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * Created by fb on 2021/7/5
 */
@Service
public class RabbitmqServiceImpl {

        @Autowired
        private RabbitTemplate rabbitTemplate;
        // 配置 confirm 机制
         RabbitTemplate.ConfirmCallback confirmCallback = new RabbitTemplate.ConfirmCallback() {
                /**
                 * @param correlationData 消息相关的数据，一般用于获取 唯一标识 id
                 * @param b true 消息确认成功，false 失败
                 * @param s 确认失败的原因
                 */
                @Override
                public void confirm(CorrelationData correlationData, boolean b, String s) {
                        if (b) {
                                System.out.println("confirm 消息确认成功..." + correlationData.getId());
                        } else {
                                System.out.println("confirm 消息确认失败..." + correlationData.getId() + " cause: " + s);
                        }
                }
        };
        // 向发布订阅模式里面发送消息
        public void sendPublish() {
                for (int i = 0; i < 5; i++) {
                        // rabbitTemplate.convertSendAndReceive("exchange_fanout", "", "测试发布订阅模型：" + i);
                        rabbitTemplate.convertSendAndReceive ("exchange_fanout", "", "测试发布订阅模型：" + i);
                }
        }

        // 向topic模型发送数据
        public void sendTopic() {
                for (int i = 0; i < 10; i++) {
                        if (i % 2 == 0) {
                                rabbitTemplate.convertAndSend("exchange_topic", "topics.km.topic", "测试发布订阅模型：" + i);

                        } else {
                                rabbitTemplate.convertAndSend("exchange_topic", "topic.km", "测试发布订阅模型：" + i);

                        }
                }
        }
        public void sendConfirm() {
                rabbitTemplate.convertAndSend("queue_confirm", new Student("sss", 1, "km123"), new CorrelationData("" + System.currentTimeMillis()));

        }

}
