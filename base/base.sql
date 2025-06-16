-- Création de la base de données et sélection de celle-ci
DROP DATABASE EnergieSolaire;
CREATE DATABASE EnergieSolaire;
USE EnergieSolaire;
CREATE TABLE Domicile(
    idDomicile INT PRIMARY KEY AUTO_INCREMENT,
    lot VARCHAR(50),
    locationMap VARCHAR(100)
);

CREATE TABLE Batterie(
    idBatterie INT PRIMARY KEY AUTO_INCREMENT,
    typeBatterie VARCHAR(50),
    prixWatt double,
    decharge double
);

CREATE TABLE Besoin(
    idBesoin INT PRIMARY KEY AUTO_INCREMENT,
    idDomicile INT,
    idBatterie INT,
    datyEnregistrement DATE,
    FOREIGN KEY (idBatterie) REFERENCES Batterie(idBatterie),
    FOREIGN KEY (idDomicile) REFERENCES Domicile(idDomicile)
);
update Besoin set idBatterie = 1;
CREATE TABLE DetailleBesoin(
    idDetailleBesoin INT PRIMARY KEY AUTO_INCREMENT,
    idBesoin INT,
    nomBesoin VARCHAR(50),
    puissanceWatt INT,
    debutDuree TIME,
    finDuree TIME,
    FOREIGN KEY (idBesoin) REFERENCES Besoin(idBesoin)
);

CREATE TABLE Saison(
    idSaison INT PRIMARY KEY AUTO_INCREMENT,
    nomSaison VARCHAR(100),
    debutSaison DATE,
    finSaison DATE
);

CREATE TABLE DetailleSaison(
    idDetailleSaison INT PRIMARY KEY AUTO_INCREMENT,
    daty Date
);

CREATE TABLE SpecificationDetaille(
    idSpecificationDetaille INT PRIMARY KEY AUTO_INCREMENT,
    energieSolaire double, -- pourcentage du soleil
    debutDuree TIME,
    finDuree TIME,
    idDetailleSaison INT,
    FOREIGN KEY (idDetailleSaison) REFERENCES DetailleSaison(idDetailleSaison)
);

CREATE OR REPLACE VIEW MinEnergieSolaireParSaison AS
SELECT
    s.idSaison AS idSaison,
    s.nomSaison AS nomSaison,
    ds.daty AS daty,
    sd.idSpecificationDetaille AS idSpecificationDetaille,
    MIN(sd.energieSolaire) AS MinEnergieSolaire
FROM 
    Saison s
JOIN 
    DetailleSaison ds ON s.idSaison = ds.idDetailleSaison  -- Assurez-vous d'avoir cette relation
JOIN 
    SpecificationDetaille sd ON ds.idDetailleSaison = sd.idDetailleSaison
WHERE 
    sd.energieSolaire > 0  -- Filtre pour ne prendre que les valeurs supérieures à zéro
GROUP BY 
    s.nomSaison;


SELECT * FROM MinEnergieSolaireParSaison;

