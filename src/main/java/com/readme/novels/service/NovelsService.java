package com.readme.novels.service;

import com.readme.novels.dto.NovelsDto;
import com.readme.novels.requestObject.RequestAddNovels;

public interface NovelsService {

    void addNovels(NovelsDto novelsDto);

    void updateNovels(NovelsDto novelsDto);

    void deleteNovels(Long id);

    NovelsDto getNovelsById(Long id);
}
