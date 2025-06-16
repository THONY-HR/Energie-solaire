package classTable;

public class Batterie {
    private int idBatterie;
    private String typeBatterie;
    private double prixWatt;
    private double decharge;
    // Constructeur par défaut
    public Batterie() {}

    // Constructeur avec paramètres
    public Batterie(int idBatterie, String typeBatterie, double prixWatt) {
        this.idBatterie = idBatterie;
        this.typeBatterie = typeBatterie;
        this.prixWatt = prixWatt;
    }

    
    public int getIdBatterie() {
        return idBatterie;
    }
    public String getTypeBatterie() {
        return typeBatterie;
    }
    public double getPrixWatt() {
        return prixWatt;
    }
    public double getDecharge(){
        return this.decharge;
    }


    public void setDecharge(double decharge){
        this.decharge = decharge;
    }
    public void setTypeBatterie(String typeBatterie) {
        this.typeBatterie = typeBatterie;
    }
    public void setIdBatterie(int idBatterie) {
        this.idBatterie = idBatterie;
    }
    public void setPrixWatt(double prixWatt) {
        this.prixWatt = prixWatt;
    }
}