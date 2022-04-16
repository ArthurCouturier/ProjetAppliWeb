import {Profile} from '/app/ClassJS/Profile.js';

var pseudo = document.getElementById("pseudo");
var password = document.getElementById("password");
var connectionBouton = document.getElementById("connectionBouton");
connectionBouton.addEventListener('click', connection);
var retourConnection = document.getElementById("retourConnection");

function connection() {
    var personne = JSON.parse(localStorage.getItem(pseudo.value));
    if (personne.password == password.value) {
        retourConnection.innerHTML = "<a href=\"personnalPage.html\"><span class=\"MainButtons\" id=\"allerInscription\">Accéder à mon profil</span></a>";
        localStorage.setItem("utilisateurActuel", personne.id.toString());
    }
}

// Permet de réinitialiser l'utilisateur actuel
if (localStorage.getItem("utilisateurActuel") != null) {
    localStorage.removeItem("utilisateurActuel");
}
