package by.bntu.fitr.profilerclient.core.mapper;


import by.bntu.fitr.profilerclient.api.entity.ListenerSettingsEntity;
import by.bntu.fitr.profilerclient.core.constant.KafkaConstant;
import org.springframework.stereotype.Component;

import java.util.Properties;

@Component
public class KafkaConsumerPropertyMapper {

    public Properties mapToKafkaTimedMetricConsumerProperties(ListenerSettingsEntity listenerSettings) {
        Properties properties = new Properties();
        properties.put(KafkaConstant.BOOTSTRAP_SERVER, listenerSettings.getBootstrapServer());
        properties.put(KafkaConstant.GROUP_ID, listenerSettings.getGroupId());
        properties.put(KafkaConstant.KEY_DESERIALIZER, listenerSettings.getKeyDeserializer());
        properties.put(KafkaConstant.VALUE_DESERIALIZER, listenerSettings.getValueDeserializer());
        properties.put(KafkaConstant.SESSION_TIMEOUT_MS, listenerSettings.getSessionTimeout());
        properties.put(KafkaConstant.HEART_BEAT_INTERVAL_MS, listenerSettings.getHeartBeatInterval());
        return properties;
    }
}
