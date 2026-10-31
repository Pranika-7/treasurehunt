CREATE DATABASE IF NOT EXISTS treasure_hunt_db;
USE treasure_hunt_db;

CREATE TABLE IF NOT EXISTS player (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255),
    score INT NOT NULL
);

CREATE TABLE IF NOT EXISTS treasure (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    location_code VARCHAR(255),
    points INT NOT NULL,
    is_collected BOOLEAN NOT NULL,
    collected_by_player_id BIGINT
);
