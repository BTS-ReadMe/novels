package com.readme.novels.messageQueue.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EpisodeNovelDto {
    private String episodeTitle;
    private String novelTitle;
    private Long novelId;
    private int grade;
    private String thumbnail;

    public EpisodeNovelDto(String episodeTitle, String novelTitle, Long novelId, int novelGrade, String novelThumbnail) {
        this.episodeTitle = episodeTitle;
        this.novelTitle = novelTitle;
        this.novelId = novelId;
        this.grade = novelGrade;
        this.thumbnail = novelThumbnail;
    }

}
