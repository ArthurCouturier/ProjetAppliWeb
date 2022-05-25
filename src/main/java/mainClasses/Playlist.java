package mainClasses;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class Playlist {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String name;


    @ManyToOne
    private User user;

    @ManyToMany
    private Collection<Song> songs;

    public Playlist(String name) {
        this.name = name;
    }

    public Playlist() {
        this.name = "Bibliotheque";
    }

    public void addSong(Song s) {
        this.songs.add(s);
    }

    public void removeSong(Song s) {
            this.songs.remove(s);
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return this.id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Collection<Song> getSongs() {
        return songs;
    }

}
