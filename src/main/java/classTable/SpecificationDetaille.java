package classTable;

import java.sql.Time;

public class SpecificationDetaille {
    private int idSpecificationDetaille;
    private double energieSolaire; // pourcentage du soleil
    private Time debutDuree;
    private Time finDuree;
    private int idDetailleSaison; // Clé étrangère

    // Constructeur par défaut
    public SpecificationDetaille() {}

    // Constructeur avec paramètres
    public SpecificationDetaille(int idSpecificationDetaille, double energieSolaire, Time debutDuree, Time finDuree, int idDetailleSaison) {
        this.idSpecificationDetaille = idSpecificationDetaille;
        this.energieSolaire = energieSolaire;
        this.debutDuree = debutDuree;
        this.finDuree = finDuree;
        this.idDetailleSaison = idDetailleSaison;
    }

    public int getIdSpecificationDetaille() {
        return idSpecificationDetaille;
    }

    public void setIdSpecificationDetaille(int idSpecificationDetaille) {
        this.idSpecificationDetaille = idSpecificationDetaille;
    }

    public double getEnergieSolaire() {
        return energieSolaire;
    }

    public void setEnergieSolaire(double energieSolaire) {
        this.energieSolaire = energieSolaire;
    }

    public Time getDebutDuree() {
        return debutDuree;
    }

    public void setDebutDuree(Time debutDuree) {
        this.debutDuree = debutDuree;
    }

    public Time getFinDuree() {
        return finDuree;
    }

    public void setFinDuree(Time finDuree) {
        this.finDuree = finDuree;
    }

    public int getIdDetailleSaison() {
        return idDetailleSaison;
    }

    public void setIdDetailleSaison(int idDetailleSaison) {
        this.idDetailleSaison = idDetailleSaison;
    }
}