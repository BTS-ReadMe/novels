package com.readme.novels.novels.aws;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/s3")
public class AwsS3Controller {

    private final AwsS3Service awsS3Service;

    @Operation(summary = "AWS S3 사진 등록", description = "AWS S3 사진 등록, MultipartFile로 파라미터 전달", tags = {"AWS"})
    @PostMapping("/file")
    public ResponseEntity<String> uploadFile(@RequestPart MultipartFile multipartFile) {
        return ResponseEntity.ok().body(awsS3Service.uploadFile(multipartFile));
    }

}
