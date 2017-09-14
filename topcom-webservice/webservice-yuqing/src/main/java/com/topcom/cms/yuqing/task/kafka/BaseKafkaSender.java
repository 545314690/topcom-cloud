package com.topcom.cms.yuqing.task.kafka;

import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Properties;

@Component
public abstract class BaseKafkaSender {
    private Properties props = new Properties();

    @Value("${kafka.uri}")
    private String kafkaServerUri = "hadoop02:9092";
    volatile private Producer<String, String> producer;
    public BaseKafkaSender() {

    }

    @PostConstruct
    void init(){
        props.put("bootstrap.servers", kafkaServerUri);
        props.put("acks", "all");
        props.put("retries", 0);
        props.put("batch.size", 16384);
        props.put("linger.ms", 1);
        props.put("buffer.memory", 33554432);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        producer = new org.apache.kafka.clients.producer.KafkaProducer<>(props);
    }
    public Properties getProps() {
        return props;
    }

    public void setProps(Properties props) {
        this.props = props;
    }



    public Producer<String, String> getProducer() {
        if (producer == null)
            producer = new org.apache.kafka.clients.producer.KafkaProducer<>(props);
        return producer;
    }

    public void setProducer(Producer<String, String> producer) {
        this.producer = producer;
    }
}