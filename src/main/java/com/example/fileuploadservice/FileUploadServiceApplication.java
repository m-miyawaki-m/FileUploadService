package com.example.fileuploadservice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@MapperScan("com.example.fileuploadservice.mapper")
public class FileUploadServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(FileUploadServiceApplication.class, args);
    }

    @Bean
    ApplicationRunner displayEndpoints() {
        return args -> {
            System.out.println("Application started successfully!");
            System.out.println("Access the file upload page at: http://localhost:8080/files/upload");
            System.out.println("API endpoint for file upload: http://localhost:8080/api/files/upload");
        };
    }
}
