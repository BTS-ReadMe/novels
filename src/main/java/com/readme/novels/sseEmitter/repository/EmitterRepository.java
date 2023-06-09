package com.readme.novels.sseEmitter.repository;

import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

public interface EmitterRepository {

    public SseEmitter findById(Long id);

}
