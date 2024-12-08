package com.example.fileuploadservice.controller;

import com.example.fileuploadservice.dto.FileUploadRequest;
import com.example.fileuploadservice.mapper.FileMetadataMapper;
import com.example.fileuploadservice.model.FileMetadata;
import com.example.fileuploadservice.service.S3Service;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/files")
@Validated
public class FileApiController {

    @Autowired
    private S3Service s3Service;

    @Autowired
    private FileMetadataMapper fileMetadataMapper;

    @PostMapping("/upload")
    public String uploadFile(@Valid @ModelAttribute FileUploadRequest request) throws IOException {
        MultipartFile file = request.getFile();
        if (file == null || file.isEmpty()) {
            throw new IllegalArgumentException("File is required");
        }

        String title = request.getTitle();
        String userGroup = request.getUserGroup();

        // S3にアップロード
        String s3Key = "uploads/" + file.getOriginalFilename();
        s3Service.uploadFile(s3Key, file.getInputStream(), file.getSize(), file.getContentType());

        // DBにメタデータを保存
        FileMetadata metadata = new FileMetadata();
        metadata.setFileName(file.getOriginalFilename());
        metadata.setS3Key(s3Key);
        metadata.setBucketName("udemy-test20241205");
        metadata.setFileSize(file.getSize());
        metadata.setContentType(file.getContentType());
        metadata.setTitle(title); // 件名を保存（必要ならカラムを追加）
        metadata.setUserGroup(userGroup); // 所属グループを保存（必要ならカラムを追加）
        fileMetadataMapper.insertFileMetadata(metadata);

        return "File uploaded successfully!";
    }

}