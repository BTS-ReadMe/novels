package com.readme.novels.episodes.responseObject;

import com.readme.novels.episodes.dto.EpisodeHistoryDto;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@ToString
public class ResponseEpisodeHistory {
    private Long id;
    private String uuid;
    private Long novelId;
    private Long episodeId;
    private LocalDateTime createDate;

    public ResponseEpisodeHistory(EpisodeHistoryDto episodeHistoryDto) {
        this.id = episodeHistoryDto.getId();
        this.uuid = episodeHistoryDto.getUuid();
        this.novelId = episodeHistoryDto.getNovelId();
        this.episodeId = episodeHistoryDto.getEpisodeId();
        this.createDate = episodeHistoryDto.getCreateDate();
    }
}
