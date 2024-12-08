package com.example.fileuploadservice.model;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class FileMetadata {
    private Long id;                 // ファイルの一意なID
    private String fileName;         // ファイル名
    private String s3Key;            // S3のキーまたはローカル保存パス
    private String bucketName;       // S3バケット名
    private Long fileSize;           // ファイルサイズ
    private String contentType;      // コンテンツタイプ
    private String title;            // 件名
    private String userGroup;        // ユーザーの所属グループ
    private LocalDateTime uploadDate; // アップロード日時
}
