require('/app/dotenv').config() // A toujours mettre tout en haut, permet de config le .env
const express = require('express')
const connection = require('/app/db-config')
const app = express()
connection.connect((error) => {
    if (error) {
        console.error(error)
    } else {
        console.log("connected to mysql")
    }
})

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
        app.get('/'+process.env.TABLE, (req, res) => {
            connection.query('INSERT INTO ' + process.env.TABLE + ' (pseudo) VALUES '+ personne.pseudo, (error, result) => {
                if (error) {
                    console.error(error)
                    res.status(500).send("error while retrieving table database")
                } else {
                    res.status(200).json(result)
                }
            })
        })
        app.get('/'+process.env.TABLE, (req, res) => {
            connection.query('SELECT * FROM ' + process.env.TABLE, (error, result) => {
                if (error) {
                    console.error(error)
                    res.status(500).send("error while retrieving table database")
                } else {
                    res.status(200).json(result)
                }
            })
        })


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
