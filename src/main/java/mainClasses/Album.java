package main.java.mainClasses;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class Album {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne
    private Artist artist;

    @ManyToOne
    private Label label;

    @OneToMany
    private Collection<Song> songs;

    public Album(int id, Artist artist, Label label) {
        this.id = id;
        this.artist = artist;
        this.label = label;
    }

    public void addSong(Song s) {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Artist getArtist() {
        return artist;
    }

    public Label getLabel() {
        return label;
    }

    public Collection<Song> getSongs() {
        return songs;
    }

    public String toString() {
        return "Album{" + "id=" + id + ", artist=" + artist + ", label=" + label + ", songs=" + songs + '}';
    }
}
