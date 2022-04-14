import Profile from "../ClassJS/Profile";

var pseudo = document.getElementById("pseudo");
var mail = document.getElementById("email");
var password = document.getElementById("password");
var id = 0;
var profiles = [];

var inscriptionBouton = document.querySelector("#inscriptionBouton");
inscriptionBouton.addEventListener('click', createAccount);


function createAccount(){
    // ex: console.log(pseudo.value);

    // Vérifier si psudo ou mail déjà en BD

    console.log("coucou1");
    profiles.push(new Profile.constructor(pseudo.value, id));
    id ++;
    console.log("coucou2");
    console.log(profiles.length);
    console.log("coucou3");
}
