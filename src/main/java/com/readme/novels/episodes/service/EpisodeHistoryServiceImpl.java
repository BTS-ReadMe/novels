package com.readme.novels.episodes.service;

import com.readme.novels.episodes.dto.EpisodeHistoryDto;
import com.readme.novels.episodes.dto.EpisodeHistoryPaginationDto;
import com.readme.novels.episodes.model.EpisodeHistory;
import com.readme.novels.episodes.model.Episodes;
import com.readme.novels.episodes.repository.EpisodeHistoryRepository;
import com.readme.novels.episodes.repository.EpisodesRepository;
import com.readme.novels.episodes.responseObject.ResponseEpisodesPagination.Pagination;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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

        Optional<EpisodeHistory> episodeHistory = episodeHistoryRepository.findByUuidAndNovelId(
            uuid, episodes.getNovelsId());

        if (episodeHistory.isEmpty()) {
            EpisodeHistory newEpisodeHistory = new EpisodeHistory(uuid, episodes.getNovelsId(),
                episodes.getId());
            episodeHistoryRepository.save(newEpisodeHistory);
        } else {
            episodeHistory.get().setEpisodeId(episodes.getId());
            episodeHistoryRepository.save(episodeHistory.get());
        }
    }

    @Override
    public EpisodeHistoryPaginationDto getEpisodeHistoryByUser(String uuid, Pageable pageable) {
        Page<EpisodeHistory> episodeHistoryPage = episodeHistoryRepository
            .findByUuidOrderByUpdateDateDesc(uuid, pageable);

        List<EpisodeHistoryDto> episodeHistoryDtoList = new ArrayList<>();

        episodeHistoryPage.forEach(episodeHistory -> {
            EpisodeHistoryDto episodeHistoryDto = new EpisodeHistoryDto(episodeHistory);
            episodeHistoryDtoList.add(episodeHistoryDto);
        });

        Pagination pagination = Pagination.builder()
            .page(episodeHistoryPage.getNumber())
            .size(episodeHistoryPage.getSize())
            .totalElements(episodeHistoryPage.getTotalElements())
            .totalPage(episodeHistoryPage.getTotalPages())
            .build();

        EpisodeHistoryPaginationDto episodeHistoryPaginationDto = new EpisodeHistoryPaginationDto(
            episodeHistoryDtoList, pagination);

        return episodeHistoryPaginationDto;
    }

    @Transactional
    @Override
    public void deleteEpisodeHistoryByUser(String uuid, Long historyId) {
        if (episodeHistoryRepository.existsByIdAndUuid(historyId, uuid)) {
            episodeHistoryRepository.deleteById(historyId);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}
