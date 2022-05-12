package mainClasses;

import java.util.HashMap;
import java.util.Map;

public class User {

    private int idP;
    private int id;
    private String pseudo;
    private String email;
    private String password;
    private Map<Integer, Playlist> playlists;

    public User(int id, String ps, String e, String pw) {
        this.id = id;
        this.pseudo = ps;
        this.email = e;
        this.password =  pw;
        this.playlists = new HashMap<Integer, Playlist>();
        this.playlists.put(idP, new Playlist(idP, "Bibliotheque")); idP++;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Map<Integer, Playlist> getPlaylists() {
        return playlists;
    }

    public void addPlaylist(Playlist p) {
        this.playlists.put(idP, p); idP++;
    }

    public void removePlaylist(Playlist p) {
        this.playlists.remove(p.getId());
    }
}
