create table customer (postal_code integer not null, customer_id bigint not null auto_increment, address varchar(255) not null, firstname varchar(255) not null, lastname varchar(255) not null, username varchar(255) not null, primary key (customer_id)) engine=InnoDB;
create table order_product (order_id bigint not null, product_id bigint not null, primary key (order_id, product_id)) engine=InnoDB;
create table order_placement (date date not null, customeridfs bigint, order_placement_id bigint not null auto_increment, primary key (order_placement_id)) engine=InnoDB;
create table product (price float(53) not null, rating integer, product_id bigint not null auto_increment, img_link varchar(255) not null, name varchar(255) not null, size varchar(255), primary key (product_id)) engine=InnoDB;
alter table customer add constraint UK_irnrrncatp2fvw52vp45j7rlw unique (username);
alter table order_product add constraint FKhnfgqyjx3i80qoymrssls3kno foreign key (product_id) references product (product_id);
alter table order_product add constraint FK7jwqfr25c3p0nnipu8m5e6xj8 foreign key (order_id) references order_placement (order_placement_id);
alter table order_placement add constraint FKmperyr0gfjprpe2fvyb0u53yy foreign key (customeridfs) references customer (customer_id);
