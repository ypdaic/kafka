package com.daiyanping.demo.kafka.listener;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.stereotype.Component;

/**
 * @ClassName KafkaListener
 * @Description TODO
 * @Author daiyanping
 * @Date 2020-05-20
 * @Version 0.1
 */
@Component
public class KafkaListener {

    /**
     * 当groupId不配置时，将id作为groupId
     * properties 可以为每个消费者指定各自的消费参数，这里指定的参数会覆盖，KafkaProperties的总体配置
     * @param record
     */
//    @org.springframework.kafka.annotation.KafkaListener(topics = {"test"}, groupId = "a",
//            properties = {
//                "auto.commit.interval.ms:6000",
//                "auto.offset.reset:latest",
//                "enable.auto.commit:true",
//                "key.deserializer:org.apache.kafka.common.serialization.StringDeserializer",
//                "value.deserializer:org.apache.kafka.common.serialization.StringDeserializer",
//                "max.poll.records:100"
//            })
    @org.springframework.kafka.annotation.KafkaListener(topics = {"test"}, groupId = "a")
    public void test(ConsumerRecord<String, String> record) {
        String key = record.key();
        String value = record.value();

        System.out.println("键：" + key + " 值：" + value);
    }
}
