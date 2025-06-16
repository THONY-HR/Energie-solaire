package Fonction;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

import classTable.*;
import classView.MinEnergieSolaireParSaison;
import utilitaire.BdConnect;

public class Calculate {
    /*eto no mila amboarina sode adino */
    public SpecificationDetaille[] diviserEnTranchesHoraires(SpecificationDetaille[] specifiDetailles) {
        // Utiliser une liste dynamique pour éviter la taille fixe
        List<SpecificationDetaille> result = new ArrayList<>();

        // Parcourir chaque période donnée
        for (SpecificationDetaille spec : specifiDetailles) {
            Time currentStart = spec.getDebutDuree();
            Time currentEnd = spec.getFinDuree();

            // Diviser la période en tranches horaires
            while (currentStart.before(currentEnd)) {
                Time nextHour = incrementHeure(currentStart);
                // Fin de la tranche = plus petite entre "fin de la période" et "prochaine heure"
                Time trancheEnd = nextHour.before(currentEnd) ? nextHour : currentEnd;

                // Ajouter la tranche horaire à la liste
                result.add(new SpecificationDetaille(
                        spec.getIdSpecificationDetaille(),
                        spec.getEnergieSolaire(),
                        currentStart,
                        trancheEnd,
                        spec.getIdDetailleSaison()
                ));

                // Passer à la tranche suivante
                currentStart = trancheEnd;
            }
        }

        // Convertir la liste en tableau avant de la retourner
        return result.toArray(new SpecificationDetaille[0]);
    }

    // Méthode pour incrémenter l'heure d'une tranche d'une heure
    private static Time incrementHeure(Time time) {
        long timeInMillis = time.getTime();
        long oneHourInMillis = 60 * 60 * 1000; // 1 heure en millisecondes
        return new Time(timeInMillis + oneHourInMillis);
    }

    // Exemple d'utilisation
    public SpecificationDetaille[] getSpec() {
        SpecificationDetaille[] specifiDetailles = new SpecificationDetaille[]{
            new SpecificationDetaille(1, 20, Time.valueOf("06:00:00"), Time.valueOf("10:00:00"), 101),
            new SpecificationDetaille(2, 60, Time.valueOf("10:00:00"), Time.valueOf("13:00:00"), 102),
            new SpecificationDetaille(3, 40, Time.valueOf("13:00:00"), Time.valueOf("16:00:00"), 103),
            new SpecificationDetaille(4, 10, Time.valueOf("16:00:00"), Time.valueOf("18:00:00"), 101)
        };

        // Diviser en tranches horaires
        return this.diviserEnTranchesHoraires(specifiDetailles);
    }
 // specifiDetailles[0] = new SpecificationDetaille(1, 20, Time.valueOf("06:00:00"), Time.valueOf("07:00:00"), 101);
        // specifiDetailles[1] = new SpecificationDetaille(2, 20, Time.valueOf("07:00:00"), Time.valueOf("08:00:00"), 102);
        // specifiDetailles[2] = new SpecificationDetaille(3, 20, Time.valueOf("08:00:00"), Time.valueOf("09:00:00"), 103);
        // specifiDetailles[3] = new SpecificationDetaille(4, 20, Time.valueOf("09:00:00"), Time.valueOf("10:00:00"), 101);
        // specifiDetailles[4] = new SpecificationDetaille(1, 60, Time.valueOf("10:00:00"), Time.valueOf("11:00:00"), 101);
        // specifiDetailles[5] = new SpecificationDetaille(2, 60, Time.valueOf("11:00:00"), Time.valueOf("12:00:00"), 102);
        // specifiDetailles[6] = new SpecificationDetaille(3, 60, Time.valueOf("12:00:00"), Time.valueOf("13:00:00"), 103);
        // specifiDetailles[7] = new SpecificationDetaille(4, 40, Time.valueOf("13:00:00"), Time.valueOf("14:00:00"), 101);
        // specifiDetailles[8] = new SpecificationDetaille(1, 40, Time.valueOf("14:00:00"), Time.valueOf("15:00:00"), 101);
        // specifiDetailles[9] = new SpecificationDetaille(2, 40, Time.valueOf("15:00:00"), Time.valueOf("16:00:00"), 102);
        // specifiDetailles[10] = new SpecificationDetaille(3, 10, Time.valueOf("16:00:00"), Time.valueOf("17:00:00"), 103);
        // specifiDetailles[11] = new SpecificationDetaille(4, 10, Time.valueOf("17:00:00"), Time.valueOf("18:00:00"), 101);


