Clear

-- Drop tables if they exist

DROP TABLE IF EXISTS sprint_results;
DROP TABLE IF EXISTS results;
DROP TABLE IF EXISTS qualifying;
DROP TABLE IF EXISTS pit_stops;
DROP TABLE IF EXISTS lap_times;
DROP TABLE IF EXISTS driver_standings;
DROP TABLE IF EXISTS constructor_standings;
DROP TABLE IF EXISTS constructor_results;
DROP TABLE IF EXISTS races;
DROP TABLE IF EXISTS drivers;
DROP TABLE IF EXISTS constructors;
DROP TABLE IF EXISTS circuits;
DROP TABLE IF EXISTS status;
DROP TABLE IF EXISTS seasons;


CREATE TABLE circuits (circuitId INT PRIMARY KEY, circuitRef VARCHAR(255), name VARCHAR(255), location VARCHAR(255), country VARCHAR(255));
CREATE TABLE constructors (constructorId INT PRIMARY KEY, constructorRef VARCHAR(255), name VARCHAR(255), nationality VARCHAR(255));
CREATE TABLE drivers (driverId INT PRIMARY KEY, number VARCHAR(10), code VARCHAR(10), forename VARCHAR(255), surname VARCHAR(255), dob DATE, nationality VARCHAR(255));
CREATE TABLE races (raceId INT PRIMARY KEY, year INT, round INT, circuitId INT, name VARCHAR(255), date DATE, time VARCHAR(20), FOREIGN KEY (circuitId) REFERENCES circuits(circuitId) ON DELETE CASCADE);
CREATE TABLE status (statusId INT PRIMARY KEY, status VARCHAR(255));
CREATE TABLE seasons (year INT PRIMARY KEY, url VARCHAR(255));
CREATE TABLE constructor_results (constructorResultsId INT PRIMARY KEY, raceId INT, constructorId INT, points FLOAT, status VARCHAR(255), FOREIGN KEY (raceId) REFERENCES races(raceId) ON DELETE CASCADE, FOREIGN KEY (constructorId) REFERENCES constructors(constructorId) ON DELETE CASCADE);
CREATE TABLE constructor_standings (constructorStandingsId INT PRIMARY KEY, raceId INT, constructorId INT, points FLOAT, position INT, wins INT, FOREIGN KEY (raceId) REFERENCES races(raceId) ON DELETE CASCADE, FOREIGN KEY (constructorId) REFERENCES constructors(constructorId) ON DELETE CASCADE);
CREATE TABLE driver_standings (driverStandingsId INT PRIMARY KEY, raceId INT, driverId INT, points FLOAT, position INT, positionText VARCHAR(255), wins INT, FOREIGN KEY (raceId) REFERENCES races(raceId) ON DELETE CASCADE, FOREIGN KEY (driverId) REFERENCES drivers(driverId) ON DELETE CASCADE);
CREATE TABLE results (resultId INT PRIMARY KEY, raceId INT, driverId INT, constructorId INT, grid INT, position VARCHAR(10), points FLOAT, laps INT, time VARCHAR(255), statusId INT, FOREIGN KEY (raceId) REFERENCES races(raceId) ON DELETE CASCADE, FOREIGN KEY (driverId) REFERENCES drivers(driverId) ON DELETE CASCADE, FOREIGN KEY (constructorId) REFERENCES constructors(constructorId) ON DELETE CASCADE, FOREIGN KEY (statusId) REFERENCES status(statusId) ON DELETE SET NULL);
CREATE TABLE lap_times (raceId INT, driverId INT, lap INT, position INT, time VARCHAR(255), PRIMARY KEY (raceId, driverId, lap), FOREIGN KEY (raceId) REFERENCES races(raceId) ON DELETE CASCADE, FOREIGN KEY (driverId) REFERENCES drivers(driverId) ON DELETE CASCADE);
CREATE TABLE pit_stops (raceId INT, driverId INT, stop INT, lap INT, time VARCHAR(20), duration VARCHAR(20), PRIMARY KEY (raceId, driverId, stop), FOREIGN KEY (raceId) REFERENCES races(raceId) ON DELETE CASCADE, FOREIGN KEY (driverId) REFERENCES drivers(driverId) ON DELETE CASCADE);
CREATE TABLE qualifying (qualifyId INT PRIMARY KEY, raceId INT, driverId INT, constructorId INT, number INT, position INT, q1 VARCHAR(255), q2 VARCHAR(255), q3 VARCHAR(255), FOREIGN KEY (raceId) REFERENCES races(raceId) ON DELETE CASCADE, FOREIGN KEY (driverId) REFERENCES drivers(driverId) ON DELETE CASCADE, FOREIGN KEY (constructorId) REFERENCES constructors(constructorId) ON DELETE CASCADE);
CREATE TABLE sprint_results (resultId INT PRIMARY KEY, raceId INT, driverId INT, constructorId INT, number INT, grid INT, position VARCHAR(10), positionText VARCHAR(255), positionOrder INT, points INT, laps INT, time VARCHAR(255), milliseconds INT, fastestLap VARCHAR(255), fastestLapTime VARCHAR(255), statusId INT, FOREIGN KEY (raceId) REFERENCES races(raceId) ON DELETE CASCADE, FOREIGN KEY (driverId) REFERENCES drivers(driverId) ON DELETE CASCADE, FOREIGN KEY (constructorId) REFERENCES constructors(constructorId) ON DELETE CASCADE, FOREIGN KEY (statusId) REFERENCES status(statusId) ON DELETE SET NULL);
