package com.readme.novels.episodes.dto;

import com.readme.novels.episodes.model.Episodes;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

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

    public EpisodesDtoByUser(Episodes episodes) {
        this.id = episodes.getId();
        this.novelsId = episodes.getNovelsId();
        this.title = episodes.getTitle();
        this.content = episodes.getContent();
        this.views = episodes.getViews();
        this.free = episodes.isFree();
        this.createDate = episodes.getCreateDate();
        this.updateDate = episodes.getUpdateDate();
        this.status = episodes.getStatus();
    }

    public void setModifiedRegistration(String registration) {
        this.registration = registration;
    }
}
