package com.readme.novels.episodes.service;

import com.readme.novels.episodes.dto.EpisodesDto;
import com.readme.novels.episodes.dto.EpisodesPageDto;
import java.util.List;
import org.springframework.data.domain.Pageable;

public interface EpisodesService {
    void addEpisodes(EpisodesDto episodesDto);

    void updateEpisodes(Long id, EpisodesDto episodesDto);

    void deleteEpisodes(Long id);

    EpisodesDto getEpisodesById(Long id);

    EpisodesPageDto getEpisodesByNovelsId(Long novelId, Pageable pageable);

    EpisodesDto getEpisodesByUser(Long id);

    void plusViewsCount(Long id, Integer plusCount);
}
