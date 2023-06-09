package com.readme.novels.sseEmitter.repository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@Repository
@RequiredArgsConstructor
@Slf4j
public class EmitterRepositoryImpl implements EmitterRepository {

    private final Map<Long, SseEmitter> emitters = new ConcurrentHashMap<>();

    @Override
    public SseEmitter findById(Long id) {
        log.info("1111111111111");
        if (emitters.containsKey(id)) {
            log.info("2222222222222");
            return emitters.get(id);
        } else {
            log.info("333333333333333333");
            SseEmitter emitter = new SseEmitter();
            emitters.put(id, emitter);
            return emitter;
        }
    }
}
