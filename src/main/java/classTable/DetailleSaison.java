package classTable;

import java.sql.Date;

public class DetailleSaison {
    private int idDetailleSaison;
    private Date daty;

    // Constructeur par défaut
    public DetailleSaison() {}

    // Constructeur avec paramètres
    public DetailleSaison(int idDetailleSaison, Date daty) {
        this.idDetailleSaison = idDetailleSaison;
        this.daty = daty;
    }

    public int getIdDetailleSaison() {
        return idDetailleSaison;
    }

    public void setIdDetailleSaison(int idDetailleSaison) {
        this.idDetailleSaison = idDetailleSaison;
    }

    public Date getDaty() {
        return daty;
    }

    public void setDaty(Date daty) {
        this.daty = daty;
    }
}