package com.readme.novels.episodes.responseObject;

import com.readme.novels.episodes.responseObject.ResponseEpisodesPagination.Pagination;
import java.util.List;
import java.util.stream.Stream;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@ToString
public class ResponseEpisodeHistoryPagination {
    private List<ResponseEpisodeHistory> contents;
    private Pagination pagination;

    public ResponseEpisodeHistoryPagination(List<ResponseEpisodeHistory> contents,
        Pagination pagination) {
        this.contents = contents;
        this.pagination = pagination;
    }
}
