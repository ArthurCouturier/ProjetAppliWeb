package main.java.mainClasses;

import javax.persistence.*;

public class Song {

    @Id
    @GeneratedValue (Strategy = GenerationType.AUTO)
    private int id;

    private String name;

    @ManyToOne
    private Album album;
    @ManyToMany
    private Collection<Artist> artist;

    public Song(String name, Album album, Artist feat) {
        this.name = name;
        this.album = album;
        this.artist.add(album.getArtist());
        if (feat != null) {artist.add(feat);}
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

    public Album getArtist() {
        return this.artist;
    }

}
