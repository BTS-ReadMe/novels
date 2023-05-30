package com.readme.novels.episodes.dto;

import com.readme.novels.episodes.model.EpisodeHistory;
import com.readme.novels.episodes.responseObject.ResponseEpisodesPagination.Pagination;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class EpisodeHistoryPaginationDto {
    private List<EpisodeHistoryDto> contents;
    private Pagination pagination;

    public EpisodeHistoryPaginationDto(List<EpisodeHistoryDto> contents, Pagination pagination) {
        this.contents = contents;
        this.pagination = pagination;
    }
}
