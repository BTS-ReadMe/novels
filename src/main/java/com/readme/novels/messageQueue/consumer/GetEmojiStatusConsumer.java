package com.readme.novels.messageQueue.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.readme.novels.messageQueue.dto.EmojiStatusDto;
import com.readme.novels.sseEmitter.repository.EmitterRepository;
import com.readme.novels.sseEmitter.service.NotificationService;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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
            String.valueOf(emojiStatusDto.getEpisodeId()) + "_");

        log.info("--------------------------");
        List<Long> success = new ArrayList<>();
        List<Long> fail = new ArrayList<>();
        List<SseEmitter> emitters = new ArrayList<>();
        List<String> ids = new ArrayList<>();

        for (Map.Entry<String, SseEmitter> entry : result.entrySet()) {

            if (notificationService.sendToClient(entry.getValue(), entry.getKey(),
                emojiStatusDto)) {
                success.add(Long.valueOf(entry.getKey().split("_")[1]));
                emitters.add(entry.getValue());
                ids.add(entry.getKey());
            } else {
                fail.add(Long.valueOf(entry.getKey().split("_")[1]));
            }
        }

        log.info("success : {}", success);
        log.info("fail : {}", fail);

        for (int i = 0; i < success.size(); i++) {
            notificationService.sendToClientOnline(emitters.get(i),ids.get(i),success.size());
        }
    }
}
