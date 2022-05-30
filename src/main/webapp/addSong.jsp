<%@ page import="java.util.List" %>
<%@ page import="mainClasses.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Ajouter un son à une playlist</title>
    <link rel="stylesheet" href="./app.css">
</head>
<body>

<h1>Données du son à ajouter</h1>
<form id="form" action="Serv" method="get">
    <input id="nouveauNomSon" name="newSongName" placeholder="Nom du son">
    <input id="nouveauSonArtiste" name="newSongArtist" placeholder="Artiste">
    <input id="nouveauSonAlbum" name="newSongAlbum" placeholder="Album">
    <input id="UrlSon" name="newSongURL" placeholder="Url Son">

    <h1>Choisir la playlist dans laquelle ajouter son</h1>

    <%
        User user = (User) request.getAttribute("user");
        for (Playlist playlist : user.getPlaylists()){
            %>
    <input type="radio" name=idPlaylist value=<%=String.valueOf(playlist.getId())%>> <%=playlist.getName()%><br>
    <% } %>
    <input class="MainButtons" type="submit" name="action" value="Soumettre un son">

    <h1>Ou choisir parmis les sons déjà présents dans la base de données</h1>

    <%
        List<Artist> allArtists = (List<Artist>) request.getAttribute("allArtists");
        for (Artist artist : allArtists) {
    %> <h2><%=artist.getName()%></h2>
    <%
        for (Album album : artist.getAlbums()) {
    %> <h3><%=album.getName()%></h3>
    <%
        for (Song song: album.getSongs())  {
    %>
    <input type="radio" name=idSong value=<%=String.valueOf(song.getId())%>> <%=song.getName()%><br>
    <%}}}%>
</form>
</body>
</html>
