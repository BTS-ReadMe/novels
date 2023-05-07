package com.readme.novels.episodes.service;

import com.readme.novels.episodes.dto.EpisodesDto;
import com.readme.novels.episodes.model.Episodes;
import com.readme.novels.episodes.repository.EpisodesRepository;
import com.readme.novels.novels.model.Novels;
import com.readme.novels.novels.repository.INovelsRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
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

        episodesRepository.save(episodes);
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
}
