package com.readme.novels.episodes.responseObject;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class ResponseFirstEpisode {
    private Long episodeId;

    public ResponseFirstEpisode(Long episodeId) {
        this.episodeId = episodeId;
    }
}
