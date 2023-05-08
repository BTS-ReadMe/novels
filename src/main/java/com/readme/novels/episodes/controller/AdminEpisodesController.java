package com.readme.novels.episodes.controller;

import com.readme.novels.episodes.dto.EpisodesDto;
import com.readme.novels.episodes.requestObject.RequestAddEpisodes;
import com.readme.novels.episodes.requestObject.RequestUpdateEpisodes;
import com.readme.novels.episodes.responseObject.ResponseEpisodes;
import com.readme.novels.episodes.service.EpisodesService;
import com.readme.novels.utils.CommonResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/admin/episodes")
@Slf4j
@RequiredArgsConstructor
public class AdminEpisodesController {

    private final EpisodesService episodesService;

    @PostMapping
    public void addEpisodes(@RequestBody RequestAddEpisodes requestAddEpisodes) {
        ModelMapper mapper = new ModelMapper();
        EpisodesDto episodesDto = mapper.map(requestAddEpisodes, EpisodesDto.class);

        episodesService.addEpisodes(episodesDto);
    }

    @PutMapping("/{id}")
    public void updateEpisodes(@RequestBody RequestUpdateEpisodes requestUpdateEpisodes,
        @PathVariable Long id) {
        ModelMapper mapper = new ModelMapper();
        EpisodesDto episodesDto = mapper.map(requestUpdateEpisodes, EpisodesDto.class);

        episodesService.updateEpisodes(id, episodesDto);

    }

    @DeleteMapping("/{id}")
    public void deleteEpisodes(@PathVariable Long id){
        episodesService.deleteEpisodes(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommonResponse> getEpisodes(@PathVariable Long id) {

        EpisodesDto episodesDto = episodesService.getEpisodesById(id);

        return ResponseEntity.ok(new CommonResponse(
            ResponseEpisodes.builder()
                .id(episodesDto.getId())
                .title(episodesDto.getTitle())
                .content(episodesDto.getContent())
                .registration(episodesDto.getRegistration())
                .free(episodesDto.getFree())
                .status(episodesDto.getStatus())
                .build()
        ));
    }

}
