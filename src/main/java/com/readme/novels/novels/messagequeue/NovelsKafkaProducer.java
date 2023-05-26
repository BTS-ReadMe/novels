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
    private final KafkaTemplate<String, NovelsKafkaDto> novelsKafkaTemplate;
    private final KafkaTemplate<String, NovelsDeleteKafkaDto> novelsDeleteKafkaTemplate;

    // topic : addNovels
    public void addNovels(String topic, NovelsKafkaDto novelsKafkaDto) {

        novelsKafkaTemplate.send(topic, novelsKafkaDto);
        log.info("topic : {}, data : {} ",topic,novelsKafkaDto);

    }

    // topic : updateNovels
    public void updateNovels(String topic, NovelsKafkaDto novelsKafkaDto) {

        novelsKafkaTemplate.send(topic, novelsKafkaDto);
        log.info("topic : {}, data : {} ",topic,novelsKafkaDto);
    }

    // topic : deleteNovels
    public void deleteNovels(String topic, NovelsDeleteKafkaDto novelsDeleteKafkaDto) {

        novelsDeleteKafkaTemplate.send(topic, novelsDeleteKafkaDto);
        log.info("topic : {}, data : {} ",topic,novelsDeleteKafkaDto);
    }
}
