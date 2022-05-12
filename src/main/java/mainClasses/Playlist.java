package mainClasses;

import java.util.HashMap;
import java.util.Map;

public class Playlist {

    private int idS = 0;
    private int id;
    private String name;
    private Map<Integer, Song> songs = new HashMap<Integer, Song>();

    public Playlist(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public void addSong(Song s) {
        this.songs.put(idS, s); idS++;
    }

    public void removeSong(int id) {
        if (id<this.songs.size()) {
            this.songs.remove(id);
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<Integer, Song> getSongs() {
        return songs;
    }
}
