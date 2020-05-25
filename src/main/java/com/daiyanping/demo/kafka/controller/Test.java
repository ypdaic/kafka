package com.daiyanping.demo.kafka.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName Test
 * @Description TODO
 * @Author daiyanping
 * @Date 2020-05-20
 * @Version 0.1
 */
@RestController
@RequestMapping("/kafka/test")
public class Test {

    @Autowired
    KafkaTemplate kafkaTemplate;

    /**
     * KafkaTemplate 的生产者参数默认使用，KafKaProperties的总体配置，如果想要
     * 配置多个生成者参数，需要配置多个KafKaTemplate，也就是配置多个DefaultKafkaProducerFactory
     */
    @PostMapping("/send")
    public void sendTest() {
        kafkaTemplate.send("test", "key", "value");
    }

    @PostMapping("/send/{id}")
    public void sendTest2(@PathVariable("id") String id, String id2) {
        System.out.println(id);
    }
}
