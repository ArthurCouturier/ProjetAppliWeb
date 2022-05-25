<%@ page import="mainClasses.User" %>
<%@ page import="mainClasses.Playlist" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Mon profil</title>
    <link rel="stylesheet" href="./app.css">
</head>
<body>

<h1 id="pseudoTitre"></h1> <!--TODO-->

<div class="container" id="container">
    <div id="playlists">
        <h1>Mes playlists</h1>
    </div>
    <form id="form" action="Serv" method="get">
        <a id="creerPlaylistBouton"><span class="MainButtons">Créer une playlist<input type="submit" name="action" value="addPlaylist"></span></a>
        <% User user = (User) request.getAttribute("user");
        System.out.println("TEST PERSO PAGE playlists of user: "+user.getPlaylists());
        for (Playlist playlist : user.getPlaylists()) {
            String name = playlist.getName();
            System.out.println("TEST PERSONNAL PAGE actual playlist: "+ name);
        %>
            <a href="playlistViewer.jsp"><span class="MainButtons"><%out.print(playlist.getName());%></span></a>
        <% } %>
    </form>
</div>

</body>
</html>
