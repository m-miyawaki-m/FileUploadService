package com.example.fileuploadservice.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.fileuploadservice.model.FileMetadata;

import lombok.extern.slf4j.Slf4j;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.InputStream;
import java.util.List;

@Slf4j
@Service
@Profile("prod") // 本番環境用
public class S3ServiceImpl implements S3Service {

    @Value("${aws.s3.bucket.name}")
    private String bucketName;

    @Value("${aws.access.key.id}")
    private String accessKey;

    @Value("${aws.secret.access.key}")
    private String secretKey;

    @Value("${aws.s3.region}")
    private String region;

    /**
     * ファイルをS3にアップロード
     */
    @Override
    public void uploadFile(String key, InputStream inputStream, long fileSize, String contentType) {
        log.info("Starting file upload to S3. Key: {}, Bucket: {}, Region: {}", key, bucketName, region);

        S3Client s3Client = createS3Client();

        try {
            log.debug("Attempting to upload file. Content-Type: {}, File Size: {}", contentType, fileSize);
            s3Client.putObject(
                    PutObjectRequest.builder()
                            .bucket(bucketName)
                            .key(key)
                            .contentType(contentType)
                            .build(),
                    software.amazon.awssdk.core.sync.RequestBody.fromInputStream(inputStream, fileSize)
            );
            log.info("File uploaded successfully. Key: {}", key);
        } catch (Exception e) {
            log.error("Failed to upload file to S3. Key: {}, Error: {}", key, e.getMessage(), e);
            throw new RuntimeException("File upload failed", e);
        }
    }

    /**
     * S3からファイルを削除
     */
    @Override
    public void deleteFile(String bucketName, String s3Key) {
        log.info("Starting file deletion from S3. Key: {}, Bucket: {}", s3Key, bucketName);

        S3Client s3Client = createS3Client();

        try {
            s3Client.deleteObject(DeleteObjectRequest.builder()
                    .bucket(bucketName)
                    .key(s3Key)
                    .build());
            log.info("File deleted successfully. Key: {}, Bucket: {}", s3Key, bucketName);
        } catch (Exception e) {
            log.error("Failed to delete file from S3. Key: {}, Bucket: {}, Error: {}", s3Key, bucketName, e.getMessage(), e);
            throw new RuntimeException("File deletion failed", e);
        }
    }

    /**
     * S3クライアントを作成
     */
    private S3Client createS3Client() {
        return S3Client.builder()
                .region(Region.of(region))
                .credentialsProvider(StaticCredentialsProvider.create(
                        AwsBasicCredentials.create(accessKey, secretKey)))
                .build();
    }
}

