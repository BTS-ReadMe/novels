package com.readme.novels.episodes.service;

import com.readme.novels.episodes.dto.EpisodesDto;
import com.readme.novels.episodes.dto.EpisodesPageDto;
import com.readme.novels.episodes.model.Episodes;
import com.readme.novels.episodes.repository.EpisodesRepository;
import com.readme.novels.episodes.responseObject.ResponseEpisodesPagination.Pagination;
import com.readme.novels.novels.model.Novels;
import com.readme.novels.novels.repository.INovelsRepository;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
@Slf4j
public class EpisodesServiceImpl implements EpisodesService {

    private final EpisodesRepository episodesRepository;
    private final INovelsRepository iNovelsRepository;

    @Override
    public void addEpisodes(EpisodesDto episodesDto) {

        iNovelsRepository.findById(episodesDto.getNovelsId()).orElseThrow(() -> {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "존재하지 않는 소설입니다.");
        });

        Episodes episodes = Episodes.builder()
            .novelsId(episodesDto.getNovelsId())
            .title(episodesDto.getTitle())
            .content(episodesDto.getContent())
            .views(0L)
            .free(episodesDto.getFree())
            .registration(episodesDto.getRegistration())
            .status(episodesDto.getStatus())
            .build();

        Episodes savedEpisodes = episodesRepository.save(episodes);
        episodesDto.setId(savedEpisodes.getId());
        episodesDto.setViews(0L);

    }

    @Override
    public void updateEpisodes(Long id, EpisodesDto episodesDto) {
        Episodes episodes = episodesRepository.findById(id).orElseThrow(() -> {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "존재하지 않는 에피소드 입니다.");
        });

        Episodes updateEpisodes = Episodes.builder()
            .id(id)
            .novelsId(episodes.getNovelsId())
            .title(episodesDto.getTitle())
            .content(episodesDto.getContent())
            .registration(episodesDto.getRegistration())
            .free(episodesDto.getFree())
            .status(episodesDto.getStatus())
            .views(episodes.getViews())
            .build();

        episodesRepository.save(updateEpisodes);
    }

    @Override
    public void deleteEpisodes(Long id) {
        Episodes episodes = episodesRepository.findById(id).orElseThrow(() -> {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "존재하지 않는 에피소드 입니다.");
        });

        Episodes deleteEpisode = Episodes.builder()
            .id(episodes.getId())
            .views(episodes.getViews())
            .novelsId(episodes.getNovelsId())
            .status("삭제")
            .free(episodes.getFree())
            .registration(episodes.getRegistration())
            .content(episodes.getContent())
            .title(episodes.getTitle())
            .build();

        episodesRepository.save(deleteEpisode);
    }

    @Override
    public EpisodesDto getEpisodesById(Long id) {

        Episodes episodes = episodesRepository.findById(id).orElseThrow(() -> {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "존재하지 않는 에피소드 입니다.");
        });

        EpisodesDto episodesDto = EpisodesDto.builder()
            .id(episodes.getId())
            .content(episodes.getContent())
            .free(episodes.getFree())
            .createDate(episodes.getCreateDate())
            .novelsId(episodes.getNovelsId())
            .registration(episodes.getRegistration())
            .status(episodes.getStatus())
            .title(episodes.getTitle())
            .views(episodes.getViews())
            .updateDate(episodes.getUpdateDate())
            .build();

        return episodesDto;
    }

    @Override
    public EpisodesPageDto getEpisodesByNovelsId(Long novelId, Pageable pageable) {

        iNovelsRepository.findById(novelId).orElseThrow(() -> {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "존재하지 않는 소설입니다.");
        });

        Page<Episodes> episodesList
            = episodesRepository.findByNovelsIdOrderByCreateDateDesc(novelId, pageable);

        List<EpisodesDto> episodesDtoList = new ArrayList<>();

        Pagination pagination = Pagination.builder()
            .page(episodesList.getNumber())
            .size(episodesList.getSize())
            .totalElements(episodesList.getTotalElements())
            .totalPage(episodesList.getTotalPages())
            .build();

        episodesList.forEach(episodes -> {
            EpisodesDto episodesDto = EpisodesDto.builder()
                .id(episodes.getId())
                .title(episodes.getTitle())
                .registration(episodes.getRegistration())
                .free(episodes.getFree())
                .novelsId(episodes.getNovelsId())
                .createDate(episodes.getCreateDate())
                .views(episodes.getViews())
                .updateDate(episodes.getUpdateDate())
                .status(episodes.getStatus())
                .build();
            episodesDtoList.add(episodesDto);
        });

        EpisodesPageDto episodesPageDto = EpisodesPageDto.builder()
            .contents(episodesDtoList)
            .pagination(pagination)
            .build();

        return episodesPageDto;
    }

    @Override
    public EpisodesDto getEpisodesByUser(Long id) {

        Episodes episodes = episodesRepository.findById(id).orElseThrow(() -> {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "존재하지 않는 에피소드 입니다.");
        });
        
        EpisodesDto episodesDto = EpisodesDto.builder()
            .id(episodes.getId())
            .title(episodes.getTitle())
            .content(episodes.getContent())
            .registration(episodes.getRegistration())
            .free(episodes.getFree())
            .status(episodes.getStatus())
            .novelsId(episodes.getNovelsId())
            .views(episodes.getViews())
            .build();

        return episodesDto;
    }

    @Override
    public void plusViewsCount(Long id, Integer plusCount) {
        Episodes episodes = episodesRepository.findById(id).orElseThrow(() -> {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "존재하지 않는 에피소드 입니다.");
        });

        Episodes updateEpisodes = Episodes.builder()
            .title(episodes.getTitle())
            .views(episodes.getViews()+plusCount)
            .content(episodes.getContent())
            .registration(episodes.getRegistration())
            .free(episodes.getFree())
            .status(episodes.getStatus())
            .novelsId(episodes.getNovelsId())
            .id(episodes.getId())
            .build();

        episodesRepository.save(updateEpisodes);

    }
}
