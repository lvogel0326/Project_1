DROP TABLE IF EXISTS PRODUCT;
DROP TABLE IF EXISTS SELLER;

CREATE TABLE SELLER (
    name varchar(255) primary key
);

CREATE TABLE PRODUCT (
    productID long primary key,
    productName varchar(255) not null,
    sellerName varchar(255) references SELLER(name),
    productPrice double
);

--INSERT INTO SELLER (name) VALUES
--('walmart'),
--('target'),
--('family dollar');

--INSERT INTO PRODUCT (productID, productName, sellerName, productPrice) VALUES
--(345678912,'candy','family dollar',1.99),
--(234567891,'bunny','target',5.99),
--(123456789,'doll','walmart',12.99);
