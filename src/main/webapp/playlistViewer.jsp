<%--
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
    <script type="module" src="playlistViewer.js" defer></script>
</head>
<body>

<h1 id="nomPlaylist"></h1> <!--TODO-->

<div class="container" id="container">

    <form id="form">
        <div class="field">
            <label for="nouveauNomPlaylist"> Modifier Nom </label>
            <input placeholder="Modifier Nom Playlist" id="nouveauNomPlaylist">
        </div>
        <span class="MainButtons" id="changementNomBouton">Confirmer</span>
    </form>

    <div id="musiques">
        <!--TODO-->
    </div>
    <a id="chercherMusique"> <!--TODO barre de recherche de musiques--> </a>
</div>

</body>
</html>