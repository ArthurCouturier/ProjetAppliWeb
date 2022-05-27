<%@ page import="mainClasses.Playlist" %><%--
  Created by IntelliJ IDEA.
  User: 33783
  Date: 24/05/2022
  Time: 19:08
  To change this template use File | Settings | File Templates.
--%>
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

    <form id="form">
        <div class="field">
            <label for="nouveauNomPlaylist"> Modifier Nom </label>
            <input id="nouveauNomPlaylist" name="newNom" value="Nouveau Nom Playlist">
        </div>
        <input class="MainButtons" type="submit" name="action" value="Changer le nom de la playlist">
        <input class="MainButtons" type="submit" name="action" value="Retour Selection Playlist">
    </form>

    <div id="musiques">
        <!--TODO-->
    </div>
    <a id="chercherMusique"> <!--TODO barre de recherche de musiques--> </a>
</div>

</body>
</html>
