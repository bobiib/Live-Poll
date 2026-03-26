DROP TABLE IF EXISTS user_authorities;
DROP TABLE IF EXISTS refresh_tokens;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS inquiries;


CREATE TABLE users (
                       user_id uuid PRIMARY KEY DEFAULT uuidv7(),
                       email VARCHAR(255) UNIQUE ,
                       password VARCHAR(255),
                       created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);