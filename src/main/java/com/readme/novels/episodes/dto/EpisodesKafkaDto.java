package com.readme.novels.episodes.dto;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@ToString
public class EpisodesKafkaDto {
    private Long episodeId;
    private Long novelId;
    private String title;
    private boolean free;
    private LocalDateTime registration;
    private String status;

    public EpisodesKafkaDto(EpisodesDto episodesDto) {
        this.episodeId = episodesDto.getId();
        this.novelId = episodesDto.getNovelsId();
        this.title = episodesDto.getTitle();
        this.free = episodesDto.isFree();
        this.registration = episodesDto.getRegistration();
        this.status = episodesDto.getStatus();
    }
}
