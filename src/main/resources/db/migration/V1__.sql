CREATE SEQUENCE IF NOT EXISTS customerprocedures_customer_procedure_id_seq START WITH 1 INCREMENT BY 1;

CREATE SEQUENCE IF NOT EXISTS customers_customer_id_seq START WITH 1 INCREMENT BY 1;

CREATE SEQUENCE IF NOT EXISTS hotelrooms_hotel_room_id_seq START WITH 1 INCREMENT BY 1;

CREATE SEQUENCE IF NOT EXISTS procedures_procedure_id_seq START WITH 1 INCREMENT BY 1;

CREATE SEQUENCE IF NOT EXISTS roombooking_booking_id_seq START WITH 1 INCREMENT BY 1;

CREATE SEQUENCE IF NOT EXISTS rooms_room_id_seq START WITH 1 INCREMENT BY 1;

CREATE SEQUENCE IF NOT EXISTS roomusage_usage_id_seq START WITH 1 INCREMENT BY 1;

CREATE SEQUENCE IF NOT EXISTS staff_staff_id_seq START WITH 1 INCREMENT BY 1;

CREATE TABLE customerprocedures
(
    customer_procedure_id INTEGER GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    customer_id           INTEGER                                  NOT NULL,
    procedure_id          INTEGER                                  NOT NULL,
    procedure_date        date                                     NOT NULL,
    CONSTRAINT customerprocedures_pkey PRIMARY KEY (customer_procedure_id)
);

CREATE TABLE customers
(
    customer_id   INTEGER GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    first_name    VARCHAR(100)                             NOT NULL,
    last_name     VARCHAR(100)                             NOT NULL,
    date_of_birth date                                     NOT NULL,
    phone         VARCHAR(15)                              NOT NULL,
    email         VARCHAR(100),
    CONSTRAINT customers_pkey PRIMARY KEY (customer_id)
);

CREATE TABLE hotelrooms
(
    hotel_room_id   INTEGER GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    room_number     VARCHAR(20)                              NOT NULL,
    bed_count       INTEGER                                  NOT NULL,
    price_per_night numeric(10, 2)                           NOT NULL,
    CONSTRAINT hotelrooms_pkey PRIMARY KEY (hotel_room_id)
);

CREATE TABLE procedures
(
    procedure_id     INTEGER GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    procedure_name   VARCHAR(100)                             NOT NULL,
    description      TEXT,
    duration_minutes INTEGER                                  NOT NULL,
    CONSTRAINT procedures_pkey PRIMARY KEY (procedure_id)
);

CREATE TABLE roombooking
(
    booking_id    INTEGER GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    customer_id   INTEGER                                  NOT NULL,
    hotel_room_id INTEGER                                  NOT NULL,
    check_in      date                                     NOT NULL,
    check_out     date                                     NOT NULL,
    CONSTRAINT roombooking_pkey PRIMARY KEY (booking_id)
);

CREATE TABLE rooms
(
    room_id     INTEGER GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    room_number VARCHAR(20)                              NOT NULL,
    capacity    INTEGER                                  NOT NULL,
    CONSTRAINT rooms_pkey PRIMARY KEY (room_id)
);

CREATE TABLE roomusage
(
    usage_id     INTEGER GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    room_id      INTEGER                                  NOT NULL,
    procedure_id INTEGER                                  NOT NULL,
    staff_id     INTEGER                                  NOT NULL,
    start_time   TIMESTAMP WITHOUT TIME ZONE              NOT NULL,
    end_time     TIMESTAMP WITHOUT TIME ZONE              NOT NULL,
    CONSTRAINT roomusage_pkey PRIMARY KEY (usage_id)
);

CREATE TABLE staff
(
    staff_id       INTEGER GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    first_name     VARCHAR(100)                             NOT NULL,
    last_name      VARCHAR(100)                             NOT NULL,
    specialization VARCHAR(100)                             NOT NULL,
    CONSTRAINT staff_pkey PRIMARY KEY (staff_id)
);

ALTER TABLE customers
    ADD CONSTRAINT customers_phone_key UNIQUE (phone);

ALTER TABLE hotelrooms
    ADD CONSTRAINT hotelrooms_room_number_key UNIQUE (room_number);

ALTER TABLE procedures
    ADD CONSTRAINT procedures_procedure_name_key UNIQUE (procedure_name);

ALTER TABLE rooms
    ADD CONSTRAINT rooms_room_number_key UNIQUE (room_number);

ALTER TABLE customerprocedures
    ADD CONSTRAINT customerprocedures_customer_id_fkey FOREIGN KEY (customer_id) REFERENCES customers (customer_id) ON DELETE CASCADE;

ALTER TABLE customerprocedures
    ADD CONSTRAINT customerprocedures_procedure_id_fkey FOREIGN KEY (procedure_id) REFERENCES procedures (procedure_id) ON DELETE CASCADE;

ALTER TABLE roombooking
    ADD CONSTRAINT roombooking_customer_id_fkey FOREIGN KEY (customer_id) REFERENCES customers (customer_id) ON DELETE CASCADE;

ALTER TABLE roombooking
    ADD CONSTRAINT roombooking_hotel_room_id_fkey FOREIGN KEY (hotel_room_id) REFERENCES hotelrooms (hotel_room_id) ON DELETE CASCADE;

ALTER TABLE roomusage
    ADD CONSTRAINT roomusage_procedure_id_fkey FOREIGN KEY (procedure_id) REFERENCES procedures (procedure_id) ON DELETE CASCADE;

ALTER TABLE roomusage
    ADD CONSTRAINT roomusage_room_id_fkey FOREIGN KEY (room_id) REFERENCES rooms (room_id) ON DELETE CASCADE;

ALTER TABLE roomusage
    ADD CONSTRAINT roomusage_staff_id_fkey FOREIGN KEY (staff_id) REFERENCES staff (staff_id) ON DELETE CASCADE;