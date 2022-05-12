package main.java.mainClasses;

import javax.persistence.*;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String pseudo;
    private String email;
    private String password;

    @OneToMany
    private Collection<Playlist> playlists;

    public User(String ps, String e, String pw) {
        this.pseudo = ps;
        this.email = e;
        this.password = pw;
        this.playlists.add(new Playlist("Bibliotheque"));
    }

    public String getPseudo() { return pseudo; }

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

    public void addPlaylist(Playlist p) {
        this.playlists.add(p);
    }

    public void removePlaylist(Playlist p) {
        this.playlists.remove(p);
    }
}
