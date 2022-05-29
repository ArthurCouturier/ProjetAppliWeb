<%@ page import="mainClasses.Playlist" %>
<%@ page import="mainClasses.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Ajouter un son à une playlist</title>
    <link rel="stylesheet" href="./app.css">
</head>
<body>

<input id="nouveauNomSon" name="newNameSon" placeholder="Nom du son">
<input id="nouveauSonArtiste" name="newSongArtist" placeholder="Artiste">
<input id="nouveauSonAlbum" name="newSongAlbum" placeholder="Album">
<input id="UrlSon" name="newUrlSon" placeholder="Url Son">

<h1>Choisir la playlist dans laquelle ajouter son</h1>

<%
    User user = (User) request.getAttribute("user");
    for (Playlist playlist : user.getPlaylists()){
        %>
<input type="radio" name=idPlaylist value=<%=String.valueOf(playlist.getId())%>> <%=playlist.getName()%><br>
<% } %>
<input class="MainButtons" type="submit" name="action" value="Ajouter un son">
</body>
</html>
