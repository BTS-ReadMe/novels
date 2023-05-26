package com.readme.novels.episodes.messagequeue;

import com.readme.novels.episodes.dto.EpisodesDeleteKafkaDto;
import com.readme.novels.episodes.dto.EpisodesKafkaDto;
import com.readme.novels.episodes.dto.PlusViewsKafkaDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class EpisodesKafkaProducer {


    private final KafkaTemplate<String, EpisodesKafkaDto> episodesKafkaTemplate;
    private final KafkaTemplate<String, EpisodesDeleteKafkaDto> episodesDeleteKafkaTemplate;
    private final KafkaTemplate<String, PlusViewsKafkaDto> plusViewsKafkaTemplate;

    // topic : addEpisodes
    public void addEpisodes(String topic, EpisodesKafkaDto episodesKafkaDto) {

        episodesKafkaTemplate.send(topic, episodesKafkaDto);
        log.info("topic : {}, data : {} ",topic,episodesKafkaDto);
    }

    // topic : plusViewCount
    public void plusViewCount(String topic, PlusViewsKafkaDto plusViewsKafkaDto) {

        plusViewsKafkaTemplate.send(topic, plusViewsKafkaDto);
        log.info("topic : {}, data : {} ",topic, plusViewsKafkaDto);
    }
//
////    // topic : sendViewCount
////    public void sendViewCount(String topic, Map<String, Integer> novelViewCountMap) {
////        ObjectMapper mapper = new ObjectMapper();
////
////
////        String jsonInString = "";
////
////        try {
////            jsonInString = mapper.writeValueAsString(novelViewCountMap);
////        } catch (JsonProcessingException e) {
////            throw new RuntimeException(e);
////        }
////
////        kafkaTemplate.send(topic, jsonInString);
////        log.info("topic : {}, data : {} ",topic,jsonInString);
////    }
//
    // topic : updateEpisodes
    public void updateEpisodes(String topic, EpisodesKafkaDto episodesKafkaDto) {

        episodesKafkaTemplate.send(topic, episodesKafkaDto);
        log.info("topic : {}, data : {} ",topic,episodesKafkaDto);
    }

    // topic : deleteEpisodes
    public void deleteEpisodes(String topic, EpisodesDeleteKafkaDto episodesDeleteKafkaDto) {

        episodesDeleteKafkaTemplate.send(topic, episodesDeleteKafkaDto);
        log.info("topic : {}, data : {} ",topic,episodesDeleteKafkaDto);
    }

}
