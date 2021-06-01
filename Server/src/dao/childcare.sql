DROP SCHEMA IF EXISTS childcare_data CASCADE;
CREATE SCHEMA childcare_data;
SET SCHEMA 'childcare_data';



CREATE TABLE account
(
    username   VARCHAR(100) UNIQUE NOT NULL,
    email      VARCHAR(100) PRIMARY KEY,
    password   VARCHAR(16),
    first_name VARCHAR(500)        NOT NULL,
    last_name  VARCHAR(500)        NOT NULL,
    isParent   BOOLEAN
);

CREATE TABLE parent
(
    email    VARCHAR(100),
    has_pets boolean,
    PRIMARY KEY (email),
    FOREIGN KEY (email) REFERENCES account (email) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE babysitter
(
    email                 VARCHAR(100),
    birthday              DATE,
    years_of_experience   DOUBLE PRECISION,
    payment               DOUBLE PRECISION,
    language              VARCHAR(200),
    first_aid_certificate boolean,
    PRIMARY KEY (email),
    FOREIGN KEY (email) REFERENCES account (email) ON DELETE CASCADE ON UPDATE CASCADE
);


CREATE TABLE booking
(

    start_time TIME,
    end_time TIME,
    parent_email      VARCHAR(100),
    babysitter_email  VARCHAR(100),
    description       VARCHAR(1000),
    status            VARCHAR(50),
    dateTimeOfBooking VARCHAR,
    booking_id INTEGER,
    PRIMARY KEY (booking_id),
    FOREIGN KEY (parent_email) REFERENCES parent (email) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (babysitter_email) REFERENCES babysitter (email) ON DELETE CASCADE ON UPDATE CASCADE
);


CREATE TABLE kid
(
    parent_email     VARCHAR(100),
    ID               INTEGER,
    gender           boolean,
    birthday         DATE,
    health_condition VARCHAR(600),
    PRIMARY KEY (ID, parent_email),
    FOREIGN KEY (parent_email) REFERENCES parent (email) ON DELETE CASCADE ON UPDATE CASCADE
);