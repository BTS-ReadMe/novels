package com.readme.novels.episodes.service;

import com.readme.novels.episodes.model.EpisodeHistory;
import com.readme.novels.episodes.model.Episodes;
import com.readme.novels.episodes.repository.EpisodeHistoryRepository;
import com.readme.novels.episodes.repository.EpisodesRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class EpisodeHistoryServiceImpl implements EpisodeHistoryService {

    private final EpisodeHistoryRepository episodeHistoryRepository;
    private final EpisodesRepository episodesRepository;

    @Override
    public void addEpisodeHistory(Long id, String uuid) {
        Episodes episodes = episodesRepository.findById(id).orElseThrow(() -> {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        });

        Optional<EpisodeHistory> episodeHistory = episodeHistoryRepository.findByUuidAndNovelId(uuid, episodes.getNovelsId());

        if (episodeHistory.isEmpty()) {
            EpisodeHistory newEpisodeHistory = new EpisodeHistory(uuid, episodes.getNovelsId(), episodes.getId());
            episodeHistoryRepository.save(newEpisodeHistory);
        } else {
            episodeHistory.get().setEpisodeId(episodes.getId());
            episodeHistoryRepository.save(episodeHistory.get());
        }
    }
}
