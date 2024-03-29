package mainClasses;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String pseudo;
    private String email;
    private String password;

    @OneToMany
    private Collection<Playlist> playlists = new ArrayList<Playlist>() ;

    public User(String pseudo, String email, String password) {
        this.pseudo = pseudo;
        this.email = email;
        this.password = password;
    }

    public User() {

    }

    public int getId() {
        return id;
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

    public Collection<Playlist> getPlaylists() {
      return playlists;
    }

    public void setPlaylists(Collection<Playlist> playlists) {
      this.playlists = playlists;
    }

    public void addPlaylist(Playlist p) {
      this.playlists.add(p);
    }

    public void removePlaylist(Playlist p) {
        this.playlists.remove(p);
    }
}
