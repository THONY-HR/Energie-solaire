package classTable;

import java.sql.Date;

public class Saison {
    private int idSaison;
    private String nomSaison;
    private Date debutSaison;
    private Date finSaison;

    // Constructeur par défaut
    public Saison() {}

    // Constructeur avec paramètres
    public Saison(int idSaison, String nomSaison, Date debutSaison, Date finSaison) {
        this.idSaison = idSaison;
        this.nomSaison = nomSaison;
        this.debutSaison = debutSaison;
        this.finSaison = finSaison;
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

    public Date getDebutSaison() {
        return debutSaison;
    }

    public void setDebutSaison(Date debutSaison) {
        this.debutSaison = debutSaison;
    }

    public Date getFinSaison() {
        return finSaison;
    }

    public void setFinSaison(Date finSaison) {
        this.finSaison = finSaison;
    }
}
