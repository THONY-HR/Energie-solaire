INSERT INTO Batterie (typeBatterie,prixWatt,decharge) VALUES
("Lithium",500,0.7),
("gel",200,0.4),
("tsotra",100,0.8);


update Batterie set prixWatt = 500 WHERE idBatterie = 1;
update Batterie set prixWatt = 200,decharge = 0.5 WHERE idBatterie = 2;
update Batterie set prixWatt = 100, decharge = 0.2 WHERE idBatterie = 3;
INSERT INTO Saison (nomSaison, debutSaison, finSaison)
VALUES 
('Saison chaude et humide', '2024-11-01', '2025-04-30'),
('Saison sèche et fraîche', '2025-05-01', '2025-10-31');

INSERT INTO DetailleSaison (daty) VALUES 
-- Janvier -- 
('2024-01-03'),
('2024-01-10'),
('2024-01-17'),
('2024-01-25'),

-- Février -- 
('2024-02-02'),
('2024-02-09'),
('2024-02-15'),
('2024-02-27'),

-- Mars -- 
('2024-03-01'),
('2024-03-05'),
('2024-03-12'),
('2024-03-26'),

-- Avril -- 
('2024-04-04'),
('2024-04-11'),
('2024-04-18'),
('2024-04-22'),

-- Mai -- 
('2024-05-02'),
('2024-05-09'),
('2024-05-15'),
('2024-05-28'),

-- Juin -- 
('2024-06-03'),
('2024-06-11'),
('2024-06-20'),
('2024-06-29'),

-- Juillet -- 
('2024-07-01'),
('2024-07-07'),
('2024-07-15'),
('2024-07-22'),

-- Août -- 
('2024-08-01'),
('2024-08-05'),
('2024-08-14'),
('2024-08-30'),

-- Septembre -- 
('2024-09-02'),
('2024-09-09'),
('2024-09-17'),
('2024-09-23'),

-- Octobre -- 
('2024-10-01'),
('2024-10-10'),
('2024-10-14'),
('2024-10-25'),

-- Novembre -- 
('2024-11-01'),
('2024-11-06'),
('2024-11-15'),
('2024-11-20'),

-- Décembre -- 
('2024-12-02'),
('2024-12-09'),
('2024-12-19'),
('2024-12-28');

INSERT INTO SpecificationDetaille (energieSolaire, debutDuree, finDuree, idDetailleSaison) VALUES 
-- Détails pour la saison chaude et humide (idDetailleSaison = 1)
(20, '05:00:00', '07:00:00', 1),
(40, '07:00:00', '09:00:00', 1),
(80, '09:00:00', '12:00:00', 1),
(90, '12:00:00', '14:00:00', 1),
(70, '14:00:00', '16:00:00', 1),
(40, '16:00:00', '17:00:00', 1),
(20, '17:00:00', '18:00:00', 1),
(0, '18:00:00', '05:00:00', 1),

-- Détails pour la saison humide (idDetailleSaison = 2)
(15, '05:00:00', '07:00:00', 2),
(30, '07:00:00', '09:00:00', 2),
(70, '09:00:00', '12:00:00', 2),
(85, '12:00:00', '14:00:00', 2),
(60, '14:00:00', '16:00:00', 2),
(35, '16:00:00', '17:00:00', 2),
(15, '17:00:00', '18:00:00', 2),
(0, '18:00:00', '05:00:00', 2),

-- Détails pour la saison sèche (idDetailleSaison = 3)
(25, '05:00:00', '07:00:00', 3),
(35, '07:00:00', '09:00:00', 3),
(75, '09:00:00', '12:00:00', 3),
(90, '12:00:00', '14:00:00', 3),
(65, '14:00:00', '16:00:00', 3),
(45, '16:00:00', '17:00:00', 3),
(20, '17:00:00', '18:00:00', 3),
(0, '18:00:00', '05:00:00', 3),

-- Détails pour la saison de transition (idDetailleSaison = 4)
(20, '05:00:00', '07:00:00', 4),
(40, '07:00:00', '09:00:00', 4),
(80, '09:00:00', '12:00:00', 4),
(85, '12:00:00', '14:00:00', 4),
(60, '14:00:00', '16:00:00', 4),
(35, '16:00:00', '17:00:00', 4),
(25, '17:00:00', '18:00:00', 4),
(0, '18:00:00', '05:00:00', 4),

