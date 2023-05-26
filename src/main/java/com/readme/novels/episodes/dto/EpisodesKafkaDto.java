package com.readme.novels.episodes.dto;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@ToString
public class EpisodesKafkaDto {
    private Long id;
    private Long novelsId;
    private String title;
    private boolean free;
    private LocalDateTime registration;
    private String status;

    public EpisodesKafkaDto(EpisodesDto episodesDto) {
        this.id = episodesDto.getId();
        this.novelsId = episodesDto.getNovelsId();
        this.title = episodesDto.getTitle();
        this.free = episodesDto.isFree();
        this.registration = episodesDto.getRegistration();
        this.status = episodesDto.getStatus();
    }
}
