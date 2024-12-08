package com.example.fileuploadservice.dto;

import jakarta.validation.constraints.NotBlank;
import org.springframework.web.multipart.MultipartFile;

public class FileUploadRequest {
    @NotBlank(message = "Title is required") // 必須
    private String title = "Untitled"; // デフォルト値

    @NotBlank(message = "User group is required") // 必須
    private String userGroup = "UnknownGroup"; // デフォルト値

    private MultipartFile file; // 必須はコントローラーでチェック

    // ゲッターとセッター
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUserGroup() {
        return userGroup;
    }

    public void setUserGroup(String userGroup) {
        this.userGroup = userGroup;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }
}
