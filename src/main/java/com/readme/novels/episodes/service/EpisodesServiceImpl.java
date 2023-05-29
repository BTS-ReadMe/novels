package com.readme.novels.episodes.service;

import com.readme.novels.episodes.dto.EpisodesDto;
import com.readme.novels.episodes.dto.EpisodesDtoByUser;
import com.readme.novels.episodes.dto.EpisodesPageDto;
import com.readme.novels.episodes.model.EpisodeHistory;
import com.readme.novels.episodes.model.Episodes;
import com.readme.novels.episodes.repository.EpisodeHistoryRepository;
import com.readme.novels.episodes.repository.EpisodesRepository;
import com.readme.novels.episodes.responseObject.ResponseEpisodesPagination.Pagination;
import com.readme.novels.novels.model.Novels;
import com.readme.novels.novels.repository.INovelsRepository;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
@Slf4j
public class EpisodesServiceImpl implements EpisodesService {

    private final EpisodesRepository episodesRepository;
    private final INovelsRepository iNovelsRepository;
    private final EpisodeHistoryRepository episodeHistoryRepository;

    @Override
    public void addEpisodes(EpisodesDto episodesDto) {

        iNovelsRepository.findById(episodesDto.getNovelsId()).orElseThrow(() -> {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        });

        Episodes episodes = Episodes.builder()
            .novelsId(episodesDto.getNovelsId())
            .title(episodesDto.getTitle())
            .content(episodesDto.getContent())
            .views(episodesDto.getViews())
            .free(episodesDto.isFree())
            .registration(episodesDto.getRegistration())
            .status(episodesDto.getStatus())
            .build();

        Episodes savedEpisodes = episodesRepository.save(episodes);
        episodesDto.setId(savedEpisodes.getId());
        episodesDto.setNovelsId(savedEpisodes.getNovelsId());

    }

    @Override
    public void updateEpisodes(EpisodesDto episodesDto) {
        Episodes episodes = episodesRepository.findById(episodesDto.getId()).orElseThrow(() -> {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        });

        Episodes updateEpisodes = Episodes.builder()
            .id(episodesDto.getId())
            .novelsId(episodesDto.getNovelsId())
            .title(episodesDto.getTitle())
            .content(episodesDto.getContent())
            .registration(episodesDto.getRegistration())
            .free(episodesDto.isFree())
            .status(episodesDto.getStatus())
            .views(episodes.getViews())
            .build();

        episodesRepository.save(updateEpisodes);
        episodesDto.setViews(episodes.getViews());
    }

    @Override
    public long deleteEpisodes(Long id) {
        Episodes episodes = episodesRepository.findById(id).orElseThrow(() -> {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        });

        Episodes deleteEpisode = Episodes.builder()
            .id(episodes.getId())
            .views(episodes.getViews())
            .novelsId(episodes.getNovelsId())
            .status("삭제")
            .free(episodes.isFree())
            .registration(episodes.getRegistration())
            .content(episodes.getContent())
            .title(episodes.getTitle())
            .build();

        episodesRepository.save(deleteEpisode);

        return deleteEpisode.getNovelsId();
    }

    @Override
    public EpisodesDto getEpisodesById(Long id) {

        Episodes episodes = episodesRepository.findById(id).orElseThrow(() -> {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        });

        EpisodesDto episodesDto = new EpisodesDto(episodes);

        return episodesDto;
    }

    @Override
    public EpisodesPageDto getEpisodesByNovelsId(Long novelId, Pageable pageable) {

        iNovelsRepository.findById(novelId).orElseThrow(() -> {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        });

        Page<Episodes> episodesList
            = episodesRepository.findByNovelsIdOrderByCreateDateDesc(novelId, pageable);

        List<EpisodesDto> episodesDtoList = new ArrayList<>();

        Pagination pagination = Pagination.builder()
            .page(episodesList.getNumber())
            .size(episodesList.getSize())
            .totalElements(episodesList.getTotalElements())
            .totalPage(episodesList.getTotalPages())
            .build();

        episodesList.forEach(episodes -> {
            EpisodesDto episodesDto = new EpisodesDto(episodes);

            episodesDtoList.add(episodesDto);
        });

        EpisodesPageDto episodesPageDto = new EpisodesPageDto(episodesDtoList, pagination);

        return episodesPageDto;
    }

    @Override
    public EpisodesDtoByUser getEpisodesByUser(Long id) {

        Episodes episodes = episodesRepository.findById(id).orElseThrow(() -> {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "존재하지 않는 에피소드 입니다.");
        });

        EpisodesDtoByUser episodesDtoByUser = new EpisodesDtoByUser(episodes);
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        episodesDtoByUser.setModifiedRegistration(
            episodes.getRegistration().format(dateTimeFormatter));

        Novels novels = iNovelsRepository.findById(episodes.getNovelsId()).get();
        episodesDtoByUser.setNovelsTitle(novels.getTitle());

        return episodesDtoByUser;
    }

    @Override
    public void plusViewsCount(Long id, Integer plusCount) {
        Episodes episodes = episodesRepository.findById(id).orElseThrow(() -> {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "존재하지 않는 에피소드 입니다.");
        });

        Episodes updateEpisodes = Episodes.builder()
            .title(episodes.getTitle())
            .views(episodes.getViews() + plusCount)
            .content(episodes.getContent())
            .registration(episodes.getRegistration())
            .free(episodes.isFree())
            .status(episodes.getStatus())
            .novelsId(episodes.getNovelsId())
            .id(episodes.getId())
            .build();

        episodesRepository.save(updateEpisodes);

    }

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
