package com.readme.novels.sseEmitter.service;

import static org.springframework.retry.policy.TimeoutRetryPolicy.DEFAULT_TIMEOUT;

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

        SseEmitter emitter = emitterRepository.save(uuid, new SseEmitter((long) (5 * 60 * 1000)));

        log.info("--------------------------");
        sendToClient(emitter, uuid, "연결되었습니다. " + uuid);

        return emitter;
    }

    @Override
    public void sendToClient(SseEmitter emitter, String uuid, Object data) {

        try {
            emitter.send(SseEmitter.event()
                .id(uuid)
                .name("sse")
                .data(data));

            log.info("[send / " + uuid.split("_")[1] + "] ");

        } catch (IOException e) {
            emitterRepository.deleteAllStartByWithId(uuid);
            log.info("--------------------------");
            log.info("SSE 연결 오류 발생" + uuid);
            log.info("[delete]" + uuid);
        }
    }
}
