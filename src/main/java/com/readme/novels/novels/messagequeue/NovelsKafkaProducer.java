package com.readme.novels.novels.messagequeue;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.readme.novels.novels.dto.NovelsDeleteKafkaDto;
import com.readme.novels.novels.dto.NovelsDto;
import com.readme.novels.novels.dto.NovelsKafkaDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class NovelsKafkaProducer {
    private final KafkaTemplate<String, Object> kafkaTemplate;

    // topic : addNovels
    public void addNovels(String topic, NovelsKafkaDto novelsKafkaDto) {
        ObjectMapper mapper = new ObjectMapper();

        // LocalDateTime, Date 객체를 그대로 보내기 위한 설정
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        JsonNode jsonNode = mapper.valueToTree(novelsKafkaDto);

        kafkaTemplate.send(topic, jsonNode);
        log.info("topic : {}, data : {} ",topic,jsonNode);

    }

    // topic : updateNovels
    public void updateNovels(String topic, NovelsKafkaDto novelsKafkaDto) {
        ObjectMapper mapper = new ObjectMapper();

        // LocalDateTime, Date 객체를 그대로 보내기 위한 설정
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        JsonNode jsonNode = mapper.valueToTree(novelsKafkaDto);

        kafkaTemplate.send(topic, jsonNode);
        log.info("topic : {}, data : {} ",topic,jsonNode);
    }

    // topic : deleteNovels
    public void deleteNovels(String topic, NovelsDeleteKafkaDto novelsDeleteKafkaDto) {
        ObjectMapper mapper = new ObjectMapper();

        JsonNode jsonNode = mapper.valueToTree(novelsDeleteKafkaDto);

        kafkaTemplate.send(topic, jsonNode);
        log.info("topic : {}, data : {} ",topic,jsonNode);
    }
}
