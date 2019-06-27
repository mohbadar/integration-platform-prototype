package af.gov.nsia.datahub.connector.config;

///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package af.gov.nsia.datahub.odkclient.config;
//
//import af.gov.nsia.datahub.odkclient.config.avro.AvroSerializer;
//import af.gov.nsia.datahub.odkclient.model.OdkForm;
//import af.gov.nsia.datahub.odkclient.service.OdkFormProducerService;
//import java.util.HashMap;
//import java.util.Map;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.kafka.clients.producer.ProducerConfig;
//import org.apache.kafka.clients.producer.internals.Sender;
//import org.apache.kafka.common.serialization.StringSerializer;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.kafka.core.DefaultKafkaProducerFactory;
//import org.springframework.kafka.core.KafkaTemplate;
//import org.springframework.kafka.core.ProducerFactory;
//
///**
// *
// * @author hp 2018
// */
//@Configuration
//@Slf4j
//public class OdkFormSenderConfig {
//
//    @Value("${kafka.bootstrap-servers}")
//    private String bootstrapServers;
//
//    @Bean
//    public Map<String, Object> producerConfigs() {
//        Map<String, Object> props = new HashMap<>();
//        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
//        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
//        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, AvroSerializer.class);
//
//        return props;
//    }
//
//    @Bean
//    public ProducerFactory<String, OdkForm> producerFactory() {
//        return new DefaultKafkaProducerFactory<>(producerConfigs());
//    }
//
//    @Bean
//    public KafkaTemplate<String, OdkForm> kafkaTemplate() {
//        return new KafkaTemplate<>(producerFactory());
//    }
//
////    @Bean
////    public OdkFormProducerService odkFormProducerService() {
////        return new OdkFormProducerService();
////    }
//
//}
