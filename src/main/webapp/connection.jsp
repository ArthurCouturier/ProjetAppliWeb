<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" import="java.util.*" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Se connecter</title>
    <link rel="stylesheet" href="./app.css">
</head>
<body>

<div class="container">
    <form id="form" action="Serv" method="get">
        <h1>Connection</h1>
        <div class="field">
            Pseudo : <input type="pseudo" placeholder="Pseudo" name="pseudo">

            Password : <input type="password" placeholder="Password" name="password">
        </div>
        <input class="MainButtons" type="submit" name="action" value="connexion">
    </form>
    <% String typeError = (String) request.getAttribute("error");
        if (typeError == "notfound")  { %>
            Pseudo ou mot de passe incorrect !! Veuillez réessayer !!
        <% } %>
</div>


<p></p>
<div id="retourConnection"></div>
<p></p>
<a href="inscription.jsp"><span class="MainButtons" id="allerInscription">S'inscrire</span></a>

</body>
</html>