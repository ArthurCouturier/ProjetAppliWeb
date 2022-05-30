package mainClasses;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

@Entity
public class Song implements Serializable {

    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private int id;

    private String name;

    @ManyToOne
    private Album album;


    @ManyToMany(cascade = CascadeType.REMOVE)
    private Collection<Playlist> playlists = new ArrayList<Playlist>() ;

    private String url;

    @ManyToMany(cascade = CascadeType.REMOVE)
    private Collection<Artist> artists = new ArrayList<Artist>() ;

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

    public void setUrl(String url) {
        this.url = url;
    }

    public void setArtists(Collection<Artist> artists) {
        this.artists = artists;
    }

    public Collection<Playlist> getPlaylists() {
        return playlists;
    }

    public void setPlaylists(Collection<Playlist> playlists) {
        this.playlists = playlists;
    }

    public void addPlaylist(Playlist playlist){
        this.playlists.add(playlist);
    }
}
