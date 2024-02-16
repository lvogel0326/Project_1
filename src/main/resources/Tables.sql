DROP TABLE SELLER IF EXISTS;
DROP TABLE PRODUCT IF EXISTS;

CREATE TABLE SELLER (
    name varchar(255) primary key
);

CREATE TABLE PRODUCT (
    productID long primary key,  //can this take a long type?
    productName varchar(255) not null,
    sellerName varchar(255) references SELLER(name),
    productPrice double
);

INSERT INTO SELLER (name) VALUES
('walmart'),
('target'),
('family dollar');

INSERT INTO PRODUCT (productID, productName, sellerName, productPrice) VALUES
(1, 'doll','family dollar', 12.99);  //how do i account for the randomly generated number?