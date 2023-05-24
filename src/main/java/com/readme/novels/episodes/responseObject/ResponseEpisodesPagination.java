package com.readme.novels.episodes.responseObject;

import java.util.stream.Stream;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
public class ResponseEpisodesPagination {
    Stream<ResponseEpisodes> contents;
    Pagination pagination;

    public ResponseEpisodesPagination(Stream<ResponseEpisodes> contents, Pagination pagination) {
        this.contents = contents;
        this.pagination = pagination;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Pagination {
        private int page;
        private int size;
        private long totalElements;
        private int totalPage;
    }
}