-- Détails pour la saison froide (idDetailleSaison = 5)
(10, '05:30:00', '07:00:00', 5),
(25, '07:00:00', '09:00:00', 5),
(50, '09:00:00', '12:00:00', 5),
(70, '12:00:00', '14:00:00', 5),
(55, '14:00:00', '16:00:00', 5),
(30, '16:00:00', '17:00:00', 5),
(10, '17:00:00', '18:00:00', 5),
(0, '18:00:00', '05:30:00', 5),

-- Détails pour une saison typique (idDetailleSaison = 6)
(20, '05:00:00', '07:00:00', 6),
(40, '07:00:00', '09:00:00', 6),
(75, '09:00:00', '12:00:00', 6),
(85, '12:00:00', '14:00:00', 6),
(65, '14:00:00', '16:00:00', 6),
(40, '16:00:00', '17:00:00', 6),
(20, '17:00:00', '18:00:00', 6),
(0, '18:00:00', '05:00:00', 6),

-- Détails pour une autre saison (idDetailleSaison = 7)
(15, '05:00:00', '07:00:00', 7),
(30, '07:00:00', '09:00:00', 7),
(70, '09:00:00', '12:00:00', 7),
(80, '12:00:00', '14:00:00', 7),
(55, '14:00:00', '16:00:00', 7),
(30, '16:00:00', '17:00:00', 7),
(10, '17:00:00', '18:00:00', 7),
(0, '18:00:00', '05:00:00', 7),

-- Détails pour une autre saison (idDetailleSaison = 8)
(20, '05:00:00', '07:00:00', 8),
(40, '07:00:00', '09:00:00', 8),
(80, '09:00:00', '12:00:00', 8),
(90, '12:00:00', '14:00:00', 8),
(70, '14:00:00', '16:00:00', 8),
(40, '16:00:00', '17:00:00', 8),
(20, '17:00:00', '18:00:00', 8),
(0, '18:00:00', '05:00:00', 8),

-- Détails pour une autre saison (idDetailleSaison = 9)
(10, '05:00:00', '07:00:00', 9),
(25, '07:00:00', '09:00:00', 9),
(65, '09:00:00', '12:00:00', 9),
(75, '12:00:00', '14:00:00', 9),
(50, '14:00:00', '16:00:00', 9),
(30, '16:00:00', '17:00:00', 9),
(15, '17:00:00', '18:00:00', 9),
(0, '18:00:00', '05:00:00', 9),

-- Détails pour une autre saison (idDetailleSaison = 10)
(20, '05:00:00', '07:00:00', 10),
(40, '07:00:00', '09:00:00', 10),
(80, '09:00:00', '12:00:00', 10),
(90, '12:00:00', '14:00:00', 10),
(60, '14:00:00', '16:00:00', 10),
(35, '16:00:00', '17:00:00', 10),
(25, '17:00:00', '18:00:00', 10),
(0, '18:00:00', '05:00:00', 10),

-- Détails pour une autre saison (idDetailleSaison = 11)
(15, '05:00:00', '07:00:00', 11),
(30, '07:00:00', '09:00:00', 11),
(70, '09:00:00', '12:00:00', 11),
(80, '12:00:00', '14:00:00', 11),
(50, '14:00:00', '16:00:00', 11),
(30, '16:00:00', '17:00:00', 11),
(15, '17:00:00', '18:00:00', 11),
(0, '18:00:00', '05:00:00', 11),

