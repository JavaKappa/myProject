CREATE TABLE resume(
    uuid CHAR(36) PRIMARY KEY NOT NULL,
    fullName TEXT not null,
    location TEXT NOT NULL
);

ALTER TABLE resume ADD COLUMN homePage TEXT;