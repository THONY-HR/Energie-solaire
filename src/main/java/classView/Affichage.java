package classView;

import Fonction.Calculate;
import classTable.Besoin;
import classTable.DetailleBesoin;
import classTable.Domicile;
import utilitaire.BdConnect;

public class Affichage {
    private Calculate calculate;
    private Domicile[] domicile;

    public Affichage() throws Exception{
        BdConnect con = new BdConnect();
        try {
            this.domicile = con.getObjectFromTable("Domicile",Domicile.class);
        } catch (Exception e) {
            System.err.println("Erreur Affichage"+e.getMessage());
        }finally{
            con.close();
        }
    }

    public Domicile[] getDomicile(){
        return domicile;
    }
    public double getTotalPanneau(DetailleBesoin[] detailleBesoins) throws Exception{
        calculate = new Calculate();
        double totalWattPaneau = 0;
        try {
            totalWattPaneau = calculate.sommePuissanceJourPanneau(detailleBesoins);
            return this.arrondir(totalWattPaneau);
        } catch (Exception e) {
            throw new Exception("Erreur Affichage getTotalPaneau"+e.getMessage());
        }
    }
    public double getTotalBatterie(DetailleBesoin[] detailleBesoins,Besoin besoin) throws Exception{
        calculate = new Calculate();
        double totalWattBatterie = 0;
        try {
            totalWattBatterie = calculate.sommePuissanceNuitBatterie(detailleBesoins,besoin);
            return this.arrondir(totalWattBatterie);
        } catch (Exception e) {
            throw new Exception("Erreur Affichage getTotalBatterie"+e.getMessage());
        }
    }
    public double getTotalPrix(DetailleBesoin[] detailleBesoins,Besoin besoin) throws Exception{
        calculate = new Calculate();
        double totalWattBatterie = 0;
        try {
            totalWattBatterie = calculate.prixTotal(besoin,detailleBesoins);
            return this.arrondir(totalWattBatterie);
        } catch (Exception e) {
            throw new Exception("Erreur Affichage getTotalPrix"+e.getMessage());
        }
    }
    public double[] getRentabilier(DetailleBesoin[] detailleBesoins,Besoin besoin) throws Exception{
        calculate = new Calculate();

        try {
            double[] totalWattBatterie = calculate.rentabliliterBatterie(detailleBesoins,besoin);
            for (int i = 0; i < totalWattBatterie.length; i++) {
                totalWattBatterie[i] = this.arrondir(totalWattBatterie[i]);
            }
            return totalWattBatterie;
        } catch (Exception e) {
            throw new Exception("Erreur Affichage getTotalPrix"+e.getMessage());
        }
    }
    public double getPrixTotalBatterie(DetailleBesoin[] detailleBesoins,Besoin besoin) throws Exception {
        calculate = new Calculate();
        double total = 0;
        try {
            total = this.getTotalBatterie(detailleBesoins,besoin) * besoin.getBatterie()[0].getPrixWatt(); //besoin.getBatterie()[0].getPrixWatt()
            return this.arrondir(total);
        } catch (Exception e) {
            throw new Exception("Erreur Affichage getPrixTotalBatterie"+e.getMessage());
        }
    }
    public double getPrixTotalPanneau(DetailleBesoin[] detailleBesoins) throws Exception {
        calculate = new Calculate();
        // ----------------------------------------------------- Prix Panneau -------------------------------------------------------------------
        double prixPanneau = 10;
        double total = 0;
        try {
            total = this.getTotalPanneau(detailleBesoins) * prixPanneau;
            return this.arrondir(total);
        } catch (Exception e) {
            throw new Exception("Erreur Affichage getPrixTotalPanneau"+e.getMessage());
        }
    }
    public double azoAmidi(DetailleBesoin[] detailleBesoins) throws Exception{
        calculate = new Calculate();
        try {
            return calculate.azoAmidi(detailleBesoins);
        } catch (Exception e) {
            throw new Exception("Erreur Affichage azoAmidi"+e.getMessage());
        }
    }
    public double prixAzoAmidiParJour(DetailleBesoin[] detailleBesoins) throws Exception{
        try {
            double rep = azoAmidi(detailleBesoins) * 20;
            return rep;
        } catch (Exception e) {
            throw new Exception("Erreur Affichage prixAzoAmidiParJour"+e.getMessage());
        }
    }
    public double arrondir(double value){
        double roundedValue = Math.round(value * 100.0) / 100.0;
        return roundedValue;
    }
}
