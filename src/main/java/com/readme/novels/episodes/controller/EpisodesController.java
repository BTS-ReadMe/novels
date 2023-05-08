package com.readme.novels.episodes.controller;

import com.readme.novels.episodes.dto.EpisodesDto;
import com.readme.novels.episodes.responseObject.ResponseEpisodesUser;
import com.readme.novels.episodes.service.EpisodesService;
import com.readme.novels.utils.CommonResponse;
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

    @GetMapping("/{id}")
    public ResponseEntity<CommonResponse<ResponseEpisodesUser>> getEpisodes(@PathVariable Long id) {

        EpisodesDto episodesDto = episodesService.getEpisodesByUser(id);

        return ResponseEntity.ok(
            new CommonResponse(
                ResponseEpisodesUser.builder()
                    .id(episodesDto.getId())
                    .title(episodesDto.getTitle())
                    .content(episodesDto.getContent())
                    .registration(episodesDto.getRegistration())
                    .build()
            )
        );
    }
}
