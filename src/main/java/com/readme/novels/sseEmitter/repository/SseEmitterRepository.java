package com.readme.novels.sseEmitter.repository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@Repository
@RequiredArgsConstructor
public class SseEmitterRepository implements EmitterRepository {

    private final Map<Long, SseEmitter> emitters = new ConcurrentHashMap<>();

    @Override
    public SseEmitter save(Long id) { // addEpisodes
        SseEmitter emitter = new SseEmitter();
        emitters.put(id, emitter);
        return emitter;
    }

    @Override
    public SseEmitter findById(Long id) {
        if (emitters.containsKey(id)) {
            return emitters.get(id);
        } else {
            return null;
        }
    }

    @Override
    public void deleteById(Long id) { //deleteEpisode
        emitters.get(id).complete();
        emitters.remove(id);
    }
}
