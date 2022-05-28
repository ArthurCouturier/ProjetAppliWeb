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
        <input class="MainButtons" type="submit" name="action" value="Ajouter une Playlist">
        <% User user = (User) request.getAttribute("user");
        for (Playlist playlist : user.getPlaylists()) {
        %>
        <input type="radio" name=idPlaylist value=<%=String.valueOf(playlist.getId())%>> <%=playlist.getName()%><br>
          <% } %>
        <input class="MainButtons" type="submit" name="action" value="Accéder à la Playlist">
        <input class="MainButtons" type="submit" name="action" value="Supprimer la Playlist">

    </form>
</div>

</body>
</html>


