package com.readme.novels.episodes.responseObject;

import com.readme.novels.episodes.dto.EpisodesDto;
import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class ResponseEpisodes {
    private Long id;
    private String title;
    private String content;
    private LocalDateTime registration;
    private boolean free;
    private String status;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;

    public ResponseEpisodes(EpisodesDto episodesDto) {
        this.id = episodesDto.getId();
        this.title = episodesDto.getTitle();
        this.content = episodesDto.getContent();
        this.registration = episodesDto.getRegistration();
        this.free = episodesDto.getFree();
        this.status = episodesDto.getStatus();
        this.createDate = episodesDto.getCreateDate();
        this.updateDate = episodesDto.getUpdateDate();
    }
}
