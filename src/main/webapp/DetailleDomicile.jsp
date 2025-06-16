<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="classTable.*" %>
<%@ page import="classView.*" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Energie Solaire</title>
</head>
<header>
    <a href="<%= request.getContextPath() %>/ListeDomicile">Retour</a>
</header>
    <% 
        Domicile domicile = (Domicile) request.getAttribute("domicile");
        Affichage affichage = new Affichage();
    %>
    <p>Lot: <%=domicile.getLot()%></p>
    <p>locationMap: <%=domicile.getLocationMap()%><p>
    <p>Type Batterie: <%=domicile.getBesoin().getBatterie()[0].getTypeBatterie()%></p>
    <p>Total watt panneau: <%=affichage.getTotalPanneau(domicile.getBesoin().getDetailleBesoin())%></p>
    <p>Total Prix panneau: <%=affichage.getPrixTotalPanneau(domicile.getBesoin().getDetailleBesoin())%> Ar</p>
    <p>Total watt batterie: <%=affichage.getTotalBatterie(domicile.getBesoin().getDetailleBesoin(),domicile.getBesoin())%></p>
    <p>Total Prix batterie: <%=affichage.getPrixTotalBatterie(domicile.getBesoin().getDetailleBesoin(),domicile.getBesoin())%> Ar</p>
    <p>Reste a Vendre: <%=affichage.azoAmidi(domicile.getBesoin().getDetailleBesoin())%></p>
    <p>Total Prix A vendre: <%=affichage.prixAzoAmidiParJour(domicile.getBesoin().getDetailleBesoin())%> Ar</p>
    <h1>Total Prix: <%=affichage.getTotalPrix(domicile.getBesoin().getDetailleBesoin(),domicile.getBesoin())%> Ar</h1>
    <%
        for(int i = 0; i<affichage.getRentabilier(domicile.getBesoin().getDetailleBesoin(),domicile.getBesoin()).length; i++){
           %><br><% out.println(affichage.getRentabilier(domicile.getBesoin().getDetailleBesoin(),domicile.getBesoin())[i]);
        }
    %>
    <table border="1px">
        <tr>
            <%-- <th>idDetailleBesoin</th> --%>
            <th>nomBesoin</th>
            <th>puissanceWatt</th>
            <th>debutDuree</th>
            <th>finDuree</th>
            <th>Delete</th>
        </tr>
    <%
        for(DetailleBesoin detailleBesoin: domicile.getBesoin().getDetailleBesoin()){
            %>
                <tr>
                    <%-- <td><%=detailleBesoin.getIdDetailleBesoin()%></td> --%>
                    <td><%=detailleBesoin.getNomBesoin()%></td>
                    <td><%=detailleBesoin.getPuissanceWatt()%></td>
                    <td><%=detailleBesoin.getDebutDuree()%></td>
                    <td><%=detailleBesoin.getFinDuree()%></td>
                    <td>
                        <form action="<%= request.getContextPath() %>/Update?type=delete" method="post">
                            <input type="hidden" name="idBesoin" id="idBesoin" value="<%=detailleBesoin.getIdDetailleBesoin()%>">
                            <input type="submit" value="delete">
                        </form>
                    </td>
                <tr>
            <%
        }
    %>
    </table>
    <%-- <form action="<%= request.getContextPath() %>/Update?type=insertionBesoin" method="post">
        <h1>Ajouter Besoin</h1>
        
        <input type="hidden" name="idBesoin" id="idBesoin">
        
        <label for="nomBesoin">Nom du Besoin :</label>
        <input type="text" name="nomBesoin" id="nomBesoin" required>
        
        <label for="puissanceWatt">Puissance (Watt) :</label>
        <input type="number" name="puissanceWatt" id="puissanceWatt" required min="1" step="1">
        
        <label for="debutDuree">Début de la durée :</label>
        <input type="time" name="debutDuree" id="debutDuree" required>
        
        <label for="finDuree">Fin de la durée :</label>
        <input type="time" name="finDuree" id="finDuree" required>
        
        <input type="submit" value="Valider">
    </form> --%>
<body>
</body>