package com.readme.novels.sseEmitter.service;

import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

public interface NotificationService {

    public SseEmitter connection(Long episodeId, String uuid);

    public Boolean sendToClient(SseEmitter emitter, String id, Object data);

    public void sendToClientOnline(SseEmitter emitter, String id, int online);
}
