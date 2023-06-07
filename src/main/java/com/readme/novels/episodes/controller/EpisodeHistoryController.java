package com.readme.novels.episodes.controller;

import com.readme.novels.commonResponseObject.CommonDataResponse;
import com.readme.novels.episodes.dto.EpisodeHistoryDto;
import com.readme.novels.episodes.dto.EpisodeHistoryPaginationDto;
import com.readme.novels.episodes.dto.UpdateReadAtDto;
import com.readme.novels.episodes.requestObject.RequestUpdateReadAt;
import com.readme.novels.episodes.responseObject.ResponseEpisodeHistory;
import com.readme.novels.episodes.responseObject.ResponseEpisodeHistoryPagination;
import com.readme.novels.episodes.service.EpisodeHistoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.Fetch;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/history")
@Slf4j
@RequiredArgsConstructor
public class EpisodeHistoryController {

    private final EpisodeHistoryService episodeHistoryService;

    @Operation(summary = "읽은 내역 조회", description = "읽은 내역 조회", tags = {"읽은 내역"})
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "OK"),
        @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
        @ApiResponse(responseCode = "404", description = "NOT FOUND"),
        @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    @GetMapping
    public ResponseEntity<CommonDataResponse<ResponseEpisodeHistoryPagination>> getEpisodeHistoryByUser(
        @RequestHeader("uuid") String uuid,
        @PageableDefault(size = 12) Pageable pageable) {
        EpisodeHistoryPaginationDto episodeHistoryPaginationDto
            = episodeHistoryService.getEpisodeHistoryByUser(uuid, pageable);

        return ResponseEntity.ok(new CommonDataResponse<>(
            new ResponseEpisodeHistoryPagination(
                episodeHistoryPaginationDto.getContents().stream().map(ResponseEpisodeHistory::new)
                    .collect(
                        Collectors.toList()),
                episodeHistoryPaginationDto.getPagination())
        ));
    }

    @Operation(summary = "읽은 내역 삭제", description = "읽은 내역 삭제", tags = {"읽은 내역"})
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "OK"),
        @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
        @ApiResponse(responseCode = "404", description = "NOT FOUND"),
        @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    @DeleteMapping("/{historyId}")
    public void deleteEpisodeHistoryByUser(@RequestHeader("uuid") String uuid,
        @PathVariable Long historyId) {
        episodeHistoryService.deleteEpisodeHistoryByUser(uuid, historyId);

    }

    @PatchMapping
    public void updateHistoryReadAt(@RequestHeader("uuid") String uuid,
        @RequestBody RequestUpdateReadAt requestUpdateReadAt) {
        episodeHistoryService.updateEpisodeReadAt(new UpdateReadAtDto(uuid, requestUpdateReadAt));
    }

    @GetMapping("/novel/{novelId}")
    public ResponseEntity<CommonDataResponse<List<ResponseEpisodeHistory>>> getReadEpisodeByNovelId(
        @RequestHeader(value = "uuid") String uuid, @PathVariable Long novelId) {
        List<EpisodeHistoryDto> episodeHistoryDtoList
            = episodeHistoryService.getReadEpisodeByNovelId(uuid, novelId);

        List<ResponseEpisodeHistory> responseEpisodeHistoryList = new ArrayList<>();
        episodeHistoryDtoList.forEach(episodeHistoryDto -> {
            responseEpisodeHistoryList.add(new ResponseEpisodeHistory(episodeHistoryDto));
        });

        return ResponseEntity.ok(new CommonDataResponse<>(responseEpisodeHistoryList));
    }

}
