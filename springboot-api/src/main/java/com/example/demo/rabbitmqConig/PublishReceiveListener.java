package com.example.demo.rabbitmqConig;

import com.example.demo.pojo.Student;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * Created by fb on 2021/7/5
 */
@Component
public class PublishReceiveListener {
        @RabbitListener(queues = "queue_fanout1")
        public void receiveMsg1(String msg) {
                System.out.println("队列1接收到消息：" + msg);
        }

        @RabbitListener(queues = "queue_fanout2")
        public void receiveMsg2(String msg) {
                System.out.println("队列2接收到消息：" + msg);
        }

        @RabbitListener(queues = "queue_topic1")
        public void receiveMsg3(String msg) {
                System.out.println("消费者1接收到：" + msg);
        }

        @RabbitListener(queues = "queue_topic2")
        public void receiveMsg4(String msg) {
                System.out.println("消费者2接收到：" + msg);
        }


        @RabbitListener(queues = "queue_confirm")
        public void receiveMsg4d(Student msg) {
                System.out.println("消费者2接收到：" + msg);
        }

}
