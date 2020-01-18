CREATE TABLE resume(
    uuid CHAR(36) PRIMARY KEY NOT NULL,
    fullName TEXT NOT NULL,
    location TEXT NOT NULL
);

ALTER TABLE resume ADD COLUMN homePage TEXT;

CREATE TABLE contact(
    id SERIAL,
    resume_uuid CHAR(36) NOT NULL,
    type TEXT NOT NULL,
    value TEXT NOT NULL,
    CONSTRAINT contact_pkey PRIMARY KEY (id),
    CONSTRAINT contact_fk FOREIGN KEY (resume_uuid) REFERENCES resume(uuid)
                    ON DELETE CASCADE
                    ON UPDATE NO ACTION
                    NOT DEFERRABLE
);
CREATE UNIQUE INDEX contact_idx ON contact USING btree(resume_uuid, type)