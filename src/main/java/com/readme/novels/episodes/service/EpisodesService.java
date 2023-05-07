package com.readme.novels.episodes.service;

import com.readme.novels.episodes.dto.EpisodesDto;

public interface EpisodesService {
    void addEpisodes(EpisodesDto episodesDto);

    void updateEpisodes(Long id, EpisodesDto episodesDto);

    void deleteEpisodes(Long id);
}
