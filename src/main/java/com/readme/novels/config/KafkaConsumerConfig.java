package com.readme.novels.config;

import com.readme.novels.episodes.dto.EpisodesKafkaDto;
import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer;
import org.springframework.kafka.support.serializer.JsonDeserializer;

@EnableKafka
@Configuration
@RequiredArgsConstructor
public class KafkaConsumerConfig {

    private final Environment environment;

//    @Bean
//    public ConsumerFactory<String, EpisodesKafkaDto> consumerFactory() {
//        Map<String, Object> properties = new HashMap<>();
//        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, environment.getProperty("kafka-config"));
//        properties.put(ConsumerConfig.GROUP_ID_CONFIG, "consumeGroupId");
//        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
//        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
//
//        return new DefaultKafkaConsumerFactory<>(properties);
//    }
//
//    @Bean
//    public ConcurrentKafkaListenerContainerFactory<String, EpisodesKafkaDto> kafkaListenerContainerFactory() {
//        ConcurrentKafkaListenerContainerFactory<String, EpisodesKafkaDto> kafkaListenerContainerFactory
//            = new ConcurrentKafkaListenerContainerFactory<>();
//        kafkaListenerContainerFactory.setConsumerFactory(consumerFactory());
//
//
//        return kafkaListenerContainerFactory;
//    }

    @Bean
    public ConsumerFactory<String, EpisodesKafkaDto> consumerFactory() {
        Map<String, Object> properties = new HashMap<>();
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, environment.getProperty("kafka-config"));
        properties.put(ConsumerConfig.GROUP_ID_CONFIG, "consumeGroupId");
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, Object.class);

        return new DefaultKafkaConsumerFactory<>(
            properties,
            new StringDeserializer(),
            new ErrorHandlingDeserializer<>(new JsonDeserializer<>(EpisodesKafkaDto.class))
        );
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, EpisodesKafkaDto> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, EpisodesKafkaDto> kafkaListenerContainerFactory
            = new ConcurrentKafkaListenerContainerFactory<>();
        kafkaListenerContainerFactory.setConsumerFactory(consumerFactory());

        return kafkaListenerContainerFactory;
    }


}
