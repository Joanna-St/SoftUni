CREATE SCHEMA fsd;

USE fsd;

#1
CREATE TABLE countries(
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(45) NOT NULL
);

CREATE TABLE towns(
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(45) NOT NULL,
    country_id INT,
    CONSTRAINT fk_countries_towns 
    FOREIGN KEY (country_id)
    REFERENCES countries(id)
);

CREATE TABLE stadiums(
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(45) NOT NULL,
    capacity INT NOT NULL,
    town_id INT,
    CONSTRAINT fk_towns_stadiums 
    FOREIGN KEY (town_id)
    REFERENCES towns(id)
);

CREATE TABLE teams(
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(45) NOT NULL,
    established DATE NOT NULL,
    fan_base BIGINT NOT NULL DEFAULT 0,
    stadium_id INT,
    CONSTRAINT fk_stadiums_teams
    FOREIGN KEY (stadium_id)
    REFERENCES stadiums(id)
);

CREATE TABLE coaches(
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(10) NOT NULL,
    last_name VARCHAR(20) NOT NULL,
    salary DECIMAL(10,2) NOT NULL DEFAULT 0,
    coach_level INT NOT NULL DEFAULT 0
);

CREATE TABLE skills_data(
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    dribbling INT NOT NULL DEFAULT 0,
    pace INT NOT NULL DEFAULT 0,
    passing INT NOT NULL DEFAULT 0,
    shooting INT NOT NULL DEFAULT 0,
    speed INT NOT NULL DEFAULT 0,
    strength INT NOT NULL DEFAULT 0
);

CREATE TABLE players(
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	first_name VARCHAR(10) NOT NULL,
    last_name VARCHAR(20) NOT NULL,
    age INT NOT NULL DEFAULT 0,
    position CHAR NOT NULL,
    salary DECIMAL(10,2) NOT NULL DEFAULT 0,
    hire_date DATETIME,
    skills_data_id INT,
    team_id INT,
    CONSTRAINT fk_players_teams
    FOREIGN KEY (team_id)
    REFERENCES teams(id),
    CONSTRAINT fk_players_skills
    FOREIGN KEY (skills_data_id)
    REFERENCES skills_data(id)
);

CREATE TABLE players_coaches(
	player_id INT,
	coach_id INT,
    CONSTRAINT fk_players_ids
    FOREIGN KEY (player_id)
    REFERENCES players(id),
    CONSTRAINT fk_coaches_ids
    FOREIGN KEY (coach_id)
    REFERENCES coaches(id)
);

#2
INSERT INTO coaches (first_name,last_name,salary,coach_level)
SELECT 
    first_name, last_name, salary * 2, LENGTH(first_name)
FROM
    players
WHERE
    age >= 45;
    
#3
UPDATE coaches
SET coach_level = coach_level + 1 
WHERE first_name LIKE ('A%') AND id IN (SELECT 
											coach_id
										FROM
											players_coaches
										GROUP BY coach_id
										HAVING COUNT(player_id) >= 1);


#4
DELETE FROM players 
WHERE concat_ws(' ',first_name,last_name) IN (SELECT 
												CONCAT_WS(' ', first_name, last_name)
											FROM
												coaches);

#5
SELECT 
    first_name, age, salary
FROM
    players
ORDER BY salary DESC;

#6
SELECT 
    p.id,
    CONCAT_WS(' ', p.first_name, p.last_name) AS full_name,
    p.age,
    p.position,
    p.hire_date
FROM
    players AS p
        JOIN
    skills_data AS sd ON sd.id = p.skills_data_id
WHERE
    p.age < 23 AND p.position = 'A'
        AND p.hire_date IS NULL
        AND sd.strength > 50
ORDER BY p.salary , p.age;

#7
SELECT 
    t.name AS team_name,
    t.established,
    t.fan_base,
    COUNT(p.id) AS players_count
FROM
    teams AS t
        LEFT JOIN
    players AS p ON p.team_id = t.id
GROUP BY t.id
ORDER BY players_count DESC,t.fan_base DESC;

#8
SELECT 
    MAX(sd.speed) AS max_speed, tw.name AS town_name
FROM
    players AS p
        JOIN
    skills_data AS sd ON sd.id = p.skills_data_id
        RIGHT JOIN
    teams AS tm ON tm.id = p.team_id
        RIGHT JOIN
    stadiums AS st ON st.id = tm.stadium_id
        RIGHT JOIN
    towns AS tw ON tw.id = st.town_id
WHERE
    NOT tm.name = 'Devify'
GROUP BY tw.id
ORDER BY max_speed DESC , town_name;

#9
SELECT 
    c.name,
    COUNT(pl.id) AS total_count_of_players,
    SUM(pl.salary) AS total_sum_of_salaries
FROM
    countries AS c
        LEFT JOIN
    towns AS tw ON tw.country_id = c.id
        LEFT JOIN
    stadiums AS st ON st.town_id = tw.id
        LEFT JOIN
    teams AS tm ON tm.stadium_id = st.id
        LEFT JOIN
    players AS pl ON pl.team_id = tm.id
GROUP BY c.id
ORDER BY total_count_of_players DESC , c.name;

#10
DELIMITER $$
CREATE FUNCTION udf_stadium_players_count (stadium_name VARCHAR(30))
RETURNS INT
DETERMINISTIC
BEGIN
	RETURN (SELECT 
		count(p.id)
	FROM
		stadiums AS st
			JOIN
		teams AS t ON t.stadium_id = st.id
			JOIN
		players AS p ON p.team_id = t.id
	WHERE st.name = stadium_name);
END$$

SELECT udf_stadium_players_count('Linklinks')$$

#11
CREATE PROCEDURE usp_find_playmaker (min_dribble_points INT,team_name VARCHAR(45))
BEGIN
	SELECT 
		concat_ws(' ',p.first_name,p.last_name) as full_name,
		p.age,
		p.salary,
		sd.dribbling,
		sd.speed,
		t.name as team_name
	FROM
		players AS p
			JOIN
		skills_data AS sd ON p.skills_data_id = sd.id
			JOIN
		teams AS t ON t.id = p.team_id
	WHERE
		t.name = team_name AND sd.dribbling > min_dribble_points
			AND sd.speed > (SELECT AVG(speed) FROM skills_data)
	ORDER BY sd.speed DESC
	LIMIT 1;
	END$$
    
    CALL usp_find_playmaker (20, 'Skyble')