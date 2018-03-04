Create Table UserInfo(
  user_id INT,
  email varchar(255) NOT NULL,
  password varchar(255) NOT NULL,
  country_code varchar(255) NOT NULL,
  dat_of_birth varchar(255) NOT NULL,
  update_time DATE NOT NULL,
  create_time DATE NOT NULL,
  PRIMARY KEY (user_id)
);