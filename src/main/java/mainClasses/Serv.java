package mainClasses;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.List;

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
                    request.setAttribute("user", this.actualUser);
                    List<Artist> allArtists = facade.getAllArtists();
                    request.setAttribute("allArtists", allArtists);
                    request.getRequestDispatcher("addSong.jsp").forward(request, response);
                    break;
                }

                case "Soumettre un son": {
                    String nomSon = request.getParameter("newSongName");
                    String artistName =  request.getParameter("newSongArtist");
                    String albumName = request.getParameter("newSongAlbum");
                    String url = request.getParameter("newSongURL");

                    if (url.contains("youtu")) {
                        if (url.contains("youtube")) {
                            url = "https://www.youtube.com/embed/"+url.substring(32, 43);
                        } else if (url.contains("youtu.be")) {
                            url = "https://www.youtube.com/embed/"+url.substring(17, 28);
                        }
                    }

                    Song song;

                    if (nomSon != "" && artistName != "" && albumName != "" && url != "") {
                        song = facade.addSong(nomSon,null,url);
                        Album album = facade.findAlbumOfArtist(albumName, artistName);
                        if (!album.getSongs().contains(song)) {
                            album.addSong(song);
                        }
                    } else {
                        String idSong = request.getParameter("idSong");
                        if (idSong != null ) {
                            song = facade.findSongById(Integer.parseInt(idSong));
                            if (song == null) {
                                request.setAttribute("user", this.actualUser);
                                List<Artist> allArtists = facade.getAllArtists();
                                request.setAttribute("allArtists", allArtists);
                                request.getRequestDispatcher("addSong.jsp").forward(request, response);
                                break;
                            }
                        } else {
                            request.setAttribute("user", this.actualUser);
                            List<Artist> allArtists = facade.getAllArtists();
                            request.setAttribute("allArtists", allArtists);
                            request.getRequestDispatcher("addSong.jsp").forward(request, response);
                            break;
                        }
                    }
                    Playlist playlist = facade.findPlaylist(request.getParameter("idPlaylist"));
                    if (playlist != null) {
                        this.actualPlaylist = playlist;
                    }
                    System.out.println(this.actualPlaylist.getName());
                    this.actualPlaylist = facade.addPlaylistoSong(String.valueOf(song.getId()),this.actualPlaylist);
                    this.actualPlaylist = facade.addSongPlaylist(String.valueOf(song.getId()),this.actualPlaylist);
                    System.out.println("apres"+this.actualPlaylist.getName());
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

                case "Retirer ce son de la BDD": {
                    Song song = facade.findSongById(Integer.parseInt(request.getParameter("idSong")));
                    String idPlaylist = String.valueOf(this.actualPlaylist.getId());
                    if (song != null) {
                        this.actualPlaylist = null;
                        facade.removeSongOfDB(song);
                    }
                    if (request.getParameter("idPlaylist") != null) {
                        idPlaylist = request.getParameter("idPlaylist");
                    }
                    this.actualPlaylist = facade.findPlaylist(String.valueOf(idPlaylist));
                    request.setAttribute("user", this.actualUser);
                    List<Artist> allArtists = facade.getAllArtists();
                    request.setAttribute("allArtists", allArtists);
                    request.getRequestDispatcher("addSong.jsp").forward(request, response);
                    break;
                }

                case "Supprimer Tout": {
                    facade.deleteAll();
                    request.getRequestDispatcher("connecInscrip.jsp").forward(request, response);
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