    public double sommePuissanceJourPanneau(DetailleBesoin[] detailleBesoins) throws Exception{
        SpecificationDetaille[] specifiDetailles = getSpec();
        double[] reponseIntervalles = new double[specifiDetailles.length];
        double maxIntervalle = 0;
        int countTab = 0;
        for(SpecificationDetaille specifiDetaille: specifiDetailles){
            for(DetailleBesoin detailleBesoin: detailleBesoins){
                if (this.verifIntervalle(specifiDetaille.getDebutDuree(),specifiDetaille.getFinDuree(),detailleBesoin.getDebutDuree(),detailleBesoin.getFinDuree()) == true) {
                    reponseIntervalles[countTab] = reponseIntervalles[countTab] + detailleBesoin.getPuissanceWatt();
                }
            }
            System.err.println("tableau: de somme" + reponseIntervalles[countTab]);
            countTab++;
        }
        for(int i=0; i<reponseIntervalles.length;i++){
            reponseIntervalles[i] = reponseIntervalles[i]/ (specifiDetailles[i].getEnergieSolaire()/100);
        }
        for(int i=0; i<reponseIntervalles.length;i++){
            if (maxIntervalle < reponseIntervalles[i]) {
                maxIntervalle = reponseIntervalles[i];
            }
        }
        return maxIntervalle * 2;
    }
    public double azoAmidi(DetailleBesoin[] detailleBesoins) throws Exception{
        SpecificationDetaille[] specifiDetailles = getSpec();
        double[] reponseIntervalles = new double[specifiDetailles.length];
        double maxIntervalle = 0;
        int countTab = 0;
        double resteEnWatt = 0;
        for(SpecificationDetaille specifiDetaille: specifiDetailles){
            for(DetailleBesoin detailleBesoin: detailleBesoins){
                if (this.verifIntervalle(specifiDetaille.getDebutDuree(),specifiDetaille.getFinDuree(),detailleBesoin.getDebutDuree(),detailleBesoin.getFinDuree()) == true) {
                    reponseIntervalles[countTab] = reponseIntervalles[countTab] + detailleBesoin.getPuissanceWatt();
                }
            }
            System.err.println("tableau: de somme" + reponseIntervalles[countTab]);
            countTab++;
        }
        for(int i=0; i<reponseIntervalles.length;i++){
            reponseIntervalles[i] = reponseIntervalles[i]/ (specifiDetailles[i].getEnergieSolaire()/100);
        }
        for(int i=0; i<reponseIntervalles.length;i++){
            if (maxIntervalle < reponseIntervalles[i]) {
                maxIntervalle = reponseIntervalles[i];
            }
        }
        for(int i=1; i<reponseIntervalles.length;i++){
            resteEnWatt += maxIntervalle - reponseIntervalles[i];
        }
        return resteEnWatt;
    }
    private boolean verifIntervalle(Time intervalStart, Time intervalEnd, Time start, Time end) {
        if (end.before(start)) {
            return (!intervalEnd.before(start) || !intervalStart.after(end));
        } 
        else {
            return !end.before(intervalStart) && !start.after(intervalEnd);
        }
    }
    
