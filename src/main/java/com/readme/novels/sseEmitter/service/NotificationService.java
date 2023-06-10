package com.readme.novels.sseEmitter.service;

import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

public interface NotificationService {

    public SseEmitter connection(Long episodeId, String uuid, HttpServletResponse response);

    public void sendToClient(SseEmitter emitter, String id, Object data);
}