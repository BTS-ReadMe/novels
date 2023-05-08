package com.readme.novels.episodes.dto;

import com.readme.novels.episodes.responseObject.ResponseEpisodesPagination.Pagination;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@ToString
public class EpisodesPageDto {
    private List<EpisodesDto> contents;
    private Pagination pagination;
}
