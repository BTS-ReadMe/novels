package com.readme.novels.episodes.controller;

import com.readme.novels.episodes.dto.EpisodesDto;
import com.readme.novels.episodes.dto.EpisodesPageDto;
import com.readme.novels.episodes.requestObject.RequestAddEpisodes;
import com.readme.novels.episodes.requestObject.RequestUpdateEpisodes;
import com.readme.novels.episodes.responseObject.ResponseEpisodes;
import com.readme.novels.episodes.responseObject.ResponseEpisodesByNovels;
import com.readme.novels.episodes.responseObject.ResponseEpisodesPagination;
import com.readme.novels.episodes.service.EpisodesService;
import com.readme.novels.novels.responseObject.ResponseNovelsPagination;
import com.readme.novels.commonResponseObject.CommonDataResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "에피소드 추가", description = "에피소드 추가", tags = {"Admin 에피소드"})
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "OK"),
        @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
        @ApiResponse(responseCode = "404", description = "NOT FOUND"),
        @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    @PostMapping
    public void addEpisodes(@RequestBody RequestAddEpisodes requestAddEpisodes) {
        ModelMapper mapper = new ModelMapper();
        EpisodesDto episodesDto = mapper.map(requestAddEpisodes, EpisodesDto.class);

        episodesService.addEpisodes(episodesDto);
    }

    @Operation(summary = "에피소드 수정", description = "에피소드 수정, 수정할 에피소드 id url 전달", tags = {"Admin 에피소드"})
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "OK"),
        @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
        @ApiResponse(responseCode = "404", description = "NOT FOUND"),
        @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    @PutMapping("/{id}")
    public void updateEpisodes(@RequestBody RequestUpdateEpisodes requestUpdateEpisodes,
        @PathVariable Long id) {
        ModelMapper mapper = new ModelMapper();
        EpisodesDto episodesDto = mapper.map(requestUpdateEpisodes, EpisodesDto.class);

        episodesService.updateEpisodes(id, episodesDto);

    }

    @Operation(summary = "에피소드 삭제", description = "에피소드 삭제, soft delete 삭제", tags = {"Admin 에피소드"})
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "OK"),
        @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
        @ApiResponse(responseCode = "404", description = "NOT FOUND"),
        @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    @DeleteMapping("/{id}")
    public void deleteEpisodes(@PathVariable Long id) {
        episodesService.deleteEpisodes(id);
    }

    @Operation(summary = "에피소드 단건 조회", description = "에피소드 단건 조회, 조회할 에피소드 id url 전달", tags = {"Admin 에피소드"})
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "OK"),
        @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
        @ApiResponse(responseCode = "404", description = "NOT FOUND"),
        @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    @GetMapping("/{id}")
    public ResponseEntity<CommonDataResponse<ResponseEpisodes>> getEpisodesById(@PathVariable Long id) {

        EpisodesDto episodesDto = episodesService.getEpisodesById(id);

        return ResponseEntity.ok(new CommonDataResponse(
            ResponseEpisodes.builder()
                .id(episodesDto.getId())
                .title(episodesDto.getTitle())
                .content(episodesDto.getContent())
                .registration(episodesDto.getRegistration())
                .free(episodesDto.getFree())
                .status(episodesDto.getStatus())
                .createDate(episodesDto.getCreateDate())
                .updateDate(episodesDto.getUpdateDate())
                .build()
        ));
    }

    @Operation(summary = "에피소드 목록 조회", description = "에피소드 목록 조회, 10개씩 페이징 처리", tags = {"Admin 에피소드"})
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "OK"),
        @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
        @ApiResponse(responseCode = "404", description = "NOT FOUND"),
        @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    @GetMapping
    public ResponseEntity<CommonDataResponse<ResponseEpisodesPagination>> getEpisodesByNovels(@RequestParam Long novelId,
        @PageableDefault(size = 10) Pageable pageable) {

        EpisodesPageDto episodesPageDto = episodesService.getEpisodesByNovelsId(novelId, pageable);

        return ResponseEntity.ok(
            new CommonDataResponse(
                ResponseNovelsPagination.builder()
                    .contents(episodesPageDto.getContents().stream().map(episodesDto ->
                        ResponseEpisodesByNovels.builder()
                            .id(episodesDto.getId())
                            .title(episodesDto.getTitle())
                            .registration(episodesDto.getRegistration())
                            .free(episodesDto.getFree())
                            .createDate(episodesDto.getCreateDate())
                            .updateDate(episodesDto.getUpdateDate())
                            .status(episodesDto.getStatus())
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
