package com.readme.novels.episodes.controller;

import com.readme.novels.episodes.dto.EpisodesDtoByUser;
import com.readme.novels.episodes.dto.PlusViewsKafkaDto;
import com.readme.novels.episodes.messagequeue.EpisodesKafkaProducer;
import com.readme.novels.episodes.responseObject.ResponseEpisodesUser;
import com.readme.novels.episodes.service.EpisodesService;
import com.readme.novels.commonResponseObject.CommonDataResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/episodes")
@RequiredArgsConstructor
public class EpisodesController {

    private final EpisodesService episodesService;
    private final EpisodesKafkaProducer episodesKafkaProducer;

    @Operation(summary = "에피소드 조회", description = "에피소드 조회, 조회할 에피소드 id url 전달", tags = {"에피소드"})
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "OK"),
        @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
        @ApiResponse(responseCode = "404", description = "NOT FOUND"),
        @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    @GetMapping("/{id}")
    public ResponseEntity<CommonDataResponse<ResponseEpisodesUser>> getEpisodes(@PathVariable Long id) {

        EpisodesDtoByUser episodesDtoByUser = episodesService.getEpisodesByUser(id);

        // 조회수 증가 topic 전송
        PlusViewsKafkaDto plusViewsKafkaDto = new PlusViewsKafkaDto(episodesDtoByUser);
        episodesKafkaProducer.plusViewCount("plusViewCount", plusViewsKafkaDto);

        return ResponseEntity.ok(
            new CommonDataResponse<>(
                new ResponseEpisodesUser(episodesDtoByUser)
            )
        );
    }
}
