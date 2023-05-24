package com.readme.novels.episodes.dto;

import com.readme.novels.episodes.responseObject.ResponseEpisodesPagination.Pagination;
import java.util.List;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class EpisodesPageDto {
    private List<EpisodesDto> contents;
    private Pagination pagination;

    public EpisodesPageDto(List<EpisodesDto> contents, Pagination pagination) {
        this.contents = contents;
        this.pagination = pagination;
    }
}
