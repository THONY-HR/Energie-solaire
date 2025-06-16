package classView;

import java.sql.Date;

public class MinEnergieSolaireParSaison {
    private int idSaison;
    private String nomSaison;
    private Date daty; // Date associée à la saison
    private int idSpecificationDetaille;
    private double minEnergieSolaire; // Valeur minimale de l'énergie solaire

    // Constructeur par défaut
    public MinEnergieSolaireParSaison() {}

    // Constructeur avec paramètres
    public MinEnergieSolaireParSaison(int idSaison, String nomSaison, Date daty, int idSpecificationDetaille, double minEnergieSolaire) {
        this.idSaison = idSaison;
        this.nomSaison = nomSaison;
        this.daty = daty;
        this.idSpecificationDetaille = idSpecificationDetaille;
        this.minEnergieSolaire = minEnergieSolaire;
    }

    public int getIdSaison() {
        return idSaison;
    }

    public void setIdSaison(int idSaison) {
        this.idSaison = idSaison;
    }

    public String getNomSaison() {
        return nomSaison;
    }

    public void setNomSaison(String nomSaison) {
        this.nomSaison = nomSaison;
    }

    public Date getDaty() {
        return daty;
    }

    public void setDaty(Date daty) {
        this.daty = daty;
    }

    public int getIdSpecificationDetaille() {
        return idSpecificationDetaille;
    }

    public void setIdSpecificationDetaille(int idSpecificationDetaille) {
        this.idSpecificationDetaille = idSpecificationDetaille;
    }

    public double getMinEnergieSolaire() {
        return minEnergieSolaire;
    }

    public void setMinEnergieSolaire(double minEnergieSolaire) {
        this.minEnergieSolaire = minEnergieSolaire;
    }
}
