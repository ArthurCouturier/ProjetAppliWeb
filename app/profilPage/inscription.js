import {Profile} from '/app/ClassJS/Profile.js';

var pseudo = document.getElementById("pseudo");
var mail = document.getElementById("email");
var password = document.getElementById("password");
var id = 0;
var profiles = new Map();
var emails = new Map();
var passwords = new Map();

var inscriptionBouton = document.querySelector("#inscriptionBouton");
inscriptionBouton.addEventListener('click', createAccount);

function checkPossibilityToSubscribe(_pseudo, _mail){
    var ok = true;
    ok = ok && !profiles.has(_pseudo); // Check if pseudo is already used
    ok = ok && !emails.has(_mail); // Check if email is already used
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
        profiles.set(pseudo.value, new Profile.constructor(pseudo.value, id));
        passwords.set(id, password.value);
        emails.set(mail.value, pseudo.value);
        id ++;
        retourInscription.innerHTML = "Vous êtes bien enregistré dans notre base de donnnées";
    } else {
        retourInscription.innerHTML = check;
    }
}

export function getProfiles() {
    return profiles.copy;
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
