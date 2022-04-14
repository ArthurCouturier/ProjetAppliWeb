import * as Profile from '/app/ClassJS/Profile.js'

var pseudo = document.getElementById("pseudo");
var inscriptionBouton = document.getElementById("inscriptionBouton");
inscriptionBouton.addEventListener(createAccount());

function createAccount(){
    console.log(pseudo.textContent);
}
