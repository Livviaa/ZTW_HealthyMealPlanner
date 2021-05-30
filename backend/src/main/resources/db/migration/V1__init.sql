CREATE TABLE users
(
	user_id INT IDENTITY(1, 1) CONSTRAINT pk_user PRIMARY KEY,
	[name] VARCHAR(255),
	surname VARCHAR(255),
	sex VARCHAR(1),
	[weight] FLOAT,
	height FLOAT,
	activity VARCHAR(11),
	email VARCHAR(255) CONSTRAINT nn_user_email NOT NULL,
    birth_date DATE,
	recommended_daily_kcal FLOAT,
	recommended_daily_protein FLOAT,
	recommended_daily_fats FLOAT,
	recommended_daily_carbohydrates FLOAT,
	CONSTRAINT ck_user_sex CHECK (sex IN ('M', 'K')),
	CONSTRAINT ck_user_activity CHECK (activity IN ('brak', 'malo', 'srednio', 'duzo')),
);


CREATE TABLE daily_menu
(
	daily_menu_id INT IDENTITY(1, 1) CONSTRAINT pk_menu PRIMARY KEY,
	user_id INT CONSTRAINT nn_menu_users_id NOT NULL,
	[date] DATE CONSTRAINT nn_menu_data NOT NULL,
	sum_kcal FLOAT,
	sum_protein FLOAT,
	sum_fats FLOAT,
	sum_carbohydrates FLOAT,
	CONSTRAINT fk_menu_users FOREIGN KEY(user_id) REFERENCES users(user_id)
);


CREATE TABLE meal
(
	meal_id INT IDENTITY(1, 1) CONSTRAINT pk_meal PRIMARY KEY,
	daily_menu_id INT CONSTRAINT nn_meal_menu_id NOT NULL,
	sum_kcal FLOAT,
	sum_protein FLOAT,
	sum_fats FLOAT,
	sum_carbohydrates FLOAT,
	CONSTRAINT fk_meal_menu FOREIGN KEY(daily_menu_id) REFERENCES daily_menu(daily_menu_id)
);


CREATE TABLE recipe
(
	recipe_id INT IDENTITY(1, 1) CONSTRAINT pk_recipe PRIMARY KEY,
	[name] VARCHAR(255),
	instruction TEXT,
	[image] VARCHAR(255),
	sum_kcal FLOAT,
	sum_protein FLOAT,
	sum_fats FLOAT,
	sum_carbohydrates FLOAT
);


CREATE TABLE ingredient
(
	ingredient_id INT IDENTITY(1, 1) CONSTRAINT pk_ingredient PRIMARY KEY,
	[name] VARCHAR(255),
	measure_unit VARCHAR(15),
	units INT,
	kcal_per100g FLOAT,
	protein_per100g FLOAT,
	fats_per100g FLOAT,
	carbohydrates_per100g FLOAT,
	CONSTRAINT ck_ingredient_measure_unit CHECK (measure_unit IN ('mg', 'g', 'dag', 'kg', 'ml', 'l',
		'szklanka (200g)', 'lyzeczka (5g)', 'lyzka (15g)', 'garsc (50g)' )),
);

