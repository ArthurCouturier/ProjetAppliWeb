<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>

<html lang="en">
<head>
    <meta charset="utf-8">
    <title>S'inscrire</title>
    <link rel="stylesheet" href="/app/app.css">
    <script type="module" src="inscription.js" defer></script>
</head>
<body>

<div class="container">
    <form id="form">
        <h1>Inscription</h1>
        <div class="field">
            <label for="password"> Password </label>
            <input type="password" placeholder="Password" id="password"/>

            <label for="email"> Email </label>
            <input placeholder="Email" id="email"/>

            <label for="pseudo"> Pseudo </label>
            <input placeholder="Pseudo" id="pseudo">
        </div>

        <p>By signing up, I agree to the Terms of Service and Privacy Policy</p>

        <span class="MainButtons" id="inscriptionBouton">S'inscrire</span>
    </form>
</div>

<p></p>
<div id="retourInscription"></div>
<p></p>
<a href="connection.html"><span class="MainButtons" id="allerConnection">Se connecter</span></a>
<p></p>
<span class="MainButtons" id="viderStockage">Vider la base de données</span>


</div>

</body>
</html>