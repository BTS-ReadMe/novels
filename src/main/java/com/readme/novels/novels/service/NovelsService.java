package com.readme.novels.novels.service;

import com.readme.novels.novels.dto.NovelsDto;
import com.readme.novels.novels.dto.NovelsSearchParamDto;
import com.readme.novels.novels.dto.PaginationDto;
import java.util.List;

public interface NovelsService {

    void addNovels(NovelsDto novelsDto);

    void updateNovels(NovelsDto novelsDto);

    void deleteNovels(Long id);

    NovelsDto getNovelsById(Long id);

    List<NovelsDto> getNovels(NovelsSearchParamDto novelsSearchParamDto);

    PaginationDto getPagination(NovelsSearchParamDto novelsSearchParamDto);
}
