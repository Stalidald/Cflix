INSERT INTO achivement  (name, description, version) VALUES ('Registration', 'Congratulation, you are a member now!', 0);

INSERT INTO movie(title, age_limit, release_year, description, rating, version) VALUES ( 'Titanic', 12, 1997, 'Egy hajókatasztrófa megfilmesítése.', 7.8, 0);
INSERT INTO movie(title, age_limit, release_year, description, rating, version) VALUES ( 'Avatar', 12, 2009, 'Egy másik élőforma felfedezése.', 7.8, 0);

INSERT INTO movie_members(name, role, version) VALUES ( 'Leonardo Di Caprio', 'Actor', 0);

INSERT INTO movie_members(name, role, version) VALUES ( 'Kate Winslet', 'Actress', 0);
INSERT INTO movie_members(name, role, version) VALUES ( 'Zoe Saldana', 'Actress', 0);

SET FOREIGN_KEY_CHECKS=0;
INSERT INTO movie_related_movie_members (movie_entity_id, related_movie_members_id)  VALUES (1, 1);
INSERT INTO movie_related_movie_members (movie_entity_id, related_movie_members_id)  VALUES (1, 2);
INSERT INTO movie_related_movie_members (movie_entity_id, related_movie_members_id)  VALUES (1, 3);
INSERT INTO movie_related_movie_members (movie_entity_id, related_movie_members_id)  VALUES (2, 3);
SET FOREIGN_KEY_CHECKS=1;

INSERT INTO roles(name) VALUES ('ROLE_USER');
INSERT INTO roles(name) VALUES ('ROLE_PARENT');
INSERT INTO roles(name) VALUES ('ROLE_CHILD');
INSERT INTO roles(name) VALUES ('ROLE_ADMIN');