-- Détails pour une autre saison (idDetailleSaison = 12)
(20, '05:00:00', '07:00:00', 12),
(40, '07:00:00', '09:00:00', 12),
(80, '09:00:00', '12:00:00', 12),
(90, '12:00:00', '14:00:00', 12),
(70, '14:00:00', '16:00:00', 12),
(40, '16:00:00', '17:00:00', 12),
(20, '17:00:00', '18:00:00', 12),
(0, '18:00:00', '05:00:00', 12);

 specifiDetailles[0] = new SpecificationDetaille(1, 20, Time.valueOf("06:00:00"), Time.valueOf("07:00:00"), 101);
            specifiDetailles[1] = new SpecificationDetaille(2, 20, Time.valueOf("07:00:00"), Time.valueOf("08:00:00"), 102);
            specifiDetailles[2] = new SpecificationDetaille(3, 20, Time.valueOf("08:00:00"), Time.valueOf("09:00:00"), 103);
            specifiDetailles[3] = new SpecificationDetaille(4, 20, Time.valueOf("09:00:00"), Time.valueOf("10:00:00"), 101);
            specifiDetailles[4] = new SpecificationDetaille(1, 60, Time.valueOf("10:00:00"), Time.valueOf("11:00:00"), 101);
            specifiDetailles[5] = new SpecificationDetaille(2, 60, Time.valueOf("11:00:00"), Time.valueOf("12:00:00"), 102);
            specifiDetailles[6] = new SpecificationDetaille(3, 60, Time.valueOf("12:00:00"), Time.valueOf("13:00:00"), 103);
            specifiDetailles[7] = new SpecificationDetaille(4, 40, Time.valueOf("13:00:00"), Time.valueOf("14:00:00"), 101);
            specifiDetailles[8] = new SpecificationDetaille(1, 40, Time.valueOf("14:00:00"), Time.valueOf("15:00:00"), 101);
            specifiDetailles[9] = new SpecificationDetaille(2, 40, Time.valueOf("15:00:00"), Time.valueOf("16:00:00"), 102);
            specifiDetailles[10] = new SpecificationDetaille(3, 10, Time.valueOf("16:00:00"), Time.valueOf("17:00:00"), 103);
            specifiDetailles[11] = new SpecificationDetaille(4, 10, Time.valueOf("17:00:00"), Time.valueOf("18:00:00"), 101);
    

            /*specifiDetailles[0] = new SpecificationDetaille(1, 15, Time.valueOf("06:00:00"), Time.valueOf("07:00:00"), 101);
            specifiDetailles[1] = new SpecificationDetaille(2, 25, Time.valueOf("07:00:00"), Time.valueOf("08:00:00"), 102);
            specifiDetailles[2] = new SpecificationDetaille(3, 70, Time.valueOf("08:00:00"), Time.valueOf("16:00:00"), 103);
            specifiDetailles[3] = new SpecificationDetaille(4, 20, Time.valueOf("16:00:00"), Time.valueOf("18:00:00"), 101);
            */
            /*// specifiDetailles[0] = new SpecificationDetaille(1, 15, Time.valueOf("06:00:00"), Time.valueOf("07:00:00"), 101);
            // specifiDetailles[1] = new SpecificationDetaille(2, 25, Time.valueOf("07:00:00"), Time.valueOf("08:00:00"), 102);
            // specifiDetailles[2] = new SpecificationDetaille(3, 70, Time.valueOf("08:00:00"), Time.valueOf("09:00:00"), 103);
            // specifiDetailles[3] = new SpecificationDetaille(4, 70, Time.valueOf("09:00:00"), Time.valueOf("10:00:00"), 103);
            // specifiDetailles[4] = new SpecificationDetaille(5, 70, Time.valueOf("10:00:00"), Time.valueOf("11:00:00"), 103);
            // specifiDetailles[5] = new SpecificationDetaille(6, 70, Time.valueOf("11:00:00"), Time.valueOf("12:00:00"), 103);
            // specifiDetailles[6] = new SpecificationDetaille(7, 70, Time.valueOf("12:00:00"), Time.valueOf("13:00:00"), 103);
            // specifiDetailles[7] = new SpecificationDetaille(8, 70, Time.valueOf("13:00:00"), Time.valueOf("14:00:00"), 103);
            // specifiDetailles[8] = new SpecificationDetaille(9, 70, Time.valueOf("14:00:00"), Time.valueOf("15:00:00"), 103);
            // specifiDetailles[9] = new SpecificationDetaille(10, 70, Time.valueOf("15:00:00"), Time.valueOf("16:00:00"), 103);
            // specifiDetailles[10] = new SpecificationDetaille(11, 20, Time.valueOf("16:00:00"), Time.valueOf("17:00:00"), 101);
            // specifiDetailles[11] = new SpecificationDetaille(12, 20, Time.valueOf("17:00:00"), Time.valueOf("18:00:00"), 101);*/
