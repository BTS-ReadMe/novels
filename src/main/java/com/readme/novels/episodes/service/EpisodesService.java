package com.readme.novels.episodes.service;

import com.readme.novels.episodes.dto.EpisodesDto;
import com.readme.novels.episodes.dto.EpisodesDtoByUser;
import com.readme.novels.episodes.dto.EpisodesPageDto;
import org.springframework.data.domain.Pageable;

public interface EpisodesService {
    void addEpisodes(EpisodesDto episodesDto);

    void updateEpisodes(EpisodesDto episodesDto);

    long deleteEpisodes(Long id);

    EpisodesDto getEpisodesById(Long id);

    EpisodesPageDto getEpisodesByNovelsId(Long novelId, Pageable pageable);

    EpisodesDtoByUser getEpisodesByUser(Long id);

    void plusViewsCount(Long id, Integer plusCount);

    void addEpisodeHistory(Long id, String uuid);
}
