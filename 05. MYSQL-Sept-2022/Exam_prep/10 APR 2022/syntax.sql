#1
CREATE SCHEMA softuni_imdb;

USE softuni_imdb;

CREATE TABLE movies_additional_info(
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    rating DECIMAL(10,2) NOT NULL,
    runtime INT NOT NULL,
    picture_url VARCHAR(80) NOT NULL,
    budget DECIMAL(10,2),
    release_date DATE NOT NULL,
    has_subtitles TINYINT(1),
    description TEXT
);

CREATE TABLE genres(
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) UNIQUE NOT NULL
);

CREATE TABLE countries(
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(30) UNIQUE NOT NULL,
    continent VARCHAR(30) NOT NULL,
    currency VARCHAR(5) NOT NULL
);

CREATE TABLE actors(
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    birthdate DATE NOT NULL,
    height INT,
    awards INT,
    country_id INT NOT NULL,
    CONSTRAINT fk_actors_countries
    FOREIGN KEY (country_id)
    REFERENCES countries(id)
);

CREATE TABLE movies(
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(70) UNIQUE NOT NULL,
    country_id INT NOT NULL,
    movie_info_id INT NOT NULL UNIQUE,
    CONSTRAINT fk_movies_countries
    FOREIGN KEY (country_id)
    REFERENCES countries(id),
    CONSTRAINT fk_movies_info
    FOREIGN KEY (movie_info_id)
    REFERENCES movies_additional_info(id)
);

CREATE TABLE movies_actors(
    movie_id INT,
    actor_id INT,
    CONSTRAINT fk_movies_ids
    FOREIGN KEY (movie_id)
    REFERENCES movies(id),
    CONSTRAINT fk_actors_ids
    FOREIGN KEY (actor_id)
    REFERENCES actors(id)
);

CREATE TABLE genres_movies(
    genre_id INT,
    movie_id INT,
    CONSTRAINT fk_genres_ids
    FOREIGN KEY (genre_id)
    REFERENCES genres(id),
    CONSTRAINT fk_movies_g_ids
    FOREIGN KEY (movie_id)
    REFERENCES movies(id)
);

#2
INSERT INTO actors(first_name,last_name,birthdate,height,awards,country_id)
SELECT 
    REVERSE(first_name),
    REVERSE(last_name),
    DATE(birthdate - 2),
    height + 10,
    country_id,
    3
FROM
    actors
WHERE
    id <= 10;
    
#3
UPDATE `movies_additional_info` 
SET 
    runtime = runtime - 10
WHERE
    id BETWEEN 15 AND 25;

#4
DELETE FROM countries where id IN (SELECT id FROM (SELECT 
    c.id
FROM
    countries AS c
        LEFT JOIN
    movies AS m ON m.country_id = c.id
GROUP BY c.id
HAVING COUNT(m.id) = 0) as send_help);

DELETE c FROM countries AS c
        LEFT JOIN
    movies AS m ON m.country_id = c.id
WHERE m.id is null;

#5
SELECT 
    *
FROM
    countries
ORDER BY currency DESC , id;

#6
SELECT 
    m.id, m.title, mi.runtime, mi.budget, mi.release_date
FROM
    movies_additional_info AS mi
        JOIN
    movies AS m ON m.movie_info_id = mi.id
WHERE
    YEAR(mi.release_date) BETWEEN '1996' AND '1999'
ORDER BY mi.runtime , m.id
LIMIT 20;

#7
SELECT 
    CONCAT(a.first_name, ' ', a.last_name) AS full_name,
    CONCAT(REVERSE(a.last_name),
            LENGTH(a.last_name),
            '@cast.com') AS email,
    2022 - YEAR(a.birthdate) AS age,
    a.height
FROM
    actors AS a
        LEFT JOIN
    movies_actors AS ma ON ma.actor_id = a.id
        LEFT JOIN
    movies AS m ON ma.movie_id = m.id
WHERE
    m.id IS NULL
ORDER BY height;

#8
SELECT 
    c.name, COUNT(m.id) AS movies_count
FROM
    countries AS c
        JOIN
    movies AS m ON m.country_id = c.id
GROUP BY c.id HAVING count(m.id) >= 7
ORDER BY c.name DESC;

#9
SELECT 
    m.title,
    CASE
        WHEN mi.rating <= 4 THEN 'poor'
        WHEN mi.rating BETWEEN 4 AND 7 THEN 'good'
        ELSE 'excellent'
    END AS rating,
    IF(mi.has_subtitles = 1, 'english', '-') AS subtitles,
    mi.budget
FROM
    movies_additional_info AS mi
        JOIN
    movies AS m ON m.movie_info_id = mi.id
ORDER BY mi.budget DESC;

#10
DELIMITER $$
CREATE FUNCTION udf_actor_history_movies_count(full_name VARCHAR(50))
RETURNS INT
DETERMINISTIC
BEGIN
	RETURN (SELECT 
		count(*)
	FROM
		actors AS a
			JOIN
		movies_actors AS ma ON ma.actor_id = a.id
			JOIN
		movies AS m ON m.id = ma.movie_id
			JOIN
		genres_movies AS ga ON ga.movie_id = m.id
			JOIN
		genres AS g ON g.id = ga.genre_id
	WHERE g.name = 'History' and concat(a.first_name,' ',a.last_name) = full_name);
END$$

#11
CREATE PROCEDURE udp_award_movie(movie_title VARCHAR(50))
BEGIN
	UPDATE actors AS a
			JOIN
		movies_actors AS ma ON ma.actor_id = a.id
			JOIN
		movies AS m ON ma.movie_id = m.id 
	SET 
		a.awards = a.awards + 1
	WHERE
		m.title = movie_title;
END$$

CALL udp_award_movie('Tea For Two')$$