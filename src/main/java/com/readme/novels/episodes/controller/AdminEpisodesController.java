package com.readme.novels.episodes.controller;

import com.readme.novels.episodes.dto.EpisodesDto;
import com.readme.novels.episodes.dto.EpisodesPageDto;
import com.readme.novels.episodes.requestObject.RequestAddEpisodes;
import com.readme.novels.episodes.requestObject.RequestUpdateEpisodes;
import com.readme.novels.episodes.responseObject.ResponseEpisodes;
import com.readme.novels.episodes.responseObject.ResponseEpisodesByNovels;
import com.readme.novels.episodes.responseObject.ResponseEpisodesPagination;
import com.readme.novels.episodes.responseObject.ResponseEpisodesPagination.Pagination;
import com.readme.novels.episodes.service.EpisodesService;
import com.readme.novels.novels.responseObject.ResponseNovelsPagination;
import com.readme.novels.utils.CommonResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    public void deleteEpisodes(@PathVariable Long id) {
        episodesService.deleteEpisodes(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommonResponse<ResponseEpisodes>> getEpisodesById(@PathVariable Long id) {

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

    @GetMapping
    public ResponseEntity<CommonResponse<ResponseEpisodesPagination>> getEpisodesByNovels(@RequestParam Long novelId,
        @PageableDefault(size = 10) Pageable pageable) {

        EpisodesPageDto episodesPageDto = episodesService.getEpisodesByNovelsId(novelId, pageable);

        return ResponseEntity.ok(
            new CommonResponse(
                ResponseNovelsPagination.builder()
                    .contents(episodesPageDto.getContents().stream().map(episodesDto ->
                        ResponseEpisodesByNovels.builder()
                            .id(episodesDto.getId())
                            .title(episodesDto.getTitle())
                            .registration(episodesDto.getRegistration())
                            .free(episodesDto.getFree())
                            .build()
                    ))
                    .pagination(
                        episodesPageDto.getPagination()
                    )
                    .build()
            )
        );
    }

}
