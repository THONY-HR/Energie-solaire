package classTable;

import Fonction.Fonction;
import utilitaire.BdConnect;

import java.util.Date;

public class Besoin extends Fonction{
    private int idBesoin;
    private int idDomicile;
    private int idBatterie;
    private Date datyEnregistrement;

    // Constructeur par défaut
    public Besoin() {}

    // Constructeur avec paramètres
    public Besoin(int idBesoin, int idDomicile, int idBatterie,Date datyEnregistrement) {
        this.idBesoin = idBesoin;
        this.idDomicile = idDomicile;
        this.idBatterie = idBatterie;
        this.datyEnregistrement = datyEnregistrement;
    }
    public Besoin(String idDomicile, String idBatterie, String datyEnregistrement) {
        this.setIdDomicile(idDomicile);
        this.setIdBatterie(idBatterie);
        this.setDatyEnregistrement(datyEnregistrement);
    }
    public Besoin(String idBatterie, String datyEnregistrement) {
        this.setIdBatterie(idBatterie);
        this.setDatyEnregistrement(datyEnregistrement);
    }
    public void setIdBesoin(int idBesoin) {
        this.idBesoin = idBesoin;
    }
    public void setIdBesoin(String idBesoin) {
        this.idBesoin = this.toInteger(idBesoin);
    }
    public void setIdDomicile(int idDomicile) {
        this.idDomicile = idDomicile;
    }
    public void setIdDomicile(String idDomicile) {
        this.idDomicile = this.toInteger(idDomicile);
    }
    public void setIdBatterie(int idBatterie) {
        this.idBatterie = idBatterie;
    }
    public void setIdBatterie(String idBatterie) {
        this.idBatterie = this.toInteger(idBatterie);
    }
    public void setDatyEnregistrement(Date datyEnregistrement) {
        this.datyEnregistrement = datyEnregistrement;
    }
    public void setDatyEnregistrement(String datyEnregistrement) {
        this.datyEnregistrement = this.toDate(datyEnregistrement);
    }


    public int getIdDomicile() {
        return idDomicile;
    }
    public Date getDatyEnregistrement() {
        return datyEnregistrement;
    }
    public int getIdBatterie() {
        return idBatterie;
    }
    public int getIdBesoin() {
        return idBesoin;
    }

    public Batterie[] getBatterie() throws Exception{
        BdConnect con = new BdConnect();
        try {
            return con.getObjectFromTable("Batterie WHERE idBatterie="+this.getIdBatterie(),Batterie.class);
        } catch (Exception e) {
            throw new Exception("Erreur get Batterie:"+e.getMessage());
        }finally{
            con.close();
        }
    }
    public DetailleBesoin[] getDetailleBesoin() throws Exception{
        BdConnect con = new BdConnect();
        try {
            DetailleBesoin[] newDetailleBesoins = con.getObjectFromTable("DetailleBesoin Where idBesoin="+this.getIdBesoin(),DetailleBesoin.class);
            return newDetailleBesoins;
        } catch (Exception e) {
            throw new Exception("Erreur getDetaille" + e.getLocalizedMessage());
        }finally{
            con.close();
        }
    }
}