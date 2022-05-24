var playlistCourante = localStorage.getItem("playlistCourante");
var personne = JSON.parse(localStorage.getItem(localStorage.getItem("utilisateurActuel")));
var container = document.getElementById("container");
var baseMusiqueHTML = document.getElementById("musiques");
var changerNomBouton = document.getElementById("changementNomBouton");
changerNomBouton.addEventListener('click', changerNom);

function creerHTMLMusiques() {
    if (localStorage.getItem("playlistCourante") != null) {
        playlistCourante = localStorage.getItem("playlistCourante");
        document.getElementById("nomPlaylistTitre").innerText = playlistCourante;
        document.getElementById("nomPlaylist").innerText = playlistCourante;
    }
    var addedHTML;
    for (let p = 1; p < playlistCourante.length; p++) { // p = 0 pour le nom de la playlist
        //addedHTML += "<a><span class=\"MainButtons\">"+personne.playlists.getItem(playlistCourante)[p]+"</span></a>";
    }
    if (addedHTML != null) {
        playlistsHTML.innerHTML = baseMusiqueHTML + addedHTML;
    }
}

function changerNom() {
    for (let p = 0; p < personne.playlists.length; p++) {
        console.log(personne.playlists[p][0]);
        console.log(localStorage.getItem("playlistCourante"));
        if (personne.playlists[p][0] == localStorage.getItem("playlistCourante")) {
            personne.playlists[p][0] = document.getElementById("nouveauNomPlaylist").value.toString();
            localStorage.setItem(personne.pseudo, JSON.stringify(personne));
            localStorage.setItem("playlistCourante", personne.playlists[p]);
        }
    }
    creerHTMLMusiques();
}

creerHTMLMusiques();
