CREATE TABLE file_metadata (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    file_name VARCHAR(255) NOT NULL,
    s3_key VARCHAR(255) NOT NULL UNIQUE,
    bucket_name VARCHAR(255) NOT NULL,
    file_size BIGINT NOT NULL,
    content_type VARCHAR(255),
    upload_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

ALTER TABLE file_metadata
ADD COLUMN title VARCHAR(255) NOT NULL, -- 件名
ADD COLUMN user_group VARCHAR(255) NOT NULL; -- 所属グループ
