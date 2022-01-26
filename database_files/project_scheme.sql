-- clearing the schema

DECLARE
    v_count  INT;
    v_name VARCHAR2(20);
    TYPE namesarray IS VARRAY(15) OF VARCHAR2(20);
    names    namesarray;
BEGIN
    names := namesarray('users', 'categories', 'transactions', 'offers', 'photos',
                       'comments', 'cities', 'voivodships', 'countries',
                       'offers_history', 'comments_history');

    FOR i IN 1..names.count LOOP
        v_name := names(i);

        SELECT COUNT(*) INTO v_count FROM user_tables WHERE table_name = upper(v_name);
        IF v_count = 1 THEN
            DBMS_OUTPUT.PUT_LINE('Dropping table: ' || v_name);
            EXECUTE IMMEDIATE 'DROP TABLE '|| v_name || ' CASCADE CONSTRAINTS';
        END IF;
    END LOOP;
END;

-- creating tables

-- table for photos
CREATE TABLE photos (
    photo_id NUMBER (4) GENERATED BY DEFAULT ON NULL AS IDENTITY START WITH 1 CONSTRAINT photo_pk PRIMARY KEY,
    link varchar(45)
);

-- table for users
CREATE TABLE users (
    user_id NUMBER (4) GENERATED BY DEFAULT ON NULL AS IDENTITY START WITH 1 CONSTRAINT user_pk PRIMARY KEY,
    login varchar2 (45) UNIQUE NOT NULL CONSTRAINT passes_pk PRIMARY KEY,
    password varchar2 (40) NOT NULL,
    name varchar(45),
    surname varchar(45),
    birthdate date,
    accountcreationdate date,
    profile_photo NUMBER REFERENCES photos (photo_id)
);

-- table for application admins
CREATE TABLE admins (
    admin_id NUMBER (4) GENERATED BY DEFAULT ON NULL AS IDENTITY START WITH 1 CONSTRAINT admin_pk PRIMARY KEY,
    name varchar(45),
    surname varchar(45),
    phone_number NUMBER(12),
    email varchar(45),
    date_employed date,
    passes_id NUMBER REFERENCES users (passes_id)
);

-- table for countries
CREATE TABLE countries (
    country_id NUMBER (4) CONSTRAINT country_pk PRIMARY KEY,
    name varchar2 (45)
)

-- table for voivodeships
CREATE TABLE voivodeships
(
    voivodeship_id NUMBER GENERATED BY DEFAULT AS IDENTITY START WITH 2 INCREMENT BY 2 PRIMARY KEY,
    name VARCHAR(30),
    country_id NUMBER REFERENCES countries (country_id)
);

-- table for cities
CREATE TABLE cities 
(
    city_id NUMBER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    name VARCHAR(30),
    voivodship_id NUMBER REFERENCES voivodships(voivodship_id)
);

-- table for categories
CREATE TABLE categories (
    category_id int GENERATED BY DEFAULT AS IDENTITY not null CONSTRAINT category_pk PRIMARY KEY,
    name varchar(30) not null unique
);

-- table for offers
CREATE TABLE offers (
    offer_id int GENERATED BY DEFAULT AS IDENTITY not null CONSTRAINT offer_pk PRIMARY KEY,
    name varchar(30) not null,
    description varchar(3000),
    category varchar(30) REFERENCES categories(name),
    for_exchange number(1) not null check(for_exchange in (0,1)),
    for_sale number(1) not null check(for_exchange in (0,1)),
    price number(8,2),
    seller varchar2(45) REFERENCES users(login),
    offer_status number,
    photo number REFERENCES photos(photo_id),
    localisation varchar2(60) REFERENCES cities(name)
);

-- table for transactions
CREATE TABLE transactions (
    transaction_id int GENERATED BY DEFAULT AS IDENTITY not null CONSTRAINT transaction_pk PRIMARY KEY,
    offer_id NUMBER REFERENCES offers(offer_id),
    buyer varchar2(45) REFERENCES users(login),
    buyers_offer varchar2(1000),
    status number check(status in (-1,0,1))
);

-- table for comments
CREATE TABLE comments (
    comment_id NUMBER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    offer_id NUMBER REFERENCES offers(offer_id),
    user_login varchar2(45) REFERENCES users(login),
    comment_text varchar2(1000)
);

-- table for offer_history
CREATE TABLE offers_history (
    offer_change_id int GENERATED BY DEFAULT AS IDENTITY not null CONSTRAINT offer_pk PRIMARY KEY,
    offer_id NUMBER REFERENCES offers(offer_id),
    name varchar(30) not null,
    description varchar(3000),
    category varchar(30) REFERENCES categories(name),
    for_exchange number(1) not null check(for_exchange in (0,1)),
    for_sale number(1) not null check(for_exchange in (0,1)),
    price number(8,2),
    seller varchar2(45) REFERENCES users(login),
    offer_status number,
    photo number REFERENCES photos(photo_id),
    localisation varchar2(60) REFERENCES cities(name),
    date_changed date not null
);

-- table for coment history
CREATE TABLE comments_history (
    comment_change_id NUMBER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    comment_id NUMBER REFERENCES comments(comment_id),
    offer_id NUMBER REFERENCES offers(offer_id),
    user_login varchar2(45) REFERENCES users(login),
    comment_text varchar2(1000),
    date_changed date not null
);




















