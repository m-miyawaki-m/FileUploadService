package com.example.fileuploadservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.fileuploadservice.mapper.FileMetadataMapper;
import com.example.fileuploadservice.model.FileMetadata;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;


@RestController
@RequestMapping("/api/files")
public class FileSearchController {

    @Autowired
    private FileMetadataMapper fileMetadataMapper;

    @GetMapping("/search")
    public Page<FileMetadata> searchFiles(
            @RequestParam(required = false, defaultValue = "") String title,
            @RequestParam(required = false, defaultValue = "") String fileName,
            @PageableDefault(size = 20) Pageable pageable) {

        // ページング情報計算
        int offset = pageable.getPageNumber() * pageable.getPageSize();
        int pageSize = pageable.getPageSize();

        // 検索結果
        List<FileMetadata> results = fileMetadataMapper.searchFiles(
                title.isEmpty() ? null : title
                , fileName.isEmpty() ? null : fileName
                , offset
                , pageSize
        );

        // 総件数
        long totalCount = fileMetadataMapper.countFiles(
                title.isEmpty() ? null : title,
                fileName.isEmpty() ? null : fileName
        );

        return new PageImpl<>(results, pageable, totalCount);
    }

}
