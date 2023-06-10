package com.readme.novels.messageQueue.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.readme.novels.messageQueue.dto.EmojiStatusDto;
import com.readme.novels.sseEmitter.repository.EmitterRepository;
import com.readme.novels.sseEmitter.service.NotificationService;
import java.io.IOException;
import java.util.Map;
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
    private final NotificationService notificationService;

    @KafkaListener(id = "emojiStatus", topics = "emojiStatus")
    public void listen(String kafkaMessage) throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        EmojiStatusDto emojiStatusDto = mapper.readValue(kafkaMessage, EmojiStatusDto.class);

        Map<String, SseEmitter> result = emitterRepository.findAllStartById(
            String.valueOf(emojiStatusDto.getEpisodeId()));

        log.info("--------------------------");
        log.info("연결된 user 수 : " + result.size());

        for (Map.Entry<String, SseEmitter> entry : result.entrySet()) {

            log.info("[send] " + entry.getKey());
            notificationService.sendToClient(entry.getValue(), entry.getKey(), emojiStatusDto);
        }

    }
}
