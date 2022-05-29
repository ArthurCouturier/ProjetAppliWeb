package mainClasses;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/Serv")
public class Serv extends HttpServlet {

    @EJB
    private final Facade facade = new Facade();

    private User actualUser;
    private Playlist actualPlaylist;

    public Serv() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {

            String action = request.getParameter("action");
            System.out.println("Test1");
            switch (action) {
                case "connexion": {
                    String pseudo = request.getParameter("pseudo");
                    String password = request.getParameter("password");
                    if (pseudo == "" || password == "") {
                        request.setAttribute("error", "notfound");
                        request.getRequestDispatcher("connection.jsp").forward(request, response);
                    }
                    User user = facade.findUser(pseudo, password);
                    if (user == null) {
                        request.setAttribute("error", "notfound");
                        request.getRequestDispatcher("connection.jsp").forward(request, response);
                    } else {
                        this.actualUser = user;
                        request.setAttribute("user", user);
                        request.getRequestDispatcher("personnalPage.jsp").forward(request, response);
                    }
                    break;

                }
                case "inscription": {
                    String pseudo = request.getParameter("pseudo");
                    String password = request.getParameter("password");
                    String email = request.getParameter("email");
                    facade.addUser(pseudo, email, password);
                    request.getRequestDispatcher("connection.jsp").forward(request, response);
                    break;

                }

                case "Ajouter une Playlist": {
                    facade.addPlaylist("Playlist" + Integer.toString(actualUser.getPlaylists().size() + 1), actualUser);
                    request.setAttribute("user", actualUser);
                    request.getRequestDispatcher("personnalPage.jsp").forward(request, response);
                    break;
                }

                case "Changer le nom de la playlist": {
                    String newNom = request.getParameter("newNom");
                    facade.changePlaylistName(actualPlaylist, newNom);
                    this.actualPlaylist = facade.findPlaylist(String.valueOf(this.actualPlaylist.getId()));
                    request.setAttribute("playlist", this.actualPlaylist);
                    request.getRequestDispatcher("playlistViewer.jsp").forward(request, response);
                    break;
                }

                case "Accéder à la Playlist": {
                    String idplaylist = request.getParameter("idPlaylist");
                    this.actualPlaylist = facade.findPlaylist(idplaylist);
                    request.setAttribute("playlist", this.actualPlaylist);
                    request.getRequestDispatcher("playlistViewer.jsp").forward(request, response);
                    break;
                }

                case "Supprimer la Playlist": {
                    this.actualPlaylist = null;
                    String idplaylist = request.getParameter("idPlaylist");
                    Playlist playlistsuppr = facade.findPlaylist(idplaylist);
                    this.actualUser = facade.removePlaylist(playlistsuppr, this.actualUser);
                    request.setAttribute("user", actualUser);
                    request.getRequestDispatcher("personnalPage.jsp").forward(request, response);
                    break;
                }

                case "Retour Selection Playlist": {
                    request.setAttribute("user", actualUser);
                    request.getRequestDispatcher("personnalPage.jsp").forward(request, response);
                    break;
                }

                case "Retour Playlist": {
                    request.setAttribute("playlist", this.actualPlaylist);
                    request.getRequestDispatcher("playlistViewer.jsp").forward(request, response);
                    break;
                }

                case "Accéder au Son": {
                    String idSong = request.getParameter("idSong");
                    request.setAttribute("son", facade.findSong(idSong));
                    request.getRequestDispatcher("launcherSon.jsp").forward(request, response);
                    break;
                }

                case "Ajouter un son a la playlist": {
                    request.getRequestDispatcher("addSong.jsp").forward(request, response);
                    break;
                }

                case "Ajouter un son": {
                    String nomSon = request.getParameter("newNameSon");
                    String artistName =  request.getParameter("newSongArtist");
                    String albumName = request.getParameter("newSongAlbum");
                    String url = request.getParameter("newUrlSon");
                    Song song = facade.addSong(nomSon,null,url);
                    Album album = facade.findAlbumOfArtist(albumName, artistName);
                    if (album.getSongs().contains(song)) {
                        break;
                    } else {
                        album.addSong(song);
                    }
                    this.actualPlaylist = facade.addSongPlaylist(String.valueOf(song.getId()),this.actualPlaylist);
                    request.setAttribute("playlist", this.actualPlaylist);
                    request.getRequestDispatcher("playlistViewer.jsp").forward(request, response);
                    break;
                }

                case "Supprimer un son": {
                    String idSong = request.getParameter("idSong");
                    this.actualPlaylist = facade.removeSong(this.actualPlaylist,idSong);
                    request.setAttribute("playlist", this.actualPlaylist);
                    request.getRequestDispatcher("playlistViewer.jsp").forward(request, response);
                    break;
                }

            }

            } catch(PseudoInvalidException e){
                request.setAttribute("error", "pseudoInvalid");
                request.getRequestDispatcher("inscription.jsp").forward(request, response);
                e.printStackTrace();
            } catch(Exception e){
                e.printStackTrace();
            }
        }
}
