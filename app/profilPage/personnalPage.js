var container = document.getElementById("container");
container.addEventListener('click', creerHTMLPlaylists);
var playlistsHTML = document.getElementById("playlists");
var basePlaylistHTML = playlistsHTML.innerHTML;
var pseudo = localStorage.getItem("utilisateurActuel");
var personne = JSON.parse(localStorage.getItem(pseudo));
var creationPlaylist = document.getElementById("creerPlaylistBouton");
creationPlaylist.addEventListener('click', creerPlaylist);

function creerHTMLPlaylists() {
    var addedHTML;
    for (let p = 0; p < personne.playlists.length; p++) {
        addedHTML += "<a><span class=\"MainButtons\">"+personne.playlists[p]+"</span></a>";
    }
    playlistsHTML.innerHTML = basePlaylistHTML + addedHTML;
}

function creerPlaylist() {
    personne.playlists.push("PLAYLIST");
    localStorage.setItem(pseudo, JSON.stringify(personne));
}

creerHTMLPlaylists();
