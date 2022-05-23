package main.java;

import mainClasses.*;

import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.Collection;

@Singleton
public class Facade {

    @PersistenceContext
    private EntityManager em;

    //TypedQuery<User> req = em.createQuery();
    //private Collection<User> users = req.getResultList();

    public Facade(){
    }

    public void addUser(String pseudo, String email, String password) {
        User user = new User(pseudo, email, password);
        Playlist playlist = new Playlist("Bibliotheque");
        user.addPlaylist(playlist);
        em.persist(user);
    }

    public void addLabel(String name) {
        Label label = new Label(name);
        em.persist(label);
    }

    public void addArtist(String name) {
        Artist artist = new Artist(name);
        em.persist(artist);
    }

    public void addPlaylist(String name, String nameUser) {
        Playlist playlist = new Playlist(name);
        TypedQuery<User> req = (TypedQuery<User>) em.createNativeQuery("SELECT u FROM User u WHERE u.name LIKE : " + nameUser, User.class).setMaxResults(1);
        User user = req.getSingleResult();
        user.addPlaylist(playlist);
        em.merge(user);
    }

    public void addAlbum(String name, String nameArtist, String nameLabel) {
        TypedQuery<Artist> reqArtist = (TypedQuery<Artist>) em.createNativeQuery("SELECT a FROM Artist a WHERE a.name LIKE : " + nameArtist, Artist.class)
                .setMaxResults(1);
        TypedQuery<Label> reqLabel = (TypedQuery<Label>) em.createNativeQuery("SELECT l FROM Label l WHERE l.name LIKE : " + nameLabel, Label.class)
                .setMaxResults(1);
        Artist artist = reqArtist.getSingleResult();
        Label label = reqLabel.getSingleResult();
        Album album = new Album(name, artist, label);
        em.persist(album);
    }

    public void addSong(String name, String nameAlbum) {
        TypedQuery<Album> reqAlbum = (TypedQuery<Album>) em.createNativeQuery("SELECT a FROM Album a WHERE a.name LIKE : " + nameAlbum, Album.class)
                .setMaxResults(1);
        Album album = reqAlbum.getSingleResult();
        Song song = new Song(name, album);
        em.persist(song);
    }
}
