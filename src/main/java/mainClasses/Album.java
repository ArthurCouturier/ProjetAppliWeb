package main.java.mainClasses;

import java.util.HashMap;
import java.util.Map;

public class Album {

    private int idS;
    private int id;
    private Artist artist;
    private Label label;
    private Map<Integer, Song> songs = new HashMap<Integer, Song>();

    public Album(int id, Artist artist, Label label) {
        this.id = id;
        this.artist = artist;
        this.label = label;
    }

    public void addSong(Song s) {
        this.songs.put(idS, s); idS++;
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

    public Map<Integer, Song> getSongs() {
        return songs;
    }

    public String toString() {
        return "Album{" + "id=" + id + ", artist=" + artist + ", label=" + label + ", songs=" + songs + '}';
    }
}
