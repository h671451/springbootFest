CREATE TABLE IF NOT EXISTS deltager (
    mobil VARCHAR(8) PRIMARY KEY,
    fornavn VARCHAR(40),
    etternavn VARCHAR(40),
    hash VARCHAR(64) NOT NULL,
    salt VARCHAR(32) NOT NULL,
    kjonn VARCHAR(32) CHECK (kjonn='MALE' OR kjonn='FEMALE')
);
