<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Se connecter</title>
    <link rel="stylesheet" href="/app/app.css">
    <script type="module" src="./connection.js" defer></script>
</head>
<body>

<div class="container">
    <form id="form" action="Servlet" method="get">
        <h1>Connection</h1>
        <div class="field">
            <label for="password"> Password </label>
            <input type="password" placeholder="Password" id="password">

            <label for="pseudo"> Pseudo </label>
            <input type="pseudo" placeholder="Pseudo" id="pseudo">
        </div>
        <input class="MainButtons" type="submit" name="action" value="Se connecter">
        <span class="MainButtons" id="connectionBouton">Se connecter</span>
    </form>
</div>


<p></p>
<div id="retourConnection"></div>
<p></p>
<a href="inscription.html"><span class="MainButtons" id="allerInscription">S'inscrire</span></a>

</body>
</html>