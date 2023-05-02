package com.readme.novels.service;

import com.readme.novels.dto.NovelsDto;
import com.readme.novels.model.Novels;
import com.readme.novels.model.Novels.Genre;
import com.readme.novels.model.Novels.Grade;
import com.readme.novels.repository.NovelsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
}
