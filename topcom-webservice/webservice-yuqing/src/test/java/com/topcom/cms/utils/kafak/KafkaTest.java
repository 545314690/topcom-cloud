package com.topcom.cms.utils.kafak;

import com.topcom.cms.yuqing.domain.CustomSubject;
import com.topcom.cms.yuqing.task.kafka.BaseKafkaSender;
import com.topcom.cms.yuqing.task.kafka.KafkaSender;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.junit.Before;
import org.junit.Test;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.Properties;

/**
 * Created by lism on 17-6-17.
 */
public class KafkaTest {

    private static final String SUBJECT_TOPIC = "topic_subject";

    private Properties props = new Properties();
    @Before
    public void setUp(){

            props.put("bootstrap.servers", "192.168.0.105:9092");
//            props.put("bootstrap.servers", "115.29.108.81:9092");
            props.put("acks", "all");
            props.put("retries", 0);
            props.put("batch.size", 16384);
            props.put("linger.ms", 1);
            props.put("buffer.memory", 33554432);
            props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
            props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
    }

    @Test
    public void sendSubject(){
        KafkaSender kafkaSender = new KafkaSender();
        kafkaSender.setProps(props);
        CustomSubject subject = new CustomSubject();
        subject.setMustWord("江苏@幼儿园@爆炸");
        subject.setName("江苏幼儿园爆炸");
        subject.setShouldWord("爆炸");
        subject.setStartDate(DateUtils.addDays(new Date(),-5));
        subject.setEndDate(DateUtils.addDays(new Date(),30));
        for (int i = 0; i <1000 ; i++) {
            if(i%2==0) subject.setDeleted(true);
            else subject.setDeleted(false);
            kafkaSender.getProducer().send(new ProducerRecord<String, String>(SUBJECT_TOPIC, net.sf.json.JSONObject.fromObject(subject).toString()));
        }
    }
}
