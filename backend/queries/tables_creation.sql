CREATE TABLE [User]
(
	UserId INT IDENTITY(1, 1) CONSTRAINT pk_uzytkownik PRIMARY KEY,
	[Name] VARCHAR(255) CONSTRAINT nn_uzytkownik_imie NOT NULL,
	Surname VARCHAR(255) CONSTRAINT nn_uzytkownik_naziwsko NOT NULL,
	Sex VARCHAR(1) CONSTRAINT nn_uzytkownik_plec NOT NULL,
	[Weight] FLOAT CONSTRAINT nn_uzytkownik_waga NOT NULL,
	Height FLOAT CONSTRAINT nn_uzytkownik_wzrost NOT NULL,
	Activity VARCHAR(11) CONSTRAINT nn_uzytkownik_aktywnosc NOT NULL,
	Email VARCHAR(255) CONSTRAINT nn_uzytkownik_email NOT NULL,
	[Password] VARCHAR(255) CONSTRAINT nn_uzytkownik_haslo NOT NULL,
	RecommendedDailyKcal FLOAT CONSTRAINT nn_uzytkownik_kcal NOT NULL,
	RecommendedDailyProtein FLOAT CONSTRAINT nn_uzytkownik_bialko NOT NULL,
	RecommendedDailyFats FLOAT CONSTRAINT nn_uzytkownik_tluszcze NOT NULL,
	RecommendedDailyCarbohydrates FLOAT CONSTRAINT nn_uzytkownik_weglowodany NOT NULL,
	CONSTRAINT ck_uzytkownik_plec CHECK (Sex IN ('M', 'K')),
	CONSTRAINT ck_uzytkownik_aktywnosc CHECK (Activity IN ('brak', 'malo', 'srednio', 'duzo', 'bardzo duzo')),
);

CREATE TABLE DailyMenu
(
	DailyMenuId INT IDENTITY(1, 1) CONSTRAINT pk_jadlospis PRIMARY KEY,
	UserId INT CONSTRAINT nn_jadlospis_udztkownik_id NOT NULL,
	[Date] DATE CONSTRAINT nn_jadlospis_data NOT NULL,
	SumKcal FLOAT CONSTRAINT nn_jadlospis_kcal NOT NULL,
	SumProtein FLOAT CONSTRAINT nn_jadlospis_bialko NOT NULL,
	SumFats FLOAT CONSTRAINT nn_jadlospis_tluszcze NOT NULL,
	SumCarbohydrates FLOAT CONSTRAINT nn_jadlospis_weglowodany NOT NULL,
	CONSTRAINT fk_jadlospis_uzytkownik FOREIGN KEY(UserId) REFERENCES [User](UserId)
);


CREATE TABLE Meal
(
	MealId INT IDENTITY(1, 1) CONSTRAINT pk_posilek PRIMARY KEY,
	DailyMenuId INT CONSTRAINT nn_posilek_jadlospis_id NOT NULL,
	SumKcal FLOAT CONSTRAINT nn_posilek_kcal NOT NULL,
	SumProtein FLOAT CONSTRAINT nn_posilek_bialko NOT NULL,
	SumFats FLOAT CONSTRAINT nn_posilek_tluszcze NOT NULL,
	SumCarbohydrates FLOAT CONSTRAINT nn_posilek_weglowodany NOT NULL,
	CONSTRAINT fk_posilek_jadlospis FOREIGN KEY(DailyMenuId) REFERENCES DailyMenu(DailyMenuId)
);


CREATE TABLE Recipe
(
	RecipeId INT IDENTITY(1, 1) CONSTRAINT pk_przepis PRIMARY KEY,
	[Name] VARCHAR(255) CONSTRAINT nn_przepis_nazwa_potrawy NOT NULL,
	Instruction TEXT CONSTRAINT nn_przepis_tresc NOT NULL,
	[Image] VARCHAR(255) CONSTRAINT nn_przepis_zdjecie NOT NULL,
	SumKcal FLOAT CONSTRAINT nn_przepis_kcal NOT NULL,
	SumProtein FLOAT CONSTRAINT nn_przepis_bialko NOT NULL,
	SumFats FLOAT CONSTRAINT nn_przepis_tluszcze NOT NULL,
	SumCarbohydrates FLOAT CONSTRAINT nn_przepis_weglowodany NOT NULL
);


CREATE TABLE Ingredient
(
	IngredientId INT IDENTITY(1, 1) CONSTRAINT pk_produkt PRIMARY KEY,
	[Name] VARCHAR(255) CONSTRAINT nn_produkt_nazwa_produktu NOT NULL,
	MeasureUnit VARCHAR(15) CONSTRAINT nn_produkt_jednostka_miary NOT NULL,
	Units INT CONSTRAINT nn_produkt_liczba_jednostek NOT NULL,
	KcalPer100 FLOAT CONSTRAINT nn_produkt_kcal NOT NULL,
	ProteinPer100 FLOAT CONSTRAINT nn_produkt_bialko NOT NULL,
	FatsPer100 FLOAT CONSTRAINT nn_produkt_tluszcze NOT NULL,
	CarbohydratesPer100 FLOAT CONSTRAINT nn_produkt_weglowodany NOT NULL,
	CONSTRAINT ck_produkt_jednostka_miary CHECK (MeasureUnit IN ('mg', 'g', 'dag', 'kg', 'ml', 'l', 
		'szklanka (200g)', 'lyzeczka (5g)', 'lyzka (15g)', 'garsc (50g)' )),
);


CREATE TABLE RecipesAndIngredients
(
	RecipeId INT CONSTRAINT nn_przepis_produkt_przepis_id NOT NULL,
	IngredientId INT CONSTRAINT nn_przepis_produkt_produkt_id NOT NULL,
	CONSTRAINT fk_przepis_produkt_przepis FOREIGN KEY(RecipeId) REFERENCES Recipe(RecipeId),
	CONSTRAINT fk_przepis_produkt_produkt FOREIGN KEY(IngredientId) REFERENCES Ingredient(IngredientId)
);


CREATE TABLE MealsAndRecipes
(
	MealsAndRecipesId INT IDENTITY(1, 1) CONSTRAINT pk_posilek_przepis PRIMARY KEY,
	MealId INT CONSTRAINT nn_posilek_przepis_posilek_id NOT NULL,
	RecipeId INT CONSTRAINT nn_posilek_przepis_przepis_id NOT NULL,
	CONSTRAINT fk_posilek_przepis_posilek FOREIGN KEY(MealId) REFERENCES Meal(MealId),
	CONSTRAINT fk_posilek_przepis_przepis FOREIGN KEY(RecipeId) REFERENCES Recipe(RecipeId)
);