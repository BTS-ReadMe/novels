package com.readme.novels.sseEmitter.service;

import com.readme.novels.sseEmitter.repository.EmitterRepository;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@Slf4j
@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    private final EmitterRepository emitterRepository;

    @Component
    public class GlobalCounter {

        private int counter;

        public synchronized int incrementCounter() {
            counter++;
            return counter;
        }
    }

    @Override
    public SseEmitter connection(Long episodeId, String id) {

        String uuid = episodeId + "_" + id;

        SseEmitter emitter = new SseEmitter((long) (10 * 60 * 1000));

        emitterRepository.save(uuid, emitter);

        emitter.onCompletion(() -> {
            emitterRepository.deleteAllStartByWithId(uuid);
            log.info("[disconnect] id : " + id + ", episodeId : " + episodeId);
        });

        log.info("--------------------------");
        sendToClient(emitter, uuid, "[connect] id : " + id + ", episodeId : " + episodeId);

        return emitter;
    }

    @Override
    public void sendToClient(SseEmitter emitter, String uuid, Object data) {

        try {
            String formattedData = formatData(data);
            emitter.send(SseEmitter.event()
                .id(uuid)
                .name("sse")
                .data(formattedData));

            log.info("[send] id : " + uuid.split("_")[1]);

        } catch (IOException e) {
//            emitterRepository.deleteAllStartByWithId(uuid);
//            log.info("--------------------------");
//
//            Long episodeId = Long.valueOf(uuid.split("_")[0]);
//            String id = uuid.split("_")[1];
//            log.info("[disconnect] id : " + id + ", episodeId : " + episodeId);
        }
    }

    private String formatData(Object data) {
        String rawData = data.toString();
        return "data: " + rawData.toString() + " \n\n";
    }
}
