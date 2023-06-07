package com.readme.novels.novels.controller;

import com.readme.novels.episodes.service.EpisodeHistoryService;
import com.readme.novels.novels.dto.NovelsDeleteKafkaDto;
import com.readme.novels.novels.dto.NovelsDto;
import com.readme.novels.novels.dto.NovelsKafkaDto;
import com.readme.novels.novels.dto.PaginationDto;
import com.readme.novels.novels.dto.ResponseNovelsDto;
import com.readme.novels.novels.messagequeue.NovelsKafkaProducer;
import com.readme.novels.novels.requestObject.RequestAddNovels;
import com.readme.novels.novels.dto.NovelsSearchParamDto;
import com.readme.novels.novels.requestObject.RequestUpdateNovels;
import com.readme.novels.commonResponseObject.CommonDataResponse;
import com.readme.novels.novels.responseObject.ResponseNovels;
import com.readme.novels.novels.responseObject.ResponseNovelsPagination;
import com.readme.novels.novels.responseObject.ResponseNovelsPagination.Pagination;
import com.readme.novels.novels.service.NovelsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
@RequestMapping("/v1/admin/novels")
@RequiredArgsConstructor
@Slf4j
public class AdminNovelsController {

    private final NovelsService novelsService;
    private final NovelsKafkaProducer novelsKafkaProducer;
    private final EpisodeHistoryService episodeHistoryService;

    @Operation(summary = "소설 추가", description = "소설 추가", tags = {"Admin 소설"})
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "OK"),
        @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
        @ApiResponse(responseCode = "404", description = "NOT FOUND"),
        @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    @PostMapping
    public void addNovels(@RequestBody RequestAddNovels requestAddNovels) {
        NovelsDto novelsDto = new NovelsDto(requestAddNovels);

        novelsService.addNovels(novelsDto);

        novelsKafkaProducer.addNovels("addNovels", new NovelsKafkaDto(novelsDto));

    }

    @Operation(summary = "소설 정보 수정", description = "소설 정보 수정, 수정할 소설 id url 전달", tags = {
        "Admin 소설"})
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "OK"),
        @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
        @ApiResponse(responseCode = "404", description = "NOT FOUND"),
        @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    @PutMapping("/{id}")
    public void updateNovels(@RequestBody RequestUpdateNovels requestUpdateNovels,
        @PathVariable Long id) {
        NovelsDto novelsDto = new NovelsDto(requestUpdateNovels, id);

        novelsService.updateNovels(novelsDto);

        novelsKafkaProducer.updateNovels("updateNovels", new NovelsKafkaDto(novelsDto));

    }

    @Operation(summary = "소설 삭제", description = "소설 삭제, soft delete 삭제", tags = {"Admin 소설"})
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "OK"),
        @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
        @ApiResponse(responseCode = "404", description = "NOT FOUND"),
        @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    @DeleteMapping("/{id}")
    public void deleteNovels(@PathVariable Long id) {
        novelsService.deleteNovels(id);

        novelsKafkaProducer.deleteNovels("deleteNovels", new NovelsDeleteKafkaDto(id));

        episodeHistoryService.deleteEpisodeByNovelsId(id);
    }

    @Operation(summary = "소설 단건 조회", description = "소설 단건 조회, 조회할 소설 id url 전달", tags = {
        "Admin 소설"})
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "OK"),
        @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
        @ApiResponse(responseCode = "404", description = "NOT FOUND"),
        @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    @GetMapping("/{id}")
    public ResponseEntity<CommonDataResponse<ResponseNovels>> getNovelsOne(@PathVariable Long id) {

        ResponseNovelsDto responseNovelsDto = novelsService.getNovelsById(id);

        return ResponseEntity.ok()
            .body(new CommonDataResponse<>(new ResponseNovels(responseNovelsDto)));
    }

    @Operation(summary = "소설 목록 조회", description = "소설 목록 조회, 작가, 제목 검색 가능, 10개씩 페이징 처리", tags = {
        "Admin 소설"})
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "OK"),
        @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
        @ApiResponse(responseCode = "404", description = "NOT FOUND"),
        @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    @GetMapping
    public ResponseEntity<CommonDataResponse<ResponseNovelsPagination>> getNovelsAll(
        @RequestParam(required = false) String author,
        @RequestParam(required = false) String title,
        @RequestParam(required = false) Integer page) {

        NovelsSearchParamDto novelsSearchParamDto = NovelsSearchParamDto
            .builder()
            .page(page)
            .author(author)
            .title(title)
            .build();

        List<ResponseNovelsDto> novelsDtoList = novelsService.getNovels(novelsSearchParamDto);
        PaginationDto paginationDto = novelsService.getPagination(novelsSearchParamDto);

        return ResponseEntity.ok(
            new CommonDataResponse<>(new ResponseNovelsPagination(novelsDtoList.stream()
                .map(responseNovelsDto -> new ResponseNovels(responseNovelsDto))
                , new Pagination(paginationDto))));

    }

}
