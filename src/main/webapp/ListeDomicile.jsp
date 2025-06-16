<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="classView.Affichage" %>
<%@ page import="classTable.Domicile" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Energie Solaire</title>
</head>
<body>
<header>
    <a href="<%= request.getContextPath() %>/EnergieSolaire">Retour</a>
</header>
    <table border="1px">
        <tr>
            <th>Id domicile</th>
            <th>Lot</th>
            <th>Location Map</th>
        </tr>
    <% 
        Affichage affichage = (Affichage) request.getAttribute("affichages");
        if (affichage != null && affichage.getDomicile() != null) {
            for (Domicile domicile : affichage.getDomicile()) {
    %>
                <tr>
                    <td>
                        <form action="<%= request.getContextPath() %>/ListeDomicile" method="post">
                            <input type="hidden" name="idDomicile" value="<%= domicile.getIdDomicile() %>">
                            <input type="submit" value="<%= domicile.getIdDomicile() %>">
                        </form>
                    </td>
                    <td><%= domicile.getLot() %></td>
                    <td><%= domicile.getLocationMap() %></td>
                </tr>
    <%
            }
        } else {
    %>
            <tr><td colspan="3">Aucun domicile trouvé.</td></tr>
    <%
        }
    %>
    </table>
</body>
</html>
