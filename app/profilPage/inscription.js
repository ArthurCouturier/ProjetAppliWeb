// https://practicalprogramming.fr/nodejs-mysql Va peut être nous aider à créer une BD mySQL et la connecter avec une appli node.js
// https://www.youtube.com/watch?v=JOik3MMZ_PY&ab_channel=NadfriJS Pour faire du local storage

// Idée pour les mdp: stocker en mémoire une fonction entre le msp et le pseudo qui soit bijective
// pour qu'ils ne soient pas stockés en clair dans la base de données
// Bijection pour pouvoir etre certain de faire face au réel utilisateur uniquement par l'unicité
// d'existence des solutions.

import {Profile} from '/app/ClassJS/Profile.js';

var pseudo = document.getElementById("pseudo");
var mail = document.getElementById("email");
var password = document.getElementById("password");
var inscriptionBouton = document.querySelector("#inscriptionBouton");
inscriptionBouton.addEventListener('click', createAccount);
var viderStockageBouton = document.getElementById("viderStockage");
viderStockageBouton.addEventListener('click', viderProfiles);

var id = 0;

function checkPossibilityToSubscribe(_pseudo, _mail){
    var ok = true;
    ok = ok && !localStorage.getItem(_pseudo); // Check if pseudo is already used
    /*if (!_mail.contains("@")) {
        return "Email non valide. Ex: arthur.couturier@etu.inp-n7.fr";
    }*/ // Ne fonctionne pas
    if (ok) {
        return "ok";
    } else {
        return "Pseudo ou Mail déjà utilisé"
    }
}

function createAccount(){
    // ex: console.log(pseudo.value);
    var retourInscription = document.getElementById("retourInscription");

    var check = checkPossibilityToSubscribe(pseudo.value, email.value)
    if (check == "ok") {
        var p = [];
        var bibli = [];
        bibli.push("Bibliothèque");
        p.push(bibli);
        var personne = {
            pseudo: pseudo.value,
            mail: mail.value,
            password: password.value,
            playlists: p
        };
        localStorage.setItem(pseudo.value, JSON.stringify(personne));


        id ++;
        var ppp = JSON.parse(localStorage.getItem(pseudo.value)).pseudo
        retourInscription.innerHTML = "Vous êtes bien enregistré dans notre base de donnnées "+ppp;
    } else {
        retourInscription.innerHTML = check;
    }
}

export function verifyConnection(_pseudo, _password) {
    if (! pseudos.has(_pseudo)) {
        return null; // marquer dans le futur qu'on ne connait pas ce pseudo
    }
    if (_password == passwords.get(pseudos(pseudo).getId())) {
        return pseudos(pseudo);
    }

    return null;
}

function viderProfiles() {
    localStorage.clear();
}
