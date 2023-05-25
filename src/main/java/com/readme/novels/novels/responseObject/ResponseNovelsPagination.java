package com.readme.novels.novels.responseObject;

import com.readme.novels.novels.dto.NovelsDto;
import com.readme.novels.novels.dto.PaginationDto;
import com.readme.novels.novels.dto.ResponseNovelsDto;
import java.util.List;
import java.util.stream.Stream;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseNovelsPagination {
    private Stream<ResponseNovels> contents;
    private Pagination pagination;

    public ResponseNovelsPagination(Stream<ResponseNovels> contents, Pagination pagination) {
        this.contents = contents;
        this.pagination = pagination;
    }

    @Getter
    @Setter
    public static class Pagination {
        private int page;
        private int size;
        private long totalElements;
        private int totalPage;

        public Pagination(PaginationDto paginationDto) {
            this.page = paginationDto.getPage();
            this.size = paginationDto.getSize();
            this.totalElements = paginationDto.getTotalElements();
            this.totalPage = paginationDto.getTotalPage();
        }
    }
}
