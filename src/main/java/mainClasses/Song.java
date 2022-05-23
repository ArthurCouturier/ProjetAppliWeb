package mainClasses;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class Song {

    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private int id;

    private String name;

    @ManyToOne
    private Album album;
    @ManyToMany
    private Collection<Artist> artists;

    public Song(String name, Album album) {
        this.name = name;
        this.album = album;
        album.addSong(this);
        this.artists.add(album.getArtist());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    public Collection<Artist> getArtist() {
        return this.artists;
    }

}
