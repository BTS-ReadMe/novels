package com.readme.novels.service;

import com.readme.novels.dto.NovelsDto;
import com.readme.novels.model.Novels;
import com.readme.novels.model.Novels.Genre;
import com.readme.novels.model.Novels.Grade;
import com.readme.novels.repository.NovelsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class NovelsServiceImpl implements NovelsService {

    private final NovelsRepository novelsRepository;

    @Override
    public void addNovels(NovelsDto novelsDto) {

        if (novelsDto.getSerializationStatus() == null) {
            novelsDto.setSerializationStatus("연재중");
        }

        Novels novels = Novels.builder()
            .title(novelsDto.getTitle())
            .author(novelsDto.getAuthor())
            .grade(novelsDto.getGrade())
            .genre(novelsDto.getGenre())
            .thumbnail(novelsDto.getThumbnail())
            .authorComment(novelsDto.getAuthorComment())
            .serializationDay(novelsDto.getSerializationDay())
            .startDate(novelsDto.getStartDate())
            .serializationStatus(novelsDto.getSerializationStatus())
            .description(novelsDto.getDescription())
            .build();

        novelsRepository.save(novels);


    }

    @Override
    @Transactional
    public void updateNovels(NovelsDto novelsDto) {

        novelsRepository.findById(novelsDto.getId()).orElseThrow(
            () -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "존재하지 않는 소설입니다."));

        Novels novels = Novels.builder()
            .id(novelsDto.getId())
            .genre(novelsDto.getGenre())
            .grade(novelsDto.getGrade())
            .startDate(novelsDto.getStartDate())
            .serializationStatus(novelsDto.getSerializationStatus())
            .serializationDay(novelsDto.getSerializationDay())
            .title(novelsDto.getTitle())
            .author(novelsDto.getAuthor())
            .thumbnail(novelsDto.getThumbnail())
            .authorComment(novelsDto.getAuthorComment())
            .description(novelsDto.getDescription())
            .build();

        novelsRepository.save(novels);

    }
}
