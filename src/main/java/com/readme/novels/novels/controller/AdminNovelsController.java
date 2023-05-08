package com.readme.novels.novels.controller;

import com.readme.novels.novels.dto.NovelsDto;
import com.readme.novels.novels.dto.PaginationDto;
import com.readme.novels.novels.requestObject.RequestAddNovels;
import com.readme.novels.novels.dto.NovelsSearchParamDto;
import com.readme.novels.novels.requestObject.RequestUpdateNovels;
import com.readme.novels.utils.CommonResponse;
import com.readme.novels.novels.responseObject.ResponseNovels;
import com.readme.novels.novels.responseObject.ResponseNovelsPagination;
import com.readme.novels.novels.responseObject.ResponseNovelsPagination.Pagination;
import com.readme.novels.novels.service.NovelsService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.core.env.Environment;
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
    private final Environment env;

    @GetMapping("/health_check")
    public String status() {
        return String.format("It's Working in User Service on PORT : %s"
            , env.getProperty("local.server.port"));
    }

    @PostMapping
    public void addNovels(@RequestBody RequestAddNovels requestAddNovels) {
        ModelMapper mapper = new ModelMapper();
        NovelsDto novelsDto = mapper.map(requestAddNovels, NovelsDto.class);

        novelsService.addNovels(novelsDto);

    }

    @PutMapping("/{id}")
    public void updateNovels(@RequestBody RequestUpdateNovels requestUpdateNovels,
        @PathVariable Long id) {
        ModelMapper mapper = new ModelMapper();
        NovelsDto novelsDto = mapper.map(requestUpdateNovels, NovelsDto.class);
        novelsDto.setId(id);

        log.info(novelsDto.toString());

        novelsService.updateNovels(novelsDto);

    }

    @DeleteMapping("/{id}")
    public void deleteNovels(@PathVariable Long id) {
        novelsService.deleteNovels(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommonResponse<ResponseNovels>> getNovelsOne(@PathVariable Long id) {

        NovelsDto novelsDto = novelsService.getNovelsById(id);

        return ResponseEntity.ok().body(new CommonResponse(ResponseNovels.builder()
            .id(novelsDto.getId())
            .title(novelsDto.getTitle())
            .author(novelsDto.getAuthor())
            .description(novelsDto.getDescription())
            .startDate(novelsDto.getStartDate())
            .serializationDay(novelsDto.getSerializationDay())
            .thumbnail(novelsDto.getThumbnail())
            .serializationStatus(novelsDto.getSerializationStatus())
            .genre(novelsDto.getGenre())
            .grade(novelsDto.getGrade())
            .authorComment(novelsDto.getAuthorComment())
            .build()));
    }

    @GetMapping
    public ResponseEntity<CommonResponse<ResponseNovelsPagination>> getNovelsAll(
        @RequestParam(required = false) String author,
        @RequestParam(required = false) String title,
        @RequestParam(required = false) Integer page) {

        NovelsSearchParamDto novelsSearchParamDto = NovelsSearchParamDto
            .builder()
            .page(page)
            .author(author)
            .title(title)
            .build();

        List<NovelsDto> novelsDtoList = novelsService.getNovels(novelsSearchParamDto);
        PaginationDto paginationDto = novelsService.getPagination(novelsSearchParamDto);

        return ResponseEntity.ok(
            new CommonResponse(ResponseNovelsPagination.builder()
                .contents(novelsDtoList.stream().map(novelsDto -> ResponseNovels.builder()
                    .id(novelsDto.getId())
                    .title(novelsDto.getTitle())
                    .author(novelsDto.getAuthor())
                    .description(novelsDto.getDescription())
                    .startDate(novelsDto.getStartDate())
                    .serializationDay(novelsDto.getSerializationDay())
                    .serializationStatus(novelsDto.getSerializationStatus())
                    .thumbnail(novelsDto.getThumbnail())
                    .genre(novelsDto.getGenre())
                    .grade(novelsDto.getGrade())
                    .authorComment(novelsDto.getAuthorComment())
                    .build()))
                .pagination(Pagination.builder()
                    .page(paginationDto.getPage())
                    .size(paginationDto.getSize())
                    .totalElements(paginationDto.getTotalElements())
                    .totalPage(paginationDto.getTotalPage())
                    .build()
                )
                .build()));
    };

}
