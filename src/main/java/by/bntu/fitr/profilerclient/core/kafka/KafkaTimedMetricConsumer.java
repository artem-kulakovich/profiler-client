package by.bntu.fitr.profilerclient.core.kafka;

import by.bntu.fitr.profilerclient.api.entity.ListenerEntity;

import by.bntu.fitr.profilerclient.api.service.MetricService;
import by.bntu.fitr.profilerclient.core.mapper.KafkaConsumerPropertyMapper;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Collections;

@Component
public class KafkaTimedMetricConsumer extends Thread {
    private ListenerEntity listenerEntity;
    private KafkaConsumerPropertyMapper kafkaConsumerPropertyMapper;
    private MetricService metricService;
    private String uniqueThreadName;


    @Override
    public void run() {
        listen();
    }

    public void listen() {
        KafkaConsumer<String, String> timedMetricConsumer = new KafkaConsumer<>(kafkaConsumerPropertyMapper.mapToKafkaTimedMetricConsumerProperties(listenerEntity.getListenerSettings()));
        timedMetricConsumer.subscribe(Collections.singletonList(listenerEntity.getTopicName()));
        Thread.currentThread().setName(uniqueThreadName);

        try {
            while (true) {
                ConsumerRecords<String, String> records = timedMetricConsumer.poll(Duration.ofSeconds(1));
                for (ConsumerRecord<String, String> record : records) {
                    metricService.saveTimedMetrics(record.value());
                }
            }

        } finally {
            timedMetricConsumer.close();
        }
    }

    public void setListenerEntity(ListenerEntity listenerEntity) {
        this.listenerEntity = listenerEntity;
    }

    @Autowired
    public void setKafkaConsumerPropertyMapper(KafkaConsumerPropertyMapper kafkaConsumerPropertyMapper) {
        this.kafkaConsumerPropertyMapper = kafkaConsumerPropertyMapper;
    }

    @Autowired
    public void setMetricService(MetricService metricService) {
        this.metricService = metricService;
    }

    public void setUniqueThreadName(String uniqueThreadName) {
        this.uniqueThreadName = uniqueThreadName;
    }
}
