import {Profile} from '/app/ClassJS/Profile.js';

var pseudo = document.getElementById("pseudo");
var password = document.getElementById("password");
var connectionBouton = document.getElementById("connectionBouton");
connectionBouton.addEventListener('click', connection);
var retourConnection = document.getElementById("retourConnection");

function connection() {
    var personne = JSON.parse(localStorage.getItem(pseudo.value));
    if (personne.password == password.value) {
        retourConnection.innerHTML = "Hello "+personne.pseudo;
    }
}
