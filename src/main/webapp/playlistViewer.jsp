<%@ page import="mainClasses.Playlist" %>
<%@ page import="mainClasses.Song" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title id="nomPlaylistTitre">Ma playlist</title>
    <link rel="stylesheet" href="./app.css">
</head>
<body>

<h1 id="nomPlaylist"></h1> <!--TODO-->
<% Playlist playlist = (Playlist) request.getAttribute("playlist"); %>
<div class="container" id="container">
    <div id="playlists">
        <h1><%=playlist.getName()%></h1>
    </div>

    <form id="form" action="Serv" method="get">
        <div class="field">
            <label for="nouveauNomPlaylist"> Modifier Nom </label>
            <input id="nouveauNomPlaylist" name="newNom" placeholder="Nouveau Nom Playlist">

        </div>
        <input class="MainButtons" type="submit" name="action" value="Changer le nom de la playlist">
        <input class="MainButtons" type="submit" name="action" value="Retour Selection Playlist">

        <div id="radios">
            <% for (Song son : playlist.getSongs()) {
            %>
            <input type="radio" name=idSong value=<%=String.valueOf(son.getId())%>> <%=son.getName()%><br>
            <% } %>
            <span id="slider"></span>
        </div>

        <input class="MainButtons" type="submit" name="action" value="Accéder au Son">
        <input class="MainButtons" type="submit" name="action" value="Supprimer un son">
        <input class="MainButtons" type="submit" name="action" value="Ajouter un son a la playlist">
    </form>

    <div id="musiques">
        <!--TODO-->
    </div>
    <a id="chercherMusique"> <!--TODO barre de recherche de musiques--> </a>
</div>

</body>
</html>
