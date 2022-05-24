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
        addedHTML += "<a href='playlistViewer.html' id="+personne.playlists[p][0]+"><span class=\"MainButtons\">"+personne.playlists[p][0]+"</span></a>";
    }
    playlistsHTML.innerHTML = basePlaylistHTML + addedHTML;
    for (let p = 0; p < personne.playlists.length; p++) {
        document.getElementById(personne.playlists[p][0]).
            addEventListener('click',
            function(){localStorage.setItem("playlistCourante", personne.playlists[p])});
    }
}

function creerPlaylist() {
    var p = ["PLAYLIST"+(personne.playlists.length+1).toString()];
    personne.playlists.push(p);
    localStorage.setItem(pseudo, JSON.stringify(personne));
}

creerHTMLPlaylists();
