create table webshop.customer (postal_code integer not null, customer_id bigint not null auto_increment, address varchar(255) not null, firstname varchar(255) not null, lastname varchar(255) not null, username varchar(255) not null, primary key (customer_id)) engine=InnoDB;
create table webshop.order_product (order_id bigint not null, product_id bigint not null, primary key (order_id, product_id)) engine=InnoDB;
create table webshop.order_placement (date date not null, customeridfs bigint, order_placement_id bigint not null auto_increment, primary key (order_placement_id)) engine=InnoDB;
create table webshop.product (price float(53) not null, rating integer, product_id bigint not null auto_increment, img_link varchar(255) not null, name varchar(255) not null, size varchar(255), primary key (product_id)) engine=InnoDB;
alter table webshop.customer add constraint UK_irnrrncatp2fvw52vp45j7rlw unique (username);
alter table webshop.order_product add constraint FKhnfgqyjx3i80qoymrssls3kno foreign key (product_id) references webshop.product (product_id);
alter table webshop.order_product add constraint FK7jwqfr25c3p0nnipu8m5e6xj8 foreign key (order_id) references webshop.order_placement (order_placement_id);
alter table webshop.order_placement add constraint FKmperyr0gfjprpe2fvyb0u53yy foreign key (customeridfs) references webshop.customer (customer_id);

INSERT INTO product(img_link, name, price, rating, size) values ('https://i0.wp.com/www.eastlondonlines.co.uk/ell_wp/wp-content/uploads/2019/03/robot-jacket.jpg?resize=750%2C422', 'big vintage', 22.99, 7, 'm');
INSERT INTO product(img_link, name, price, rating, size) values ('https://i0.wp.com/www.eastlondonlines.co.uk/ell_wp/wp-content/uploads/2019/03/robot-jacket.jpg?resize=750%2C422', 'small vintage', 22.99, 7, 'm');
INSERT INTO product(img_link, name, price, rating, size) values ('https://i0.wp.com/www.eastlondonlines.co.uk/ell_wp/wp-content/uploads/2019/03/robot-jacket.jpg?resize=750%2C422', 'true vintage', 22.99, 7, 'm');
INSERT INTO product(img_link, name, price, rating, size) values ('https://i0.wp.com/www.eastlondonlines.co.uk/ell_wp/wp-content/uploads/2019/03/robot-jacket.jpg?resize=750%2C422', 'what vintage', 22.99, 7, 'm');
INSERT INTO product(img_link, name, price, rating, size) values ('https://i0.wp.com/www.eastlondonlines.co.uk/ell_wp/wp-content/uploads/2019/03/robot-jacket.jpg?resize=750%2C422', 'dunno vintage', 22.99, 7, 'm');
INSERT INTO customer(postal_code, address, firstname, lastname, username) values (8898, 'sd', 'asd', 'test', 'test');
INSERT INTO customer(postal_code, address, firstname, lastname, username) values (8898, 'sd', 'asd', 'test', 'test2');