package com.example.fileuploadservice.mapper;

import com.example.fileuploadservice.model.FileMetadata;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface FileMetadataMapper {
    void insertFileMetadata(FileMetadata fileMetadata);
    FileMetadata findById(Long id);

    List<FileMetadata> searchFiles(
            @Param("title") String title
            , @Param("fileName") String fileName
            , @Param("offset") int offset
            , @Param("pageSize") int pageSize
            );

    long countFiles(
            @Param("title") String title,
            @Param("fileName") String fileName);
}
