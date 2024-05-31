CREATE TABLE IF NOT EXISTS users
(
    id       BIGINT AUTO_INCREMENT PRIMARY KEY,
    name     VARCHAR(255) NOT NULL,
    lastname VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    email    VARCHAR(255) NOT NULL UNIQUE,
    phone    VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS movies
(
    id           BIGINT AUTO_INCREMENT PRIMARY KEY,
    title        VARCHAR(255) NOT NULL,
    description  TEXT,
    release_date DATE,
    genre        VARCHAR(100),
    is_public    BOOLEAN,
    user_id      BIGINT       NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users (id)
);