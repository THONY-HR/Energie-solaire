package classTable;

import java.sql.Time;
import Fonction.Fonction;

public class DetailleBesoin extends Fonction{
    private int idDetailleBesoin;
    private int idBesoin;
    private String nomBesoin;
    private int puissanceWatt;
    private Time debutDuree;
    private Time finDuree;

    // Constructeur par défaut
    public DetailleBesoin() {}

    // Constructeur avec paramètres
    public DetailleBesoin(int idDetailleBesoin, int idBesoin, String nomBesoin, int puissanceWatt, Time debutDuree, Time finDuree) {
        this.idDetailleBesoin = idDetailleBesoin;
        this.idBesoin = idBesoin;
        this.nomBesoin = nomBesoin;
        this.puissanceWatt = puissanceWatt;
        this.debutDuree = debutDuree;
        this.finDuree = finDuree;
    }
    public DetailleBesoin(String idBesoin, String nomBesoin, String puissanceWatt, String debutDuree, String finDuree) throws Exception{
            this.setIdBesoin(idBesoin);
            this.setNomBesoin(nomBesoin);
            this.setPuissanceWatt(puissanceWatt); 
            this.setDebutDuree(debutDuree);
            this.setFinDuree(finDuree);         
    }

    public void setIdDetailleBesoin(int idDetailleBesoin) {
        this.idDetailleBesoin = idDetailleBesoin;
    }
    public void setIdBesoin(int idBesoin) {
        this.idBesoin = idBesoin;
    }
    public void setNomBesoin(String nomBesoin) {
        this.nomBesoin = nomBesoin;
    }
    public void setPuissanceWatt(int puissanceWatt) {
        this.puissanceWatt = puissanceWatt;
    }
    public void setDebutDuree(Time debutDuree) {
        this.debutDuree = debutDuree;
    }
    public void setFinDuree(Time finDuree) {
        this.finDuree = finDuree;
    }

    public void setIdDetailleBesoin(String idDetailleBesoin) {
        this.idDetailleBesoin = this.toInteger(idDetailleBesoin);
    }
    public void setIdBesoin(String idBesoin) {
        this.idBesoin = this.toInteger(idBesoin);
    }
    public void setPuissanceWatt(String puissanceWatt) {
        this.puissanceWatt = this.toInteger(puissanceWatt);
    }
    public void setDebutDuree(String debutDuree) throws Exception{
        try {
            this.debutDuree = this.toTime(debutDuree);
            System.err.println(this.getLongueurDuree());
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
    public void setFinDuree(String finDuree) {
        this.finDuree = this.toTime(finDuree);
    }

    public int getIdBesoin() {
        return idBesoin;
    }
    public int getIdDetailleBesoin() {
        return idDetailleBesoin;
    }
    public String getNomBesoin() {
        return nomBesoin;
    }
    public int getPuissanceWatt() {
        return puissanceWatt;
    }
    public Time getDebutDuree() {
        return debutDuree;
    }
    public Time getFinDuree() {
        return finDuree;
    }
    public String getDebuDureeS(){
        return this.toStringTime(this.getDebutDuree());
    }
}
