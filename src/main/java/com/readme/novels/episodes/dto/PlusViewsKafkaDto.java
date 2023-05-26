package com.readme.novels.episodes.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@ToString
public class PlusViewsKafkaDto {
    private long episodesId;
    private long novelsId;
    private long plusCnt;

    public PlusViewsKafkaDto(EpisodesDtoByUser episodesDto) {
        this.episodesId = episodesDto.getId();
        this.novelsId = episodesDto.getNovelsId();
        this.plusCnt = 1;
    }
}
