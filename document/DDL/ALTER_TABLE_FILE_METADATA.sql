ALTER TABLE file_metadata
ADD COLUMN title VARCHAR(255) NOT NULL, -- 件名
ADD COLUMN user_group VARCHAR(255) NOT NULL; -- 所属グループ
