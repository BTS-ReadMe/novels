package com.readme.novels.episodes.service;

import com.readme.novels.episodes.dto.EpisodeHistoryPaginationDto;
import com.readme.novels.episodes.dto.UpdateReadAtDto;
import org.springframework.data.domain.Pageable;

public interface EpisodeHistoryService {

    void addEpisodeHistory(Long id, String uuid);

    EpisodeHistoryPaginationDto getEpisodeHistoryByUser(String uuid, Pageable pageable);

    void deleteEpisodeHistoryByUser(String uuid, Long historyId);

    void updateEpisodeReadAt(UpdateReadAtDto updateReadAtDto);
}
