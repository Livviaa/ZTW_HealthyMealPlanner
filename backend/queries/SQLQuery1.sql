INSERT INTO Uzytkownik 
VALUES 
('Maciek', 'Wasilewski', 'M', 80, 180, 'malo','maciek.wasilewski@gmail.com', '12345', 2500, 50, 75, 100),
('Mi³osz', 'Dyka', 'M', 85, 185, 'duzo', 'milosz.dyka@gmail.com', '12345', 2700, 70, 95, 155),
('Pawe³', 'Piotrowski', 'M', 60, 165, 'srednio', 'pawel.piotrowski@gmail.com', '12345', 2300, 60, 65, 120),
('£ukasz', 'Gajerski', 'M', 70, 170, 'duzo', 'lukasz.gajerski@gmail.com', '12345', 3500, 40, 60, 80)

SELECT * FROM Uzytkownik;

INSERT INTO Jadlospis 
VALUES 
(2, '2021-05-15', 800, 100, 75, 20),
(2, '2021-05-15', 700, 195, 75, 30),
(2, '2021-05-15', 500, 25, 50, 20),
(3, '2021-05-15', 1000, 50, 50, 12);

SELECT * FROM Jadlospis