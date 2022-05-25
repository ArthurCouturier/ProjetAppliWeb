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
        <!--TODO-->
    </div>
    <a id="creerPlaylistBouton"><span class="MainButtons">Créer une playlist</span></a><!--href="creationPlaylist.html"--> <!--TODO-->
    <% User user = (User) request.getAttribute("user");
    System.out.println(user.getPlaylists().size());
    for (Playlist playlist : user.getPlaylists()) {%>
    <a href="playlistViewer.jsp" id=<% playlist.getName(); %>> <span class=\"MainButtons\"><% playlist.getName(); %></span></a><%
    }%>
</div>

</body>
</html>
