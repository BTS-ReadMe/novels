package com.readme.novels.sseEmitter.repository;

import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

public interface EmitterRepository {

    public SseEmitter save(String id);

    public SseEmitter findById(String id);

    public void deleteById(String id);

}
