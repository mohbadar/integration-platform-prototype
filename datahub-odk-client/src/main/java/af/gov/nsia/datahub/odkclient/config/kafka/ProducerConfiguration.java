/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package af.gov.nsia.datahub.odkclient.config.kafka;

import af.gov.nsia.datahub.odkclient.config.avro.AvroSerializer;
import af.gov.nsia.datahub.odkclient.model.OdkForm;
import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

/**
 *
 * @author hp 2018
 */
@Configuration
@Slf4j
public class ProducerConfiguration {

    @Autowired
    private KafkaProperties kafkaProperties;

    @Value("${kafka.bootstrap-servers}")
    private String bootstrapServers;

    @Value("${tpd.topic-name}")
    private String topicName;

    //producer configuration
    @Bean
    public Map<String, Object> producerConfigs() {
        Map<String, Object> props = new HashMap<>(kafkaProperties.buildProducerProperties());
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
                StringSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
                JsonSerializer.class.getName());
//        props.put(KafkaAvroSerializerConfig.SCHEMA_REGISTRY_URL_CONFIG,"http://localhost:8081");
        return props;
    }

    @Bean
    public ProducerFactory<String, OdkForm> producerFactory() {
        return new DefaultKafkaProducerFactory<>(producerConfigs());
    }

    @Bean
    public KafkaTemplate<String, OdkForm> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }
//
//    @Bean
//    public NewTopic adviceTopic() {
//        return new NewTopic(topicName, 3, (short) 1);
//    }

}
