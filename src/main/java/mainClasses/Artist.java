package mainClasses;

import javax.persistence.*;
import java.util.Collection;

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
}
