package com.readme.novels.messageQueue.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.readme.novels.messageQueue.dto.EmojiStatusDto;
import com.readme.novels.sseEmitter.repository.EmitterRepository;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@Slf4j
@Service
@RequiredArgsConstructor
public class GetEmojiStatusConsumer {

    private final EmitterRepository emitterRepository;

    @KafkaListener(id = "emojiStatus", topics = "emojiStatus")
    public void listen(String kafkaMessage) throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        EmojiStatusDto emojiStatusDto = mapper.readValue(kafkaMessage, EmojiStatusDto.class);

        log.info("before");

        SseEmitter emitter = emitterRepository.findById(emojiStatusDto.getEpisodeId());
        emitter.send(SseEmitter.event().data(emojiStatusDto).name("emojiStatus"));

        log.info("after");
    }
}
