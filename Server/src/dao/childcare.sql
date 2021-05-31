DROP SCHEMA IF EXISTS childcare_data CASCADE;
CREATE SCHEMA childcare_data;
SET SCHEMA 'childcare_data';

CREATE DOMAIN paymentDomain DECIMAL CHECK ( value < 0 AND value > 500);



CREATE TABLE account
(
    username VARCHAR(100) UNIQUE NOT NULL,
    email    VARCHAR(100) PRIMARY KEY,
    password VARCHAR(16),
    first_name            VARCHAR(500) NOT NULL,
    last_name             VARCHAR(500) NOT NULL
);

CREATE TABLE parent
(
    email          VARCHAR(100),
    has_pets       boolean,
    PRIMARY KEY (email),
    FOREIGN KEY (email) REFERENCES account (email) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE babysitter
(
    email                 VARCHAR(100),
    birthday              DATE,
    payment               paymentDomain,
    language              VARCHAR(200),
    years_of_experience   DECIMAL,
    first_aid_certificate boolean,
    PRIMARY KEY (email),
    FOREIGN KEY (email) REFERENCES account (email) ON DELETE CASCADE ON UPDATE CASCADE
);


CREATE TABLE booking
(
    bookingID  VARCHAR(5),
    parent_email     VARCHAR(100),
    babysitter_email VARCHAR(100),
   dateTime timeStamp ,
   status VARCHAR (50),
   description VARCHAR(1000),
   dateTimeOfBooking timeStamp,
    PRIMARY KEY (bookingID),
    FOREIGN KEY ( parent_email ) REFERENCES parent (email) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (babysitter_email) REFERENCES babysitter (email) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE kid
( parent_email           VARCHAR(100),
    ID           INTEGER,
    gender           boolean,
    birthday         DATE,
    health_condition VARCHAR(600),
    PRIMARY KEY (ID, parent_email),
    FOREIGN KEY (parent_email) REFERENCES parent (email) ON DELETE CASCADE ON UPDATE CASCADE
);

INSERT INTO account(username, email, password, first_name, last_name)
Values
('gloria','gloria@gmail.com','password','Gloria','Melu');
;

