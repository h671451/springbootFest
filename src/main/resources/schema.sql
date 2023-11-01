CREATE TABLE IF NOT EXISTS deltager (
    mobil VARCHAR(8) PRIMARY KEY,
    hash VARCHAR(64) NOT NULL,
    salt VARCHAR(32) NOT NULL,
    fornavn VARCHAR(40),
    etternavn VARCHAR(40),
    kjonn CHAR CHECK (kjonn='MALE' OR kjonn='FEMALE')
);
