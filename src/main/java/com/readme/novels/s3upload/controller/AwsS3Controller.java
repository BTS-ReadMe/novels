package com.readme.novels.s3upload.controller;

import com.readme.novels.s3upload.service.AwsS3Service;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/s3")
@Slf4j
public class AwsS3Controller {

    private final AwsS3Service awsS3Service;

    @Operation(summary = "AWS S3 사진 등록", description = "AWS S3 사진 등록, MultipartFile로 파라미터 전달", tags = {"AWS"})
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "OK"),
        @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
        @ApiResponse(responseCode = "404", description = "NOT FOUND"),
        @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    @PutMapping("/file")
    public ResponseEntity<String> uploadFile(@RequestPart MultipartFile multipartFile) {

        log.info("사진 등록!! 컨트롤러 진입!");
        return ResponseEntity.ok().body(awsS3Service.uploadFile(multipartFile));
    }

}
