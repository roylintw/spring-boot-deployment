-- 密碼的值為「帳號去掉 @gmail.com」，例如 admin@gmail.com 的密碼為 admin
INSERT INTO member(account, email, password, member_name) VALUES ('admin', 'admin@gmail.com', '$2a$10$DuMH/aNBaYtHT0j/b2swG.0Z7eYJ8Sckw06PSdbY4Kqy4PzZxQJeu', 'Admin');
INSERT INTO member(account, email, password, member_name) VALUES ('normal', 'normal@gmail.com', '$2a$10$B/OtT9gtlgsh/w8tdO.ISeFHNNzcILIgRuh2vDwFRUSPGRC.WqWRq', 'Normal Member');
INSERT INTO member(account, email, password, member_name) VALUES ('vip', 'vip@gmail.com', '$2a$10$/cAcYaD3L4cvf09pC4YwkOB.KHPi.JzAXG1qoqtTVniaOeDog5Atm', 'VIP Member');
INSERT INTO member(account, email, password, member_name) VALUES ('movie-manager', 'movie-manager@gmail.com', '$2a$10$4ygMq.sezGuSU7UxNPiVwefcWSVZq17z98MrTK1KhsO2AMQU1qbDa', 'Movie Manager');
INSERT INTO member(account, password, member_name, email) VALUES ('Jack', '$2a$10$mNZNVrKz7/6g.Ix8ZHII2.WYclQkyZIRVgfrXRwkguJ3gdx4hGK.2', '王阿東', 'xxx@gmail.com');

INSERT INTO role(role_name) VALUES ('ROLE_ADMIN');
INSERT INTO role(role_name) VALUES ('ROLE_NORMAL_MEMBER');
INSERT INTO role(role_name) VALUES ('ROLE_VIP_MEMBER');
INSERT INTO role(role_name) VALUES ('ROLE_MOVIE_MANAGER');

INSERT INTO member_has_role (member_id, role_id) VALUES (1, 1);
INSERT INTO member_has_role (member_id, role_id) VALUES (2, 2);
INSERT INTO member_has_role (member_id, role_id) VALUES (3, 2);
INSERT INTO member_has_role (member_id, role_id) VALUES (3, 3);
INSERT INTO member_has_role (member_id, role_id) VALUES (4, 4);