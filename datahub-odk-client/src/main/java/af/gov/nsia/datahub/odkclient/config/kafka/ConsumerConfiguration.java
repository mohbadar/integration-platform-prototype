///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package af.gov.nsia.datahub.odkclient.config.kafka;
//
//import lombok.extern.slf4j.Slf4j;
//import org.apache.kafka.common.serialization.ByteArrayDeserializer;
//import org.apache.kafka.common.serialization.StringDeserializer;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
//import org.springframework.kafka.core.ConsumerFactory;
//import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
//import org.springframework.kafka.support.serializer.JsonDeserializer;
//
///**
// *
// * @author hp 2018
// */
//@Configuration
//@Slf4j
//public class ConsumerConfiguration {
//
//    @Autowired
//    private KafkaProperties kafkaProperties;
//
//    @Value("${tpd.topic-name}")
//    private String topicName;
//
//    // Consumer configuration
//    // If you only need one kind of deserialization, you only need to set the
//    // Consumer configuration properties. Uncomment this and remove all others below.
////    @Bean
////    public Map<String, Object> consumerConfigs() {
////        Map<String, Object> props = new HashMap<>(
////                kafkaProperties.buildConsumerProperties()
////        );
////        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
////                StringDeserializer.class);
////        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
////                JsonDeserializer.class);
////        props.put(ConsumerConfig.GROUP_ID_CONFIG,
////                "tpd-loggers");
////
////        return props;
////    }
//    @Bean
//    public ConsumerFactory<String, Object> consumerFactory() {
//        final JsonDeserializer<Object> jsonDeserializer = new JsonDeserializer<>();
//        jsonDeserializer.addTrustedPackages("*");
//        return new DefaultKafkaConsumerFactory<>(
//                kafkaProperties.buildConsumerProperties(), new StringDeserializer(), jsonDeserializer
//        );
//    }
//
//    @Bean
//    public ConcurrentKafkaListenerContainerFactory<String, Object> kafkaListenerContainerFactory() {
//        ConcurrentKafkaListenerContainerFactory<String, Object> factory
//                = new ConcurrentKafkaListenerContainerFactory<>();
//        factory.setConsumerFactory(consumerFactory());
//
//        return factory;
//    }
//
//    // String Consumer Configuration
//    @Bean
//    public ConsumerFactory<String, String> stringConsumerFactory() {
//        return new DefaultKafkaConsumerFactory<>(
//                kafkaProperties.buildConsumerProperties(),
//                new StringDeserializer(),
//                new StringDeserializer()
//        );
//    }
//
//    @Bean
//    public ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerStringContainerFactory() {
//        ConcurrentKafkaListenerContainerFactory<String, String> factory
//                = new ConcurrentKafkaListenerContainerFactory<>();
//        factory.setConsumerFactory(stringConsumerFactory());
//
//        return factory;
//    }
//
//    // Byte Array Consumer Configuration
//    @Bean
//    public ConsumerFactory<String, byte[]> byteArrayConsumerFactory() {
//        return new DefaultKafkaConsumerFactory<>(
//                kafkaProperties.buildConsumerProperties(),
//                new StringDeserializer(),
//                new ByteArrayDeserializer()
//        );
//    }
//
//    @Bean
//    public ConcurrentKafkaListenerContainerFactory<String, byte[]> kafkaListenerByteArrayContainerFactory() {
//        ConcurrentKafkaListenerContainerFactory<String, byte[]> factory
//                = new ConcurrentKafkaListenerContainerFactory<>();
//        factory.setConsumerFactory(byteArrayConsumerFactory());
//        return factory;
//    }
//
//}
