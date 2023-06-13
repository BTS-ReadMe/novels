package com.readme.novels.episodes.service;

import com.readme.novels.episodes.dto.EpisodesDto;
import com.readme.novels.episodes.dto.EpisodesDtoByUser;
import com.readme.novels.episodes.dto.EpisodesPageDto;
import com.readme.novels.episodes.model.Episodes;
import com.readme.novels.episodes.responseObject.ResponseFirstEpisode;
import org.springframework.data.domain.Pageable;

public interface EpisodesService {
    void addEpisodes(EpisodesDto episodesDto);

    void updateEpisodes(EpisodesDto episodesDto);

    Episodes deleteEpisodes(Long id);

    EpisodesDto getEpisodesById(Long id);

    EpisodesPageDto getEpisodesByNovelsId(Long novelId, Pageable pageable);

    EpisodesDtoByUser getEpisodesByUser(String uuid, Long id);

    void plusViewsCount(Long id, Integer plusCount);

    ResponseFirstEpisode findFirstEpisode(Long novelId);
}
