-- DIRECTORS
INSERT INTO director (id, name, surname)
VALUES (NEXT VALUE FOR director_seq, 'Christopher', 'Nolan');

INSERT INTO director (id, name, surname)
VALUES (NEXT VALUE FOR director_seq, 'Quentin', 'Tarantino');

INSERT INTO director (id, name, surname)
VALUES (NEXT VALUE FOR director_seq, 'Steven', 'Spielberg');


-- ACTORS
INSERT INTO actor (id, name, surname)
VALUES (NEXT VALUE FOR actor_seq, 'Leonardo', 'DiCaprio');

INSERT INTO actor (id, name, surname)
VALUES (NEXT VALUE FOR actor_seq, 'Brad', 'Pitt');

INSERT INTO actor (id, name, surname)
VALUES (NEXT VALUE FOR actor_seq, 'Tom', 'Hanks');

INSERT INTO actor (id, name, surname)
VALUES (NEXT VALUE FOR actor_seq, 'Samuel', 'Jackson');

INSERT INTO actor (id, name, surname)
VALUES (NEXT VALUE FOR actor_seq, 'Joseph', 'Gordon-Levitt');


-- MOVIES
INSERT INTO movie (id, title, genre, director_id)
VALUES (NEXT VALUE FOR movie_seq, 'Inception', 'Sci-Fi', 1);

INSERT INTO movie (id, title, genre, director_id)
VALUES (NEXT VALUE FOR movie_seq, 'Pulp Fiction', 'Crime', 2);

INSERT INTO movie (id, title, genre, director_id)
VALUES (NEXT VALUE FOR movie_seq, 'Saving Private Ryan', 'War', 3);


-- MANY TO MANY
INSERT INTO movie_actor (movie_id, actor_id)
VALUES (1, 1);

INSERT INTO movie_actor (movie_id, actor_id)
VALUES (1, 5);

INSERT INTO movie_actor (movie_id, actor_id)
VALUES (2, 2);

INSERT INTO movie_actor (movie_id, actor_id)
VALUES (2, 4);

INSERT INTO movie_actor (movie_id, actor_id)
VALUES (3, 3);