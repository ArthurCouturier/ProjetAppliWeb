package main.java.mainClasses;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class Label {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String name;

    @OneToMany
    private Collection<Album> albums;
    
    public Label(String name) {
        this.name = name;
    }

    public void addAlbum(Album a) {
        albums.add(a);
    }

    public String getName() {
        return this.name;
    }
}
