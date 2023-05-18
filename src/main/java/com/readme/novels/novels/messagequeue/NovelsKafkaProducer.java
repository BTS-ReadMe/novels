package com.readme.novels.novels.messagequeue;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.readme.novels.novels.dto.NovelsDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class NovelsKafkaProducer {
    private final KafkaTemplate<String, String> kafkaTemplate;

    public void addNovels(String topic, NovelsDto novelsDto) {
        ObjectMapper mapper = new ObjectMapper();
        String jsonInString = "";

        // LocalDateTime, Date 객체를 그대로 보내기 위한 설정
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        try {
            jsonInString = mapper.writeValueAsString(novelsDto);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        kafkaTemplate.send(topic, jsonInString);
//        log.info("topic : {}, data : {} ",topic,jsonInString);

    }
}
