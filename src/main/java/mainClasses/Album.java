package main.java.mainClasses;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class Album {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String name;

    @ManyToOne
    private Artist artist;

    @ManyToOne
    private Label label;

    @OneToMany
    private Collection<Song> songs;

    public Album(String name, Artist artist, Label label) {
        this.artist = artist;
        this.label = label;
        label.addAlbum(this);
        artist.addAlbum(this);
    }

    public void addSong(Song s) {
        this.songs.add(s);
    }

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
