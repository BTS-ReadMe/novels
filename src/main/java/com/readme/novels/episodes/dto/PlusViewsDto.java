package com.readme.novels.episodes.dto;

import lombok.Getter;

@Getter
public class PlusViewsDto {
    private long episodesId;
    private long novelsId;
    private long plusCnt;

    public PlusViewsDto(EpisodesDtoByUser episodesDto) {
        this.episodesId = episodesDto.getId();
        this.novelsId = episodesDto.getNovelsId();
        this.plusCnt = 1;
    }
}
