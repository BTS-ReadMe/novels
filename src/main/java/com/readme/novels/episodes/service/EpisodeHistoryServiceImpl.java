package com.readme.novels.episodes.service;

import com.readme.novels.episodes.dto.EpisodeHistoryDto;
import com.readme.novels.episodes.dto.EpisodeHistoryPaginationDto;
import com.readme.novels.episodes.dto.EpisodeRecentHistoryDto;
import com.readme.novels.episodes.dto.UpdateReadAtDto;
import com.readme.novels.episodes.model.EpisodeHistory;
import com.readme.novels.episodes.model.Episodes;
import com.readme.novels.episodes.repository.EpisodeHistoryRepository;
import com.readme.novels.episodes.repository.EpisodesRepository;
import com.readme.novels.episodes.responseObject.ResponseEpisodesPagination.Pagination;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
@Slf4j
public class EpisodeHistoryServiceImpl implements EpisodeHistoryService {

    private final EpisodeHistoryRepository episodeHistoryRepository;
    private final EpisodesRepository episodesRepository;

    @Override
    public void addEpisodeHistory(Long id, String uuid) {
        Episodes episodes = episodesRepository.findById(id).orElseThrow(() -> {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        });

        // uuid랑 episode id로 조회
        Optional<EpisodeHistory> episodeHistory = episodeHistoryRepository.findByUuidAndEpisodeId(
            uuid, episodes.getId());

        if (episodeHistory.isEmpty()) {
            EpisodeHistory newEpisodeHistory = new EpisodeHistory(uuid, episodes.getNovelsId(),
                episodes.getId(), episodes.isFree());
            episodeHistoryRepository.save(newEpisodeHistory);
        }
    }

    @Override
    public EpisodeHistoryPaginationDto getEpisodeHistoryByUser(String uuid, Pageable pageable) {
//        Page<EpisodeHistory> episodeHistoryPage = episodeHistoryRepository
//            .findByUuidOrderByUpdateDateDesc(uuid, pageable);

        Page<EpisodeRecentHistoryDto> episodeHistoryPage = episodeHistoryRepository
            .findRecentEpisodeIdsByUuid(uuid, pageable);
        List<EpisodeHistoryDto> episodeHistoryDtoList = new ArrayList<>();

        episodeHistoryPage.forEach(dto -> {
            EpisodeHistory episodeHistory = episodeHistoryRepository
                .findByUuidAndEpisodeId(uuid, dto.getEpisodeId()).get();

            EpisodeHistoryDto episodeHistoryDto = new EpisodeHistoryDto(episodeHistory);
            episodeHistoryDtoList.add(episodeHistoryDto);
        });

        Pagination pagination = Pagination.builder()
            .page(episodeHistoryPage.getNumber())
            .size(episodeHistoryPage.getSize())
            .totalElements(episodeHistoryPage.getTotalElements())
            .totalPage(episodeHistoryPage.getTotalPages())
            .build();

        return new EpisodeHistoryPaginationDto(episodeHistoryDtoList, pagination);
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

    @Override
    public void updateEpisodeReadAt(UpdateReadAtDto updateReadAtDto) {
        EpisodeHistory episodeHistory = episodeHistoryRepository
            .findByUuidAndEpisodeId(updateReadAtDto.getUuid(), updateReadAtDto.getEpisodeId())
            .orElseThrow(() -> {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
            });

        episodeHistory.setReadAt(updateReadAtDto.getReadAt());
        episodeHistoryRepository.save(episodeHistory);
    }

    @Override
    public List<EpisodeHistoryDto> getReadEpisodeByNovelId(String uuid, Long novelId) {

        List<EpisodeHistory> episodeHistoryList
            = episodeHistoryRepository.findByUuidAndNovelId(uuid, novelId);

        List<EpisodeHistoryDto> episodeHistoryDtoList = new ArrayList<>();

        episodeHistoryList.forEach(episodeHistory -> {
            EpisodeHistoryDto episodeHistoryDto = new EpisodeHistoryDto(episodeHistory);
            episodeHistoryDtoList.add(episodeHistoryDto);
        });

        return episodeHistoryDtoList;
    }

    @Transactional
    @Override
    public void deleteEpisodeById(Long id) {
        episodeHistoryRepository.deleteById(id);
    }
}
