package com.readme.novels.episodes.messagequeue;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.readme.novels.episodes.dto.EpisodesDeleteKafkaDto;
import com.readme.novels.episodes.dto.EpisodesDto;
import com.readme.novels.episodes.dto.EpisodesKafkaDto;
import com.readme.novels.episodes.dto.PlusViewsDto;
import io.swagger.v3.core.util.Json;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class EpisodesKafkaProducer {
    private final KafkaTemplate<String, Object> kafkaTemplate;

    // topic : addEpisodes
    public void addEpisodes(String topic, EpisodesKafkaDto episodesKafkaDto) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        JsonNode jsonNode = mapper.valueToTree(episodesKafkaDto);

        kafkaTemplate.send(topic, jsonNode);
        log.info("topic : {}, data : {} ",topic,jsonNode);
    }

    // topic : plusViewCount
    public void plusViewCount(String topic, PlusViewsDto plusViewsDto) {
        ObjectMapper mapper = new ObjectMapper();

        JsonNode jsonNode = mapper.valueToTree(plusViewsDto);
        kafkaTemplate.send(topic, jsonNode);
        log.info("topic : {}, data : {} ",topic,jsonNode);
    }

//    // topic : sendViewCount
//    public void sendViewCount(String topic, Map<String, Integer> novelViewCountMap) {
//        ObjectMapper mapper = new ObjectMapper();
//
//
//        String jsonInString = "";
//
//        try {
//            jsonInString = mapper.writeValueAsString(novelViewCountMap);
//        } catch (JsonProcessingException e) {
//            throw new RuntimeException(e);
//        }
//
//        kafkaTemplate.send(topic, jsonInString);
//        log.info("topic : {}, data : {} ",topic,jsonInString);
//    }

    // topic : updateEpisodes
    public void updateEpisodes(String topic, EpisodesKafkaDto episodesKafkaDto) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        JsonNode jsonNode = mapper.valueToTree(episodesKafkaDto);

        kafkaTemplate.send(topic, jsonNode);
        log.info("topic : {}, data : {} ",topic,jsonNode);
    }

    // topic : deleteEpisodes
    public void deleteEpisodes(String topic, EpisodesDeleteKafkaDto episodesDeleteKafkaDto) {
        ObjectMapper mapper = new ObjectMapper();

        JsonNode jsonNode = mapper.valueToTree(episodesDeleteKafkaDto);

        kafkaTemplate.send(topic, jsonNode);
        log.info("topic : {}, data : {} ",topic,jsonNode);
    }

}
