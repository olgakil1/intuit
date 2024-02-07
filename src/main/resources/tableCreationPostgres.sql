BEGIN;

-- Create BirthDeathRegistry table
CREATE TABLE BirthDeathRegistry (
                                    bdID SERIAL PRIMARY KEY,
                                    date_type VARCHAR(6) NOT NULL CHECK (date_type IN ('death', 'birth')),
                                    year INT NOT NULL,
                                    month INT NOT NULL,
                                    day INT NOT NULL,
                                    country VARCHAR(255) NOT NULL,
                                    state VARCHAR(255) NOT NULL,
                                    city VARCHAR(255) NOT NULL
);

-- Create PopulationRegistry table
CREATE TABLE PopulationRegistry (
                                    pID SERIAL PRIMARY KEY,
                                    nameFirst VARCHAR(255) NOT NULL,
                                    nameLast VARCHAR(255) NOT NULL,
                                    nameGiven VARCHAR(255) NOT NULL,
                                    birth INT NOT NULL REFERENCES BirthDeathRegistry(bdID) ON DELETE RESTRICT,
                                    death INT REFERENCES BirthDeathRegistry(bdID) ON DELETE RESTRICT
);

-- Create Player table
CREATE TABLE Player (
                        playerID SERIAL PRIMARY KEY,
                        populationRegistryID INT NOT NULL,
                        retroID VARCHAR(255) NOT NULL,
                        bbrefID VARCHAR(255) NOT NULL,
                        FOREIGN KEY (populationRegistryID) REFERENCES PopulationRegistry(pID) ON DELETE RESTRICT
);

-- Create Game table
CREATE TABLE Game (
                      gID SERIAL PRIMARY KEY,
                      date DATE NOT NULL
);

-- Create PlayerGames table (junction table to support many-to-many relationship)
CREATE TABLE PlayerGames (
                             pgID SERIAL PRIMARY KEY,
                             playerID INT NOT NULL,
                             gameID INT NOT NULL,
                             FOREIGN KEY (playerID) REFERENCES Player(playerID) ON DELETE CASCADE,
                             FOREIGN KEY (gameID) REFERENCES Game(gID) ON DELETE CASCADE,
                             UNIQUE (playerID, gameID) -- Ensure that each player can be part of each game only once
);

COMMIT;