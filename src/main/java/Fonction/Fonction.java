package Fonction;

import java.sql.Time;
import java.util.Date;
import java.text.SimpleDateFormat;
import utilitaire.BdConnect;
import java.util.Calendar;

public class Fonction{
    protected BdConnect con;
    protected String nomTable;
    protected double duree;
    protected String key;
    public Boolean inserer() throws Exception {
        con = new BdConnect();
        try {
            con.insertObjectIntoTable(this.getNomTable(), this);
            System.out.println("Insertion reussie dans" + nomTable);
            return true;
        } catch (Exception e) {
            throw new Exception("Erreur: Insertion: " + nomTable + " " + e.getMessage());
        }finally{
            con.close();
        }
    }
    public Boolean update(String primaryKey) throws Exception{
        con = new BdConnect();
        try {
            con.updateObjectInTable(this.getNomTable(),this,primaryKey);
            System.out.println("Update reussie dans" + nomTable);
            return true;
        } catch (Exception e) {
            throw new Exception("Erreur Update: "+ nomTable + " " +e.getMessage());
        }finally{
            con.close();
        }
    }
    public int getLastInsert() throws Exception{
        con = new BdConnect();
        try {
            return con.getLastInsertIdFromTable(getKey(),this.getNomTable());
        } catch (Exception e) {
            throw new Exception("Erreur last Insert: "+ nomTable + " " +e.getMessage());
        }
    }
    public void setKey(String keys){
        this.key = keys;
    }

    public String getKey(){
        return this.key;
    }
    public String getNomTable(){
        return this.nomTable;
    }
    public double getLongueurDuree(){
        return this.duree;
    }
    public void setLongueurDuree(double duree){
        this.duree = duree;
    }
    public void setNomTable(String nomTable){
        this.nomTable = nomTable;
    }
    public int toInteger(String value) {
        return Integer.parseInt(value);
    }
    public double toDouble(String value) {
        return Double.parseDouble(value);
    }
    public Date toDate(String dateStr) {
        try {
            return new java.text.SimpleDateFormat("yyyy-MM-dd").parse(dateStr);
        } catch (Exception e) {
            return null;
        }
    }
    public Time toTime(String time) throws IllegalArgumentException{
        if (time != null && time.matches("\\d{2}:\\d{2}")) {
            String timeWithSecond = time + ":00";
            return Time.valueOf(timeWithSecond);
        }else{
            throw new IllegalArgumentException("Les format de l'heure n'est pas valide");
        }
    }
    public String toStringTime(Time time) {
        if (time == null) {
            return "erreur";
        }
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
        return formatter.format(time);
    }
}
