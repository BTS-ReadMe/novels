package com.readme.novels.episodes.dto;

import com.readme.novels.episodes.model.Episodes;
import com.readme.novels.episodes.responseObject.ResponseEpisodesPagination.Pagination;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@Getter
@Setter
public class EpisodesDtoByUser {
    private Long id;
    private Long novelsId;
    private String novelsTitle;
    private String title;
    private String content;
    private Long views;
    private boolean free;
    private String registration;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
    private String status;

    private long prevId;
    private boolean prevFree;
    private boolean prevRead;
    private long nextId;
    private boolean nextFree;
    private boolean nextRead;

    private SseEmitter emitter;

    public EpisodesDtoByUser(Episodes episodes) {
        this.id = episodes.getId();
        this.novelsId = episodes.getNovelsId();
        this.title = episodes.getTitle();
        this.views = episodes.getViews();
        this.free = episodes.isFree();
        this.createDate = episodes.getCreateDate();
        this.updateDate = episodes.getUpdateDate();
        this.status = episodes.getStatus();
        this.content = episodes.getContent();
    }

    public void setModifiedRegistration(String registration) {
        this.registration = registration;
    }

}
