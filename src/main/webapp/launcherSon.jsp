<%@ page import="mainClasses.Song" %>
<%@ page import="mainClasses.Playlist" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Video Son</title>
    <link rel="stylesheet" href="./app.css">
</head>
<body>
<% Song son = (Song) request.getAttribute("son"); %>
<div class="container" id="container">
    <div id="playlists">
        <h1><%=son.getName()%></h1>
    </div>

    <form id="form">
        <iframe width="560" height="315" src=<%=son.getUrl()%> title="YouTube_video_player" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>
        <input class="MainButtons" type="submit" name="action" value="Retour Playlist">
    </form>

    <div id="musiques">
        <!--TODO-->
    </div>
    <a id="chercherMusique"> <!--TODO barre de recherche de musiques--> </a>
</div>
</body>
</html>
