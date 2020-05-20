package com.daiyanping.demo.kafka.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;

import java.util.HashMap;
import java.util.Properties;

/**
 * @ClassName KafKaConfig
 * @Description TODO
 * @Author daiyanping
 * @Date 2020-05-20
 * @Version 0.1
 */
@Configuration
public class KafKaConfig {

    /**
     * 自定义DefaultKafkaProducerFactory 参数，不使用自动配置中的参数
     * @return
     */
    public KafkaTemplate myKafKaTemplate() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("bootstrap.servers","192.168.140.129:9092");
        map.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        map.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        /**
         * ----------------TODO 更多发送配置（重要的）----------------------------
         */

        /**
         * ack 0,1,all 0表示发送就不管了，1表示发送到首领就表示发送成功，all表示发送到首领和副本都成功才表示发送成功
         * 如果有多个副本，配置all,性能会比较差
         */
        map.put("acks","1");
        /**
         * 一个批次可以使用的内存大小 缺省16384(16k)
         */
        map.put("batch.size",16384);
        /**
         * 指定了生产者在发送批次前等待更多消息加入批次的时间,  缺省0
         * 0就表示不等待，有消息就发送了（也就是一条条走），如果想要性能高，可以配置等50ms
         * 然后一起发送，配合batch.size配置一起使用
         */
        map.put("linger.ms",0L);
        /**
         * 控制生产者发送请求最大大小,默认1M （这个参数和Kafka主机的message.max.bytes 参数有关系）
         * 如果比服务器的大就会发送数据丢失
         */
        map.put("max.request.size",1 * 1024 * 1024);

        /**
         * -----------------TODO 更多发送配置（非重要的）-------------------------
         */
        /**
         * 生产者内存缓冲区大小
         */
        map.put("buffer.memory",32 * 1024 * 1024L);
        /**
         * 重发消息次数,默认int max，在多分区下顺序保证不好做，存在重试机制，导致第一条消息失败重试，排在了第二天消息的后面
         * 但是不重试有存在消息丢失的风险
         */
        map.put("retries",0);
        map.put("request.timeout.ms",30 * 1000);//客户端将等待请求的响应的最大时间 默认30秒
        map.put("max.block.ms",60*1000);//最大阻塞时间，超过则抛出异常 缺省60000ms

        map.put("compression.type","none"); // 于压缩数据的压缩类型。默认是无压缩 ,none、gzip、snappy
        DefaultKafkaProducerFactory defaultKafkaProducerFactory = new DefaultKafkaProducerFactory<String, String>(map);
        KafkaTemplate kafkaTemplate = new KafkaTemplate<>(defaultKafkaProducerFactory);
        return kafkaTemplate;
    }
}
