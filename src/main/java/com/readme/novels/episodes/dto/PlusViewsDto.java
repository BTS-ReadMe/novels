package com.readme.novels.episodes.dto;

import lombok.Getter;

@Getter
public class PlusViewsDto {
    private long id;
    private long novelsId;

    public PlusViewsDto(EpisodesDtoByUser episodesDto) {
        this.id = episodesDto.getId();
        this.novelsId = episodesDto.getNovelsId();
    }
}
