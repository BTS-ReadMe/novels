package com.readme.novels.episodes.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@ToString
public class PlusViewsKafkaDto {
    private long episodeId;
    private long novelId;
    private long plusCnt;

    public PlusViewsKafkaDto(EpisodesDtoByUser episodesDto) {
        this.episodeId = episodesDto.getId();
        this.novelId = episodesDto.getNovelsId();
        this.plusCnt = 1;
    }
}
