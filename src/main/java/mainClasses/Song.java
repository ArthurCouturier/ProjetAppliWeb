package mainClasses;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

@Entity
public class Song implements Serializable {

    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private int id;

    private String name;

    @ManyToOne(cascade = CascadeType.REMOVE)
    private Album album;

    private String url;
    @ManyToMany(cascade = CascadeType.REMOVE)
    private Collection<Artist> artists;

    public Song(String name, Album album, String url) {
        this.name = name;
        this.album = album;
        if (album != null) {
            album.addSong(this);
            this.artists.add(album.getArtist());
        }
        this.url = url;
    }

    public Song() {

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

    public String getUrl() {
        return this.url;
    }

    public int getId() {
        return this.id;
    }
}
