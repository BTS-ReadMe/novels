package com.readme.novels.sseEmitter.repository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@Repository
@RequiredArgsConstructor
public class SseEmitterRepository implements EmitterRepository {

    private final Map<String, SseEmitter> emitters = new ConcurrentHashMap<>();

    @Override
    public SseEmitter save(String episodeId) { // addEpisodes
        SseEmitter emitter = new SseEmitter();
        emitters.put(episodeId, emitter);
        return emitter;
    }

    @Override
    public SseEmitter findById(String episodeId) {
        if (emitters.containsKey(episodeId)) {
            return emitters.get(episodeId);
        } else {
            return null;
        }
    }

    @Override
    public void deleteById(String episodeId) { //deleteEpisode
        emitters.get(episodeId).complete();
        emitters.remove(episodeId);
    }
}
