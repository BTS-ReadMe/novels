package com.readme.novels.service;

import com.readme.novels.dto.NovelsDto;
import com.readme.novels.dto.PaginationDto;
import com.readme.novels.model.Novels;
import com.readme.novels.repository.INovelsRepository;
import com.readme.novels.dto.NovelsSearchParamDto;
import com.readme.novels.repository.NovelsRepository;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
@Slf4j
public class NovelsServiceImpl implements NovelsService {

    private final INovelsRepository iNovelsRepository;
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

        iNovelsRepository.save(novels);


    }

    @Override
    @Transactional
    public void updateNovels(NovelsDto novelsDto) {

        iNovelsRepository.findById(novelsDto.getId()).orElseThrow(
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

        iNovelsRepository.save(novels);

    }

    @Override
    public void deleteNovels(Long id) {
        iNovelsRepository.findById(id).orElseThrow(
            () -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "존재하지 않는 소설 입니다."));
        iNovelsRepository.deleteById(id);
    }

    @Override
    public NovelsDto getNovelsById(Long id) {
        Novels novels = iNovelsRepository.findById(id)
            .orElseThrow(
                () -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "존재하지 않는 소설입니다."));

        NovelsDto novelsDto = NovelsDto.builder()
            .id(novels.getId())
            .serializationDay(novels.getSerializationDay())
            .serializationStatus(novels.getSerializationStatus())
            .grade(novels.getGrade())
            .genre(novels.getGenre())
            .author(novels.getAuthor())
            .authorComment(novels.getAuthorComment())
            .createDate(novels.getCreateDate())
            .updateDate(novels.getUpdateDate())
            .description(novels.getDescription())
            .startDate(novels.getStartDate())
            .thumbnail(novels.getThumbnail())
            .title(novels.getTitle())
            .startDate(novels.getStartDate())
            .build();

        return novelsDto;
    }

    @Override
    public List<NovelsDto> getNovels(NovelsSearchParamDto novelsSearchParamDto) {

        List<Novels> novelsList = novelsRepository.getNovels(novelsSearchParamDto);

        List<NovelsDto> novelsDtoList = new ArrayList<>();

        novelsList.forEach(item -> {
            NovelsDto novelsDto = NovelsDto.builder()
                .id(item.getId())
                .title(item.getTitle())
                .author(item.getAuthor())
                .description(item.getDescription())
                .startDate(item.getStartDate())
                .serializationDay(item.getSerializationDay())
                .serializationStatus(item.getSerializationStatus())
                .thumbnail(item.getThumbnail())
                .genre(item.getGenre())
                .grade(item.getGrade())
                .authorComment(item.getAuthorComment())
                .build();

            novelsDtoList.add(novelsDto);
        });

        return novelsDtoList;
    }

    @Override
    public PaginationDto getPagination(NovelsSearchParamDto novelsSearchParamDto) {
        return novelsRepository.getPagination(novelsSearchParamDto);
    }
}
