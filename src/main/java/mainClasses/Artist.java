package mainClasses;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

@Entity
public class Artist {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String name;

    @OneToMany
    private Collection<Album> albums;

    public Artist(String name)  {
        this.name = name;
        this.albums = new ArrayList<Album>();
    }

    public Artist() {
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

    public void addAlbum(Album album) {
        this.albums.add(album);
    }

    public Collection<Album> getAlbums() {
        return this.albums;
    }

    public void setAlbums(Collection<Album> albums) {
        this.albums = albums;
    }
}
