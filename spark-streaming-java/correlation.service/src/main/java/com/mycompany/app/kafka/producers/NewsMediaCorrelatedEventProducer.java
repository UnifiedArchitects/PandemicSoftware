package com.mycompany.app.kafka.producers;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

/**
 *
 * @author skakk
 */
public class NewsMediaCorrelatedEventProducer {
    String topicName = "newscorrelated";
    Properties kafkaParams = new Properties();
    Producer<String, String> producer;
    
    public NewsMediaCorrelatedEventProducer(){
        Map<String, Object> kafkaParams = new HashMap<>();
        kafkaParams.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "node-master:9092,node1:19092,node2:29092");
        kafkaParams.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        kafkaParams.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        kafkaParams.put("group.id", "pandemic.group.news");
        kafkaParams.put("auto.offset.reset", "latest");
        kafkaParams.put("enable.auto.commit", false);
        
        producer = new KafkaProducer<>(kafkaParams);
    }
    
    public void sendNewsCorrelatedEvent(String val){
        this.producer.send(new ProducerRecord<>(this.topicName, val));
    }
    
    public void close(){
        this.producer.close();
    }
}
