package com.readme.novels.novels.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PaginationDto {
    private int page;
    private int size;
    private long totalElements;
    private int totalPage;

}
