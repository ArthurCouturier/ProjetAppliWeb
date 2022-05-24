<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>

<html lang="en">
<head>
    <meta charset="utf-8">
    <title>S'inscrire</title>
    <link rel="stylesheet" href="./app.css">
</head>
<body>

<div class="container">
    <form id="form" action="Serv" method="get">
        <h1>Inscription</h1>
        <div class="field">
            Pseudo : <input placeholder="Pseudo" name="pseudo"/>

            Password : <input type="password" placeholder="Password" name="password"/>

            Email : <input type="email" placeholder="Email" name="email"/>
        </div>

        <p>By signing up, I agree to the Terms of Service and Privacy Policy</p>

        <input type="submit" name="action" value="inscription">
    </form>
    <% String typeError = (String) request.getAttribute("error");
        if (typeError == "pseudoInvalid")  { %>
    Pseudo déjà utilisé !! Veuillez réessayer !!
    <% } %>
</div>

<p></p>
<div id="retourInscription"></div>
<p></p>
<a href="connection.jsp"><span class="MainButtons" id="allerConnection">Se connecter</span></a>

</div>

</body>
</html>