package mainClasses;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
public class Album  {

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
        this.name = name;
        this.artist = artist;
        this.label = label;
        if (label != null) {
            label.addAlbum(this);
        }
        this.songs = new ArrayList<Song>();
        artist.addAlbum(this);
    }

    public Album() {

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

    public String getName() {
        return this.name;
    }

    public void removeSong(Song s) {
        for (Song song: this.songs) {
            if (song.getName().equals(s.getName())) {
                this.songs.remove(song);
            }
        }
    }

}
