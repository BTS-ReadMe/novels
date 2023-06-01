package com.readme.novels.novels.service;

import com.readme.novels.novels.dto.NovelMainDto;
import com.readme.novels.novels.model.NovelMain;
import com.readme.novels.novels.repository.NovelMainRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class NovelMainServiceImpl implements NovelMainService {

    private final NovelMainRepository novelMainRepository;

    @Override
    public NovelMainDto getMainNovel() {
        long id = 1L;
        NovelMain novelMain = novelMainRepository.findById(id).orElseThrow(() -> {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        });

        return new NovelMainDto(novelMain);

    }
}
