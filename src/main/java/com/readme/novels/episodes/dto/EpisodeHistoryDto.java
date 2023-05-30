package com.readme.novels.episodes.dto;

import com.readme.novels.episodes.model.EpisodeHistory;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class EpisodeHistoryDto {
    private Long id;
    private String uuid;
    private Long novelId;
    private Long episodeId;
    private LocalDateTime createDate;

    public EpisodeHistoryDto(EpisodeHistory episodeHistory) {
        this.id = episodeHistory.getId();
        this.uuid = episodeHistory.getUuid();
        this.novelId = episodeHistory.getNovelId();
        this.episodeId = episodeHistory.getEpisodeId();
        this.createDate = episodeHistory.getCreateDate();
    }
}
