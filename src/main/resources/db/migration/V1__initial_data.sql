CREATE TABLE "student" (
    id serial NOT NULL,
    firstname character varying(100) NOT NULL,
    lastname character varying(100) NOT NULL,
    email character varying(100) NOT NULL,
    PRIMARY KEY (id)
);

ALTER TABLE "student" OWNER to postgres;