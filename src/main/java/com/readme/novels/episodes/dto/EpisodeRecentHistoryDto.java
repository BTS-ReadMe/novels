package com.readme.novels.episodes.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@NoArgsConstructor
@Setter
@ToString
public class EpisodeRecentHistoryDto {
    private long novelId;
    private long episodeId;
    private long readAt;

    public EpisodeRecentHistoryDto(long novelId, long episodeId) {
        this.novelId = novelId;
        this.episodeId = episodeId;
    }
}
