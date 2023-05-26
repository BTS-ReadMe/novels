package com.readme.novels.novels.messagequeue;

import com.readme.novels.episodes.dto.EpisodesKafkaDto;
import com.readme.novels.novels.dto.NovelsKafkaDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class NovelsKafkaConsumer {

//    @KafkaListener(topics = "addNovels", groupId = "consumeGroupId2")
//    public void kafkaNovelsTest(NovelsKafkaDto novelsKafkaDto) {
//        log.info("수신 메세지 : " + novelsKafkaDto);
//        log.info("id : " + novelsKafkaDto.getId());
//    }
}
