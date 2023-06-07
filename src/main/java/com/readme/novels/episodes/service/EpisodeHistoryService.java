package com.readme.novels.episodes.service;

import com.readme.novels.episodes.dto.EpisodeHistoryDto;
import com.readme.novels.episodes.dto.EpisodeHistoryPaginationDto;
import com.readme.novels.episodes.dto.UpdateReadAtDto;
import com.readme.novels.episodes.model.EpisodeHistory;
import java.util.List;
import org.springframework.data.domain.Pageable;

public interface EpisodeHistoryService {

    void addEpisodeHistory(Long id, String uuid);

    EpisodeHistoryPaginationDto getEpisodeHistoryByUser(String uuid, Pageable pageable);

    void deleteEpisodeHistoryByUser(String uuid, Long historyId);

    void updateEpisodeReadAt(UpdateReadAtDto updateReadAtDto);

    List<EpisodeHistoryDto> getReadEpisodeByNovelId(String uuid, Long novelId);
}
