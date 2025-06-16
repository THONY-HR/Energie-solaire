package classTable;

import Fonction.Fonction;
import utilitaire.BdConnect;

public class Domicile extends Fonction{
    private int idDomicile;
    private String lot;
    private String locationMap;
    // Constructeur par défaut
    public Domicile() {}

    // Constructeur avec paramètres
    public Domicile(int idDomicile, String lot, String locationMap) {
        this.idDomicile = idDomicile;
        this.lot = lot;
        this.locationMap = locationMap;
    }
    public Domicile(String lot, String locationMap) {
        this.setIdDomicile(idDomicile);
        this.lot = lot;
        this.locationMap = locationMap;
    }

    public void setLocationMap(String locationMap) {
        this.locationMap = locationMap;
    }
    public void setLot(String lot) {
        this.lot = lot;
    }
    public void setIdDomicile(String idDomicile) {
        this.idDomicile = this.toInteger(idDomicile);
    }
    public void setIdDomicile(int idDomicile) {
        this.idDomicile = idDomicile;
    }


    public int getIdDomicile() {
        return idDomicile;
    }
    public String getLot() {
        return lot;
    }
    public String getLocationMap() {
        return locationMap;
    }

    public Besoin getBesoin() throws Exception{
        BdConnect con = new BdConnect();
        try {
            return con.getObjectFromTable("Besoin Where idDomicile="+this.getIdDomicile(),Besoin.class)[0];
        } catch (Exception e) {
            throw new Exception("Erreur getDetaille" + e.getLocalizedMessage());
        }finally{
            con.close();
        }
    }
}