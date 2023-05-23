package com.readme.novels.episodes.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PlusViewsDto {
    private long id;
    private long novelsId;

    public PlusViewsDto(EpisodesDto episodesDto) {
        this.id = episodesDto.getId();
        this.novelsId = episodesDto.getNovelsId();
    }
}
