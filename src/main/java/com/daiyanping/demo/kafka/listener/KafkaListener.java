package com.daiyanping.demo.kafka.listener;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.TopicPartition;
import org.springframework.kafka.listener.ConsumerSeekAware;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @ClassName KafkaListener
 * @Description TODO
 * @Author daiyanping
 * @Date 2020-05-20
 * @Version 0.1
 */
@Component
public class KafkaListener implements ConsumerSeekAware {

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

    /**
     * 这里的ConsumerSeekCallback就是我们的ListenerConsumer，该方法在
     * ListenerConsumer的run方法中会执行 ((ConsumerSeekAware) this.genericListener).registerSeekCallback(this);
     * 我们可以在这里去指定我们的消费偏移量，前提是使用的assgin 手动模式，但是如果使用的是assgin，这里就不需要了，因为
     * ListenerConsumer 会调用 initPartitionsIfNeeded方法进行偏量的初始化
     *
     * subscribe模式自动分配分区，是无法确定一个消费者每次重启都是分配的同一个分区的，所以就无法通过seek初始化偏移量
     * @param callback
     */
    @Override
    public void registerSeekCallback(ConsumerSeekCallback callback) {

    }

    @Override
    public void onPartitionsAssigned(Map<TopicPartition, Long> assignments, ConsumerSeekCallback callback) {

    }

    @Override
    public void onIdleContainer(Map<TopicPartition, Long> assignments, ConsumerSeekCallback callback) {

    }
}
