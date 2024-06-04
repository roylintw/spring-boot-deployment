CREATE TABLE IF NOT EXISTS member
(
    member_id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    account VARCHAR(256) NOT NULL UNIQUE,
    address VARCHAR(256),
    birth_date date,
    create_date date,
    email VARCHAR(256),
    gender VARCHAR(256),
    member_name VARCHAR(256),
    password VARCHAR(256),
    region VARCHAR(256)
);

CREATE TABLE IF NOT EXISTS role
(
    role_id   INT          NOT NULL PRIMARY KEY AUTO_INCREMENT,
    role_name VARCHAR(256) NOT NULL
);

CREATE TABLE IF NOT EXISTS member_has_role
(
    member_has_role_id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    member_id          INT NOT NULL,
    role_id            INT NOT NULL
);