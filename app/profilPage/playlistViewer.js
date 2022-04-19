var playlistCourante;
if (localStorage.getItem("playlistCourante") != null) {
    playlistCourante = localStorage.getItem("playlistCourante");
    document.getElementById("nomPlaylistTitre").innerText = playlistCourante;
    document.getElementById("nomPlaylist").innerText = playlistCourante;
}
var container = document.getElementById("container");
var baseMusiqueHTML = document.getElementById("musiques");

function creerHTMLMusiques() {
    var addedHTML;
    for (let p = 1; p < playlistCourante.length; p++) { // p = 0 pour le nom de la playlist
        addedHTML += "<a><span class=\"MainButtons\">"+playlistCourante[p]+"</span></a>";
    }
    playlistsHTML.innerHTML = baseMusiqueHTML + addedHTML;
}

creerHTMLMusiques();
