package com.readme.novels.episodes.messagequeue;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.readme.novels.episodes.dto.EpisodesDeleteKafkaDto;
import com.readme.novels.episodes.dto.EpisodesKafkaDto;
import com.readme.novels.episodes.service.EpisodesService;
import io.swagger.v3.core.util.Json;
import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.KafkaListenerEndpointRegistry;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class EpisodesKafkaConsumer {

//    private final KafkaListenerEndpointRegistry kafkaListenerEndpointRegistry;
//    private static final Map<String, Integer> episodeViewCountMap = new HashMap<>();
//    private static final Map<String, Integer> novelViewCountMap = new HashMap<>();
//    private final EpisodesService episodesService;
//    private final EpisodesKafkaProducer episodesKafkaProducer;

//    @KafkaListener(topics = "addEpisodes", groupId = "consumeGroupId")
//    public void kafkaEpisodesTest(EpisodesKafkaDto episodesKafkaDto) {
//        log.info("수신 메세지 : " + episodesKafkaDto );
//        log.info("id : " + episodesKafkaDto.getId());
//    }

//    @KafkaListener(topics = "updateEpisodes")
//    public void kafkaEpisodeUpdateTest(EpisodesKafkaDto episodesKafkaDto) {
//        log.info("수신 메세지 : " + episodesKafkaDto );
//        log.info("id : " + episodesKafkaDto.getId());
//    }
//
//    @KafkaListener(topics = "deleteEpisodes")
//    public void kafkaEpisodeDeleteTest(EpisodesDeleteKafkaDto episodesDeleteKafkaDto) {
//        log.info("수신 메세지 : " + episodesDeleteKafkaDto );
//        log.info("id : " + episodesDeleteKafkaDto.getId());
//    }


//    @KafkaListener(id = "viewCountListener", topics = "plusViewCount")
//    public void saveViewCount(String kafkaMessage) {
//        Map<String, Object> kafkaMap = new HashMap<>();
//        ObjectMapper mapper = new ObjectMapper();
//
//        try {
//            kafkaMap = mapper.readValue(kafkaMessage, new TypeReference<Map<String, Object>>() {
//            });
//        } catch (JsonProcessingException e) {
//            throw new RuntimeException(e);
//        }
//
//        // 에피소드별 조회수 집계 (mysql 저장용)
//        if (episodeViewCountMap.get(kafkaMap.get("id").toString()) == null) {
//            episodeViewCountMap.put(kafkaMap.get("id").toString(), 1);
//        } else {
//            episodeViewCountMap.put(kafkaMap.get("id").toString(),
//                episodeViewCountMap.get(kafkaMap.get("id").toString()) + 1);
//        }
//
//        // 소설별 조회수 집계 (mongodb 전송용)
//        if (novelViewCountMap.get(kafkaMap.get("novelsId").toString()) == null) {
//            novelViewCountMap.put(kafkaMap.get("novelsId").toString(), 1);
//        } else {
//            novelViewCountMap.put(kafkaMap.get("novelsId").toString(),
//                novelViewCountMap.get(kafkaMap.get("novelsId").toString()) + 1);
//        }
//    }

//    @Scheduled(fixedRate = 60000) // 1분마다 실행
//    public void consumeViewCountListener() {
//        kafkaListenerEndpointRegistry.getListenerContainer("viewCountListener").start();
//
//        try {
//            Thread.sleep(5000);
//        } catch (InterruptedException e) {
//            Thread.currentThread().interrupt();
//        }
//
//        log.info("여기는 컨슈머 1분마다 실행 됨!");
//
//        log.info("현재 episodeViewCountMap 상태 : " + episodeViewCountMap);
//        log.info("현재 novelViewCountMap 상태 : " + novelViewCountMap);
//
//        // mysql 에피소드별 조회수 일괄 업데이트
//        if (episodeViewCountMap != null) {
//            episodeViewCountMap.forEach((episodesId, totalCount) ->
//                episodesService.plusViewsCount(Long.parseLong(episodesId), totalCount)
//            );
//        }
//
//        // mongodb 소설별 조회수 전송
//        if (novelViewCountMap != null) {
//            episodesKafkaProducer.sendViewCount("sendViewCount", novelViewCountMap);
//        }
//
//        episodeViewCountMap.clear();
//        novelViewCountMap.clear();
//
//        kafkaListenerEndpointRegistry.getListenerContainer("viewCountListener").stop();
//    }


}
