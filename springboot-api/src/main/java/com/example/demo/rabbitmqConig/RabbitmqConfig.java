//package com.example.demo.rabbitmqConig;
//
//import org.springframework.amqp.core.*;
//import org.springframework.boot.SpringBootConfiguration;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
///**
// * Created by fb on 2021/7/5
// * rabbitmq配置
// */
//
//@Configuration
//@SpringBootConfiguration
//public class RabbitmqConfig {
//        // 发布订阅模式
//        // 声明两个队列
//        @Bean
//        public Queue queueFanout1() {
//                return new Queue("queue_fanout1",true);
//        }
//        @Bean
//        public Queue queueFanout2() {
//                return new Queue("queue_fanout2",true);
//        }
//        // 准备一个交换机
//        @Bean
//        public FanoutExchange exchangeFanout() {
//                return new FanoutExchange("exchange_fanout");
//        }
//        // 将交换机和队列进行绑定
//        @Bean
//        public Binding bindingExchange1() {
//                return BindingBuilder.bind(queueFanout1()).to(exchangeFanout());
//        }
//        @Bean
//        public Binding bindingExchange2() {
//                return BindingBuilder.bind(queueFanout2()).to(exchangeFanout());
//        }
//
//
//
//        // topic 模型
//        @Bean
//        public Queue queueTopic1() {
//                return new Queue("queue_topic1");
//        }
//        @Bean
//        public Queue queueTopic2() {
//                return new Queue("queue_topic2");
//        }
//        @Bean
//        public TopicExchange exchangeTopic() {
//                return new TopicExchange("exchange_topic");
//        }
//        @Bean
//        public Binding bindingTopic1() {
//                return BindingBuilder.bind(queueTopic1()).to(exchangeTopic()).with("topic.#");
//        }
//        @Bean
//        public Binding bindingTopic2() {
//                return BindingBuilder.bind(queueTopic2()).to(exchangeTopic()).with("topic.*");
//        }
//
//        // 测试confirm 机制，专门创建了一个队列
//        @Bean
//        public Queue queueConfirm() {
//                return new Queue("queue_confirm");
//        }
//
//}
