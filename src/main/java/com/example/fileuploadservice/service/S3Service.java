package com.example.fileuploadservice.service;

import java.io.IOException;
import java.io.InputStream;

public interface S3Service {
    void uploadFile(String key, InputStream inputStream, long fileSize, String contentType) throws IOException;
    public void deleteFile(String bucketName, String s3Key) ;
}