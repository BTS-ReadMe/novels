package com.readme.novels.episodes.dto;

import com.readme.novels.episodes.model.Episodes;
import com.readme.novels.episodes.requestObject.RequestAddEpisodes;
import com.readme.novels.episodes.requestObject.RequestUpdateEpisodes;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EpisodesDto {

    private Long id;
    private Long novelsId;
    private String title;
    private String content;
    private Long views;
    private boolean free;
    private LocalDateTime registration;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
    private String status;

    public EpisodesDto(RequestAddEpisodes requestAddEpisodes) {
        this.novelsId = requestAddEpisodes.getNovelsId();
        this.title = requestAddEpisodes.getTitle();
        this.content = requestAddEpisodes.getContent();
        this.free = requestAddEpisodes.isFree();
        this.registration = requestAddEpisodes.getRegistration();
        this.status = requestAddEpisodes.getStatus();
        this.views = requestAddEpisodes.getViews() == null ? 0L : requestAddEpisodes.getViews();
    }

    public EpisodesDto(RequestUpdateEpisodes requestUpdateEpisodes, Long id) {
        this.id = id;
        this.novelsId = requestUpdateEpisodes.getNovelsId();
        this.title = requestUpdateEpisodes.getTitle();
        this.content = requestUpdateEpisodes.getContent();
        this.free = requestUpdateEpisodes.isFree();
        this.registration = requestUpdateEpisodes.getRegistration();
        this.status = requestUpdateEpisodes.getStatus();
    }

    public EpisodesDto(Episodes episodes) {
        this.id = episodes.getId();
        this.novelsId = episodes.getNovelsId();
        this.title = episodes.getTitle();
        this.content = episodes.getContent();
        this.views = episodes.getViews();
        this.free = episodes.isFree();
        this.registration = episodes.getRegistration();
        this.createDate = episodes.getCreateDate();
        this.updateDate = episodes.getUpdateDate();
        this.status = episodes.getStatus();
    }

}
