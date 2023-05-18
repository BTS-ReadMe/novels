package com.readme.novels.novels.service;

import com.readme.novels.novels.dto.NovelsDto;
import com.readme.novels.novels.dto.NovelsSearchParamDto;
import com.readme.novels.novels.dto.PaginationDto;
import com.readme.novels.novels.dto.ResponseNovelsDto;
import java.util.List;

public interface NovelsService {

    void addNovels(NovelsDto novelsDto);

    void updateNovels(NovelsDto novelsDto);

    void deleteNovels(Long id);

    ResponseNovelsDto getNovelsById(Long id);

    List<ResponseNovelsDto> getNovels(NovelsSearchParamDto novelsSearchParamDto);

    PaginationDto getPagination(NovelsSearchParamDto novelsSearchParamDto);
}
