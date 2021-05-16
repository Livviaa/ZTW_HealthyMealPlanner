INSERT INTO [dbo].[User]
VALUES 
('Maciek', 'Wasilewski', 'M', 80, 180, 'malo','maciek.wasilewski@gmail.com', '12345', 2500, 50, 75, 100),
('Mi³osz', 'Dyka', 'M', 85, 185, 'duzo', 'milosz.dyka@gmail.com', '12345', 2700, 70, 95, 155),
('Pawe³', 'Piotrowski', 'M', 60, 165, 'srednio', 'pawel.piotrowski@gmail.com', '12345', 2300, 60, 65, 120),
('£ukasz', 'Gajerski', 'M', 70, 170, 'duzo', 'lukasz.gajerski@gmail.com', '12345', 3500, 40, 60, 80)

SELECT * FROM [dbo].[User];

INSERT INTO [dbo].[DailyMenu]
VALUES 
(2, '2021-05-15', 800, 100, 75, 20),
(2, '2021-05-15', 700, 195, 75, 30),
(2, '2021-05-15', 500, 25, 50, 20),
(3, '2021-05-15', 1000, 50, 50, 12);

SELECT * FROM [dbo].[DailyMenu];

INSERT INTO [dbo].[Meal]
VALUES
(1, 100, 100, 100, 100),
(1, 200, 200, 200, 200),
(1, 300, 300, 300, 300)

SELECT * FROM [dbo].[Meal];

INSERT INTO [dbo].[Recipe]
VALUES 
('Jajecznica', 'Kupic jajka usmazyc jajka', 'jajecznica.png', 100, 100, 100, 100),
('Tost z awokado', 'Kupic tosta i awokado', 'tost_z_awokado.png', 200, 200, 200, 200),
('Lazania', 'lolm nie wiem', 'lazania.png', 300, 300, 300, 300),
('P³atki z mlekiem', 'platki do mleczka', 'plaaatki.png', 400, 400, 400, 400);

SELECT * FROM [dbo].[Recipe]

INSERT INTO [dbo].[MealsAndRecipes]
VALUES 
(1, 1),
(1, 2),
(1, 3),
(2, 4)

INSERT INTO [dbo].[Ingredient]
VALUES 
('Jajko', 'g', 50, 10, 10, 10, 10),
('Platki', 'kg', 150,  20, 20, 20, 20),
('Mleko', 'kg', 250, 30, 30, 30, 30),
('Maslo', 'g', 350, 40, 40, 40, 40)

SELECT * FROM [dbo].[Ingredient]
SELECT * FROM [dbo].[Recipe]

INSERT INTO [dbo].[RecipesAndIngredients]
VALUES
(1, 1),
(1, 4),
(4, 2),
(4, 3)