package com.readme.novels.config;

import com.readme.novels.episodes.dto.EpisodesDeleteKafkaDto;
import com.readme.novels.episodes.dto.EpisodesKafkaDto;
import com.readme.novels.episodes.dto.PlusViewsKafkaDto;
import com.readme.novels.novels.dto.NovelsDeleteKafkaDto;
import com.readme.novels.novels.dto.NovelsKafkaDto;
import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

@EnableKafka
@Configuration
@RequiredArgsConstructor
public class KafkaProducerConfig {

    private final Environment environment;

    @Bean
    public Map<String, Object> producerConfigs() {
        return CommonJsonSerializer.getStringObjectMap(environment.getProperty("kafka-config"));
    }

    @Bean
    public ProducerFactory<String, EpisodesKafkaDto> EpisodesProducerFactory() {
        return new DefaultKafkaProducerFactory<>(producerConfigs());
    }

    @Bean
    public KafkaTemplate<String, EpisodesKafkaDto> EpisodesKafkaTemplate() {
        return new KafkaTemplate<>(EpisodesProducerFactory());
    }

    @Bean
    public ProducerFactory<String, EpisodesDeleteKafkaDto> EpisodesDeleteProducerFactory() {
        return new DefaultKafkaProducerFactory<>(producerConfigs());
    }

    @Bean
    public KafkaTemplate<String, EpisodesDeleteKafkaDto> EpisodesDeleteKafkaTemplate() {
        return new KafkaTemplate<>(EpisodesDeleteProducerFactory());
    }

    @Bean
    public ProducerFactory<String, PlusViewsKafkaDto> EpisodesPlusViewProducerFactory() {
        return new DefaultKafkaProducerFactory<>(producerConfigs());
    }

    @Bean
    public KafkaTemplate<String, PlusViewsKafkaDto> EpisodesPlusViewTemplate() {
        return new KafkaTemplate<>(EpisodesPlusViewProducerFactory());
    }

    @Bean
    public ProducerFactory<String, NovelsKafkaDto> NovelsProducerFactory() {
        return new DefaultKafkaProducerFactory<>(producerConfigs());
    }

    @Bean
    public KafkaTemplate<String, NovelsKafkaDto> NovelsKafkaTemplate() {
        return new KafkaTemplate<>(NovelsProducerFactory());
    }

    @Bean
    public ProducerFactory<String, NovelsDeleteKafkaDto> NovelsDeleteProducerFactory() {
        return new DefaultKafkaProducerFactory<>(producerConfigs());
    }

    @Bean
    public KafkaTemplate<String, NovelsDeleteKafkaDto> NovelsDeleteKafkaTemplate() {
        return new KafkaTemplate<>(NovelsDeleteProducerFactory());
    }

    @Bean
    public ProducerFactory<String, Object> getPurchasedInfoResultDtoProducerFactory(){
        Map<String, Object> properties = new HashMap<>();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, environment.getProperty("kafka-config"));
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        return new DefaultKafkaProducerFactory<>(properties);
    }

    @Bean
    public KafkaTemplate<String, Object> getPurchasedInfoResultDtoKafkaTemplate() {
        return new KafkaTemplate<>(getPurchasedInfoResultDtoProducerFactory());
    }

//    @Bean
//    public ProducerFactory<String, EpisodesKafkaDto> EpisodesProducerFactory() {
//        Map<String, Object> properties = new HashMap<>();
//        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, environment.getProperty("kafka-config"));
//        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
//        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
//
//        return new DefaultKafkaProducerFactory<>(properties);
//    }
//
//    @Bean
//    public KafkaTemplate<String, EpisodesKafkaDto> EpisodeskafkaTemplate() {
//        return new KafkaTemplate<String, EpisodesKafkaDto>(EpisodesProducerFactory());
//    }
//
//    @Bean
//    public ProducerFactory<String, EpisodesDeleteKafkaDto> EpisodesDeleteProducerFactory() {
//        Map<String, Object> properties = new HashMap<>( );
//        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, environment.getProperty("kafka-config"));
//        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
//        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
//
//        return new DefaultKafkaProducerFactory<>(properties);
//    }
//
//    @Bean
//    public KafkaTemplate<String, EpisodesDeleteKafkaDto> EpisodesDeletekafkaTemplate() {
//        return new KafkaTemplate<String, EpisodesDeleteKafkaDto>(EpisodesDeleteProducerFactory());
//    }
//
//    @Bean
//    public ProducerFactory<String, PlusViewsKafkaDto> EpisodesPlusViewProducerFactory() {
//        Map<String, Object> properties = new HashMap<>();
//        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, environment.getProperty("kafka-config"));
//        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
//        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
//
//        return new DefaultKafkaProducerFactory<>(properties);
//    }
//
//    @Bean
//    public KafkaTemplate<String, PlusViewsKafkaDto> EpisodesPlusViewTemplate() {
//        return new KafkaTemplate<String, PlusViewsKafkaDto>(EpisodesPlusViewProducerFactory());
//    }
//
//    @Bean
//    public ProducerFactory<String, NovelsKafkaDto> NovelsProducerFactory() {
//        Map<String, Object> properties = new HashMap<>();
//        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, environment.getProperty("kafka-config"));
//        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
//        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
//
//        return new DefaultKafkaProducerFactory<>(properties);
//    }
//
//    @Bean
//    public KafkaTemplate<String, NovelsKafkaDto> NovelsKafkaTemplate() {
//        return new KafkaTemplate<String, NovelsKafkaDto>(NovelsProducerFactory());
//    }
//
//    @Bean
//    public ProducerFactory<String, NovelsDeleteKafkaDto> NovelsDeleteProducerFactory() {
//        Map<String, Object> properties = new HashMap<>();
//        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, environment.getProperty("kafka-config"));
//        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
//        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
//
//        return new DefaultKafkaProducerFactory<>(properties);
//    }
//
//    @Bean
//    public KafkaTemplate<String, NovelsDeleteKafkaDto> NovelsDeleteKafkaTemplate() {
//        return new KafkaTemplate<String, NovelsDeleteKafkaDto>(NovelsDeleteProducerFactory());
//    }


}
