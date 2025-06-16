package classView;

import classTable.Besoin;
import classTable.DetailleBesoin;
import classTable.Domicile;
import utilitaire.BdConnect;

public class InformationDomicile {
    private Domicile domicile;
    private Besoin besoin;
    private DetailleBesoin[] detailleBesoins;

    public InformationDomicile(Domicile domiciles,Besoin besoins,DetailleBesoin[] detailleBesoins){
        this.domicile = domiciles;
        this.besoin = besoins;
        this.detailleBesoins = detailleBesoins;
    }
    public void inserer() throws Exception{
        try {
            this.domicile.setNomTable("Domicile");
            this.domicile.inserer();
            this.domicile.setKey("idDomicile");
            int idDomicile = this.domicile.getLastInsert();
            this.besoin.setIdDomicile(idDomicile);
            this.besoin.setNomTable("Besoin");
            this.besoin.inserer();
            this.besoin.setKey("idBesoin");
            int idBesoin = this.besoin.getLastInsert();
            for(DetailleBesoin detailleBesoin: this.detailleBesoins){
                detailleBesoin.setIdBesoin(idBesoin);
                detailleBesoin.setNomTable("DetailleBesoin");
                detailleBesoin.inserer();
            }
        } catch (Exception e) {
            System.err.println("Erreur Information" + e.getMessage());
        }
    }
}
