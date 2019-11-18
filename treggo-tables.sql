
CREATE TABLE USERS(
  user_id serial PRIMARY KEY,
  is_admin VARCHAR(1),
  full_name VARCHAR(50),
  username VARCHAR(50),
  password VARCHAR(250),
  created_on TIMESTAMP NOT NULL
);


CREATE TABLE Customers(
   customer_id serial PRIMARY KEY,
   customer_name VARCHAR (100) NOT NULL,
   phone VARCHAR (10) NOT NULL,
   VALIDATED vARCHAR(1),
   created_on TIMESTAMP NOT NULL
);

CREATE TABLE DISH_CATEGORY(
  category_id serial PRIMARY KEY,
  category_name VARCHAR(50) NOT NULL,
  created_on TIMESTAMP NOT NULL
);

CREATE TABLE DISH(
  dish_id serial PRIMARY KEY,
  category_id INTEGER,
  dish_name VARCHAR(50) NOT NULL,
  base_price INTEGER,
  is_available VARCHAR(1),
  img_data BYTEA,
  created_on TIMESTAMP NOT NULL,
  FOREIGN KEY (category_id) REFERENCES DISH_CATEGORY(category_id)
);


CREATE TABLE ORDERS(
  order_id serial PRIMARY KEY,
  customer_id INTEGER,
  order_date DATE NOT NULL,
  order_status VARCHAR(10),
  total_amount INTEGER,
  created_on TIMESTAMP NOT NULL,
  FOREIGN KEY (customer_id) REFERENCES Customers(customer_id)
);


CREATE TABLE SUB_ORDERS(
  sub_order_id serial PRIMARY KEY,
  customer_id INTEGER,
  order_id INTEGER,
  order_date DATE NOT NULL,
  status VARCHAR(15),
  dishes VARCHAR(1000),
  quantities VARCHAR(1000),
  created_on TIMESTAMP NOT NULL,
  FOREIGN KEY (customer_id) REFERENCES Customers(customer_id),
  FOREIGN KEY (order_id) REFERENCES ORDERS(order_id)
);


CREATE TABLE BANNER_MASTER(
  banner_id serial PRIMARY KEY,
  img_data BYTEA,
  is_available VARCHAR(1),
  start_date DATE,
  end_date DATE,
  created_on TIMESTAMP NOT NULL
);


CREATE TABLE TABLE_MASTER(
  table_id serial PRIMARY KEY,
  device_id VARCHAR(50),
  table_number VARCHAR(10),
  created_on TIMESTAMP NOT NULL
);


CREATE TABLE COUPON(
  coupon_id serial PRIMARY KEY,
  coupon_name VARCHAR(50) NOT NULL,
  percentage_discount INTEGER,
  max_discount INTEGER,
  created_on TIMESTAMP NOT NULL
);



-- INSERT ADMIN USER TO ACCESS THE ADMIN PORTAL--
-- Password: admin@123
INSERT INTO USERS VALUES (DEFAULT,'Y','RITESH KUMAR', 'admin', 'S2+ZJZ7HWQqgOJH4iZ1AYA==', current_timestamp);
