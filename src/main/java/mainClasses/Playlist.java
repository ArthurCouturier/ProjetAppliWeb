package main.java.mainClasses;

import javax.persistence.*;
import java.util.Collection;

public class Playlist {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String name;

    @ManyToMany
    private Collection<Song> songs;

    public Playlist( String name) {
        this.name = name;
    }

    public void addSong(Song s) {
        this.songs.add(s);
    }

    public void removeSong(Song s) {
            this.songs.remove(s);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Collection<Song> getSongs() {
        return songs;
    }

}