    public double sommePuissanceNuitBatterie(DetailleBesoin[] detailleBesoins, Besoin besoin) throws Exception {
        double total = 0;
        try {
            for (DetailleBesoin detailleBesoin : detailleBesoins) {
                Time debutDuree = detailleBesoin.getDebutDuree();
                Time finDuree = detailleBesoin.getFinDuree();
                if (checkTimeConditions(debutDuree.toLocalTime(), finDuree.toLocalTime())) {
                    total += detailleBesoin.getPuissanceWatt() * longueurDuree(debutDuree, finDuree);
                }
            }
            if (total > 0) {
                total /= besoin.getBatterie()[0].getDecharge();
            }
            System.err.println("sommePuissanceNuitBatterie: " + total);
            return total;
        } catch (Exception e) {
            throw new Exception("Erreur Somme Puissance Nuit Batterie: " + e.getMessage());
        }
    }
    public double[] rentabliliterBatterie(DetailleBesoin[] detailleBesoins, Besoin besoin) throws Exception {
        double rentabliter[] = new double[3];
        double total = 0;
        double max = 0;
        double max1 = 0;
        try {
            for (DetailleBesoin detailleBesoin : detailleBesoins) {
                Time debutDuree = detailleBesoin.getDebutDuree();
                Time finDuree = detailleBesoin.getFinDuree();
                if (checkTimeConditions(debutDuree.toLocalTime(), finDuree.toLocalTime())) {
                    total += detailleBesoin.getPuissanceWatt() * longueurDuree(debutDuree, finDuree);
                }
            }
            if (total > 0) {
                rentabliter[0] = total/this.getBatterie()[0].getDecharge();
                rentabliter[1] = total/this.getBatterie()[1].getDecharge();
                rentabliter[2] = total/this.getBatterie()[2].getDecharge();
            }
            System.err.println("sommePuissanceNuitBatterie: " + total);
            return rentabliter;
        } catch (Exception e) {
            throw new Exception("Erreur Somme Puissance Nuit Batterie: " + e.getMessage());
        }
    }
    public Batterie[] getBatterie() throws Exception{
        BdConnect con = new BdConnect();;
        try {
            return con.getObjectFromTable("Batterie",Batterie.class);
        } catch (Exception e) {
            throw new Exception("Erreur get Batterie:"+e.getMessage());
        }finally{
            con.close();
        }
    }
    public boolean checkTimeConditions(LocalTime timeDebut, LocalTime timeFin) {
        LocalTime eveningStart = LocalTime.of(18, 0);  // 18:00:00
        LocalTime morningEnd = LocalTime.of(6, 0);     // 06:00:00
    
        // Cas où la période traverse la nuit (ex : 5h - 10h)
        if (timeFin.isBefore(timeDebut)) {
            return timeDebut.isAfter(eveningStart) || timeFin.isBefore(morningEnd);
        }
    
        // Cas où le début ou la fin chevauche une période de nuit
        return (timeDebut.isAfter(eveningStart) || timeDebut.isBefore(morningEnd)) ||
               (timeFin.isAfter(eveningStart) || timeFin.isBefore(morningEnd));
    }
    

    public double prixTotal(Besoin besoin, DetailleBesoin[] detailleBesoins)throws Exception{
        double total = 0;
        double panneauWatt = 0;
        double  batterieWatt = 0;
        double marge = 1.20;
    //    --------------------------------------------- // Eto ny prix Ana paneau par watt ---------------------------------------------------------------------------------------------------------------------------------------
        double prixPanneau = 10;
        try {
            panneauWatt = this.sommePuissanceJourPanneau(detailleBesoins);
            batterieWatt = this.sommePuissanceNuitBatterie(detailleBesoins,besoin);
            
            panneauWatt = panneauWatt * prixPanneau;
            batterieWatt = batterieWatt * besoin.getBatterie()[0].getPrixWatt();
            System.err.println("Prix Batterie=" + besoin.getBatterie()[0].getPrixWatt());
            double reponse = panneauWatt + batterieWatt;
            total = reponse * marge;
            System.err.println("prixTotal: " + total);
            return total;
        } catch (Exception e) {
            throw new Exception("Erreur Prix total:" + e.getMessage());
        }
    }
    public void sessionDetailleBesoin(HttpServletResponse response, HttpSession session, DetailleBesoin detailleBesoin) throws IOException {
        try {
            DetailleBesoin[] detailleSessionBesoin;          
            if (session.getAttribute("allDetailleBesoins") == null) {
                detailleSessionBesoin = new DetailleBesoin[1];
                detailleSessionBesoin[0] = detailleBesoin;
                session.setAttribute("allDetailleBesoins", detailleSessionBesoin);
            } else {
                detailleSessionBesoin = (DetailleBesoin[]) session.getAttribute("allDetailleBesoins");
                DetailleBesoin[] newDetailleSessionBesoin = new DetailleBesoin[detailleSessionBesoin.length + 1];
                System.arraycopy(detailleSessionBesoin, 0, newDetailleSessionBesoin, 0, detailleSessionBesoin.length);
                newDetailleSessionBesoin[detailleSessionBesoin.length] = detailleBesoin;
                session.setAttribute("allDetailleBesoins", newDetailleSessionBesoin);
            } 
            response.setContentType("text/plain");
            response.getWriter().write("Detail ajoute avec succès à la session.");
            
        } catch (Exception e) {
            response.setContentType("text/plain");
            response.getWriter().write("Erreur lors de l'ajout des details des besoins à la session : " + e.getMessage());
        }
    }
    public void sessionDomicile(HttpServletResponse response, HttpSession session, Domicile domiciles)throws IOException{
        try {
            Domicile domicile = domiciles;
            session.setAttribute("domicile", domicile);
        } catch (Exception e) {
            response.setContentType("text/plain");
            response.getWriter().write("Erreur lors de l'ajout Domicile à la session : " + e.getMessage()); 
        }
    }
    public void sessionBesoin(HttpServletResponse response, HttpSession session, Besoin besoins)throws IOException{
        try {
            Besoin besoin = besoins;
            session.setAttribute("besoin", besoin);
        } catch (Exception e) {
            response.setContentType("text/plain");
            response.getWriter().write("Erreur lors de l'ajout besoin à la session : " + e.getMessage()); 
        }
    }

