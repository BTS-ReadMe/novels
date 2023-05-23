package com.readme.novels.episodes.messagequeue;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.readme.novels.episodes.dto.EpisodesDto;
import com.readme.novels.episodes.dto.PlusViewsDto;
import com.readme.novels.novels.dto.NovelsDto;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class EpisodesKafkaProducer {
    private final KafkaTemplate<String, String> kafkaTemplate;

    // topic : addEpisodes
    public void addEpisodes(String topic, EpisodesDto episodesDto) {
        ObjectMapper mapper = new ObjectMapper();
        String jsonInString = "";

        // LocalDateTime, Date 객체를 그대로 보내기 위한 설정
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        try {
            jsonInString = mapper.writeValueAsString(episodesDto);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        kafkaTemplate.send(topic, jsonInString);
        log.info("topic : {}, data : {} ",topic,jsonInString);
    }

    // topic : plusViewCount
    public void plusViewCount(String topic, PlusViewsDto plusViewsDto) {
        ObjectMapper mapper = new ObjectMapper();
        String jsonInString = "";

        try {
            jsonInString = mapper.writeValueAsString(plusViewsDto);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        kafkaTemplate.send(topic, jsonInString);

        log.info("조회수 증가 이벤트 메시지 전송 완료!!!");
    }

    // topic : sendViewCount
    public void sendViewCount(String topic, Map<String, Integer> novelViewCountMap) {
        ObjectMapper mapper = new ObjectMapper();
        String jsonInString = "";

        try {
            jsonInString = mapper.writeValueAsString(novelViewCountMap);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        kafkaTemplate.send(topic, jsonInString);
        log.info("topic : {}, data : {} ",topic,jsonInString);
    }

    // topic : updateEpisodes
    public void updateEpisodes(String topic, EpisodesDto episodesDto) {
        ObjectMapper mapper = new ObjectMapper();
        String jsonInString = "";

        // LocalDateTime, Date 객체를 그대로 보내기 위한 설정
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        try {
            jsonInString = mapper.writeValueAsString(episodesDto);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        kafkaTemplate.send(topic, jsonInString);
        log.info("topic : {}, data : {} ",topic,jsonInString);
    }

    // topic : deleteEpisodes
    public void deleteEpisodes(String topic, Long episodesId) {

        kafkaTemplate.send(topic, episodesId.toString());
        log.info("topic : {}, data : {} ",topic,episodesId.toString());
    }

}
