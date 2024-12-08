package com.example.fileuploadservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.example.fileuploadservice.model.LocalConfig;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

@Service
@Profile("local") // ローカル環境用
public class LocalS3ServiceImpl implements S3Service {

    private final LocalConfig localConfig;

    public LocalS3ServiceImpl(LocalConfig localStorageConfig) {
        this.localConfig = localStorageConfig;
    }

    @Override
    public void uploadFile(String key, InputStream inputStream, long fileSize, String contentType) throws IOException {
        // ローカルの保存パスを決定
        File file = new File(localConfig.getStoragePath() + File.separator + key);
        file.getParentFile().mkdirs(); // 必要なディレクトリを作成

        // ファイルを保存
        try (OutputStream outputStream = new FileOutputStream(file)) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
        }

        System.out.println("File saved locally at: " + file.getAbsolutePath());
    }

    @Override
    public void deleteFile(String bucketName, String s3Key) {
        // TODO 自動生成されたメソッド・スタブ
        
    }
}
