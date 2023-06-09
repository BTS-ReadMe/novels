package com.readme.novels.messageQueue.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.readme.novels.messageQueue.dto.EmojiStatusDto;
import com.readme.novels.sseEmitter.repository.EmitterRepository;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@Service
@RequiredArgsConstructor
public class GetEmojiStatusConsumer {

    private final EmitterRepository emitterRepository;

    @KafkaListener(id = "emojiStatus", topics = "emojiStatus")
    public void listen(String kafkaMessage) throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        EmojiStatusDto emojiStatusDto = mapper.readValue(kafkaMessage, EmojiStatusDto.class);

        SseEmitter emitter = emitterRepository.findById(emojiStatusDto.getEpisodeId());
        emitter.send(SseEmitter.event().data(emojiStatusDto).name("emojiStatus"));
    }
}
