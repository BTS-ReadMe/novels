package com.readme.novels.episodes.controller;

import com.readme.novels.commonResponseObject.CommonDataResponse;
import com.readme.novels.episodes.dto.EpisodeHistoryPaginationDto;
import com.readme.novels.episodes.responseObject.ResponseEpisodeHistory;
import com.readme.novels.episodes.responseObject.ResponseEpisodeHistoryPagination;
import com.readme.novels.episodes.service.EpisodeHistoryService;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/history")
@Slf4j
@RequiredArgsConstructor
public class EpisodeHistoryController {

    private final EpisodeHistoryService episodeHistoryService;

    @GetMapping
    public ResponseEntity<CommonDataResponse<ResponseEpisodeHistoryPagination>> getEpisodeHistoryByUser(
        @RequestHeader("uuid") String uuid,
        @PageableDefault(size = 12) Pageable pageable) {
        EpisodeHistoryPaginationDto episodeHistoryPaginationDto
            = episodeHistoryService.getEpisodeHistoryByUser(uuid, pageable);

        return ResponseEntity.ok(new CommonDataResponse<>(
            new ResponseEpisodeHistoryPagination(
            episodeHistoryPaginationDto.getContents().stream().map(ResponseEpisodeHistory::new).collect(
                Collectors.toList()),
            episodeHistoryPaginationDto.getPagination())
        ));
    }

}
