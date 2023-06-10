package com.readme.novels.sseEmitter.repository;

import java.util.Map;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

public interface EmitterRepository {

    public SseEmitter save(String id, SseEmitter sseEmitter);

    public Map<String, SseEmitter> findAllStartById(String id);

    public void deleteAllStartByWithId(String id);

}
