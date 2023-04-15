package by.bntu.fitr.profilerclient.core.kafka;

import by.bntu.fitr.profilerclient.api.entity.ListenerEntity;
import by.bntu.fitr.profilerclient.api.service.MetricService;
import by.bntu.fitr.profilerclient.core.mapper.KafkaConsumerPropertyMapper;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.Duration;
import java.util.Collections;

public class KafkaJVMMetricConsumer extends Thread {
    private ListenerEntity listenerEntity;
    private KafkaConsumerPropertyMapper kafkaConsumerPropertyMapper;
    private MetricService metricService;
    private String uniqueThreadName;


    @Override
    public void run() {
        listen();
    }

    @SuppressWarnings(value = "all")
    public void listen() {
        KafkaConsumer<String, String> jvmMetricConsumer = new KafkaConsumer<>(kafkaConsumerPropertyMapper.mapToKafkaTimedMetricConsumerProperties(listenerEntity.getListenerSettings()));
        jvmMetricConsumer.subscribe(Collections.singletonList(listenerEntity.getTopicName()));
        Thread.currentThread().setName(uniqueThreadName);

        try {
            while (true) {
                ConsumerRecords<String, String> records = jvmMetricConsumer.poll(Duration.ofSeconds(1));
                for (ConsumerRecord<String, String> record : records) {
                    metricService.saveJVMMetric(record.value());
                }
            }

        } finally {
            jvmMetricConsumer.close();
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