    public double longueurDuree(Time debut, Time fin) {
        // Définir les intervalles
        Time debutIntervalleMatin = Time.valueOf("00:00:00");
        Time finIntervalleMatin = Time.valueOf("05:59:00");
        Time debutIntervalleSoir = Time.valueOf("18:00:00");
        Time finIntervalleSoir = Time.valueOf("23:59:59");

        // Convertir Time en millisecondes
        long debutMillis = debut.getTime();
        long finMillis = fin.getTime();

        // Calcul de la consommation totale
        double totalHeures = 0;

        // Calcul de la durée dans l'intervalle 18h - 00h
        if (finMillis > debutIntervalleSoir.getTime() && debutMillis < finIntervalleSoir.getTime()) {
            long start = Math.max(debutMillis, debutIntervalleSoir.getTime());
            long end = Math.min(finMillis, finIntervalleSoir.getTime());
            if (end > start) {
                totalHeures += (end - start) / 1000.0 / 60.0 / 60.0;
            }
        }

        // Calcul de la durée dans l'intervalle 00h - 5h59
        if (finMillis > debutIntervalleMatin.getTime() && debutMillis < finIntervalleMatin.getTime()) {
            long start = Math.max(debutMillis, debutIntervalleMatin.getTime());
            long end = Math.min(finMillis, finIntervalleMatin.getTime());
            if (end > start) {
                totalHeures += (end - start) / 1000.0 / 60.0 / 60.0;
            }
        }

        return totalHeures;
    }
    public double timeToDouble(Time time){
        long times = time.getTime();
        return times / (1000.0 * 60 * 60);
    }
    private Time toTime(String timeStr) {
        try {
            // Creer un format SimpleDateFormat pour l'heure
            java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("HH:mm:ss");
            java.util.Date parsedDate = sdf.parse(timeStr);
            return new java.sql.Time(parsedDate.getTime());
        } catch (Exception e) {
            return null; // Renvoie null en cas d'erreur de parsing
        }
    }
    public int nombrePuissance(double valeur, double minEnergies){
        double pourcentage = (minEnergies/100)*valeur;
        int i = 1;
        while(pourcentage * i <= valeur){
            i++;
        }
        if(pourcentage * i == valeur){
            i = i + 0;
        }else if(pourcentage * i < valeur){
            i = i+1;
        }
        System.err.println("nombrePuissance" + i);
        return i;
    }
    public void destroyAllSessions(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false); // ne pas créer une nouvelle session
        if (session != null) {
            session.invalidate();
            System.err.println("Session effacer avec succes");
        }
    }
    public double arrondir(double val){
        int nbrentier = (int) val;
        double nbrdouble = (double) nbrentier;
        if ((val - nbrdouble)>=0.5) {
            nbrdouble +=1;
        }else{
            nbrdouble = nbrdouble + 0;
        }
        return nbrdouble;
    }
}