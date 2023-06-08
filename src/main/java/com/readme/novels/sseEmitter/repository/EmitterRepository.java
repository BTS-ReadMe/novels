package com.readme.novels.sseEmitter.repository;

import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

public interface EmitterRepository {

    public SseEmitter save(Long id);

    public SseEmitter findById(Long id);

    public void deleteById(Long id);

}
