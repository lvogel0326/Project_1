DROP TABLE PRODUCT IF EXISTS;
DROP TABLE SELLER IF EXISTS;

CREATE TABLE SELLER (
    name varchar(255) primary key
);

CREATE TABLE PRODUCT (
    productID long primary key,  -- tried removing this to account for auto generated productID - didn't work
    productName varchar(255) not null,
    sellerName varchar(255) references SELLER(name),
    productPrice double
);

INSERT INTO SELLER (name) VALUES
('walmart'),
('target'),
('family dollar');

--INSERT INTO PRODUCT (productID, productName, sellerName, productPrice) VALUES
--(123456789, 'doll','walmart', 12.99);  --how do i enter the productID which is randomly generated?

-- removed productID thinking this would fix my issue - it didn't
--INSERT INTO PRODUCT (productName, sellerName, productPrice) VALUES
--('doll','walmart', 12.99);  --how do i get the productID which is randomly generated?