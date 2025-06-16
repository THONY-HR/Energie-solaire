<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="classTable.*" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Energie Solaire</title>
    <link rel="stylesheet" href="https://unpkg.com/leaflet@1.9.4/dist/leaflet.css" />
    <style>
        #map {
            height: 400px;
            width: 100%;
            margin-bottom: 20px;
        }
    </style>
</head>
<header>
    <a href="<%= request.getContextPath() %>/ListeDomicile">Liste Domicile</a>
</header>
<body>
    <%
        Batterie[] batteries = (Batterie[]) request.getAttribute("batterie");
        Domicile domicile = (Domicile) request.getAttribute("domicile");
        DetailleBesoin[] detailleBesoins = (DetailleBesoin[]) request.getAttribute("allDetailleBesoins");
        Besoin besoin = (Besoin) request.getAttribute("besoin");
        if (domicile != null) {
    %>
        <h2>Informations de votre domicile</h2>
        <p>Lot: <%= domicile.getLot() %></p>
        <p>Location Map: <%= domicile.getLocationMap() %></p>
        <p>Type Batterie: <%= besoin.getIdBatterie() %></p>
        <p>Date Enregistrement: <%= besoin.getDatyEnregistrement() %></p>
    <% 
        if (detailleBesoins != null) {
    %>
        <h2>Détail des Besoins</h2>
        <% for (DetailleBesoin detailleBesoin : detailleBesoins) { %>
            <p>Nom: <%= detailleBesoin.getNomBesoin() %></p>
            <p>Puissance: <%= detailleBesoin.getPuissanceWatt() %></p>
            <p>Début: <%= detailleBesoin.getDebuDureeS() %></p>
            <p>Fin: <%= detailleBesoin.getFinDuree() %></p>
        <% } %>
        <form action="<%= request.getContextPath() %>/EnergieSolaire?type=dernierConfirmation" method="post">
            <input type="submit" value="Confirmer">
        </form>
    <% 
        } 
    %>
    <form action="<%= request.getContextPath() %>/EnergieSolaire?type=detailleBesoin" method="post">
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
    </form>
    <% 
        } else { 
    %>
    <!-- Formulaire pour entrer les informations de domicile -->
    <form action="<%= request.getContextPath() %>/EnergieSolaire?type=domicile" method="post">
        <h1>Votre Domicile</h1>
        <label>Lot</label>
        <input type="text" name="lot">
        <label>Location map</label>
        <div id="map"></div>
        <input type="hidden" name="locationMap" id="locationMap">
        <label>Type Batterie</label>
        <select name="typeBatterie">
            <% for (Batterie batterie : batteries) { %>
                <option value="<%= batterie.getIdBatterie() %>"><%= batterie.getTypeBatterie() %></option>
            <% } %>
        </select>
        <label>Date d'enregistrement</label>
        <input type="date" name="datyEnregistrement">
        <input type="submit" value="Valider">
    </form>
    <% 
        } 
    %>

    <script src="https://unpkg.com/leaflet@1.9.4/dist/leaflet.js"></script>
    <script>
        // Initialisation de la carte
        const map = L.map('map').setView([-18.8792, 47.5079], 13); // Exemple : Antananarivo
        const tileLayer = L.tileLayer('path/to/local/tiles/{z}/{x}/{y}.png', {
            maxZoom: 19,
            attribution: '© OpenStreetMap contributors'
        }).addTo(map);

        // Ajout d'un marqueur draggable
        const marker = L.marker([-18.8792, 47.5079], { draggable: true }).addTo(map);

        // Mettre à jour la localisation lorsque le marqueur est déplacé
        marker.on('dragend', function () {
            const position = marker.getLatLng();
            document.getElementById('locationMap').value = `${position.lat},${position.lng}`;
        });
    </script>
</body>
</html>
