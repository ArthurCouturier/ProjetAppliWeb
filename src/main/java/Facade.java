package main.java;

import main.java.mainClasses.*;

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

    private Collection<User> users = new ArrayList<User>();
    private Collection<Label> labels = new ArrayList<Label>();
    private Collection<Artist> artists = new ArrayList<Artist>();
    private Collection<Playlist> playlists = new ArrayList<Playlist>();
    private Collection<Album> albums = new ArrayList<Album>();
    private Collection<Song> songs = new ArrayList<Song>();

    //TypedQuery<User> req = em.createQuery();
    //private Collection<User> users = req.getResultList();

    public Facade(){
        em.persist(users);
        em.persist(labels);
        em.persist(artists);
        em.persist(playlists);
        em.persist(albums);
        em.persist(songs);
    }

    public void addUser(String pseudo, String email, String password) {
        User user = new User(pseudo, email, password);
        Playlist playlist = new Playlist("Bibliotheque");
        user.addPlaylist(playlist);
        this.playlists.add(playlist);
        users.add(user);
    }

    public void addLabel(String name) {
        Label label = new Label(name);
        this.labels.add(label);
    }

    public void addArtist(String name) {
        Artist artist = new Artist(name);
        this.artists.add(artist);
    }

    public void addPlaylist(String name, String nameUser) {
        Playlist playlist = new Playlist(name);
        this.playlists.add(playlist);
        TypedQuery<User> req = (TypedQuery<User>) em.createNativeQuery("SELECT u FROM User u WHERE u.name LIKE : " + nameUser, User.class)
                .setMaxResults(1);
        User user = req.getSingleResult();
        user.addPlaylist(playlist);
    }

    public void addAlbum(String name, String nameArtist, String nameLabel) {
        TypedQuery<Artist> reqArtist = (TypedQuery<Artist>) em.createNativeQuery("SELECT a FROM Artist a WHERE a.name LIKE : " + nameArtist, Artist.class)
                .setMaxResults(1);
        TypedQuery<Label> reqLabel = (TypedQuery<Label>) em.createNativeQuery("SELECT l FROM Label l WHERE l.name LIKE : " + nameLabel, Label.class)
                .setMaxResults(1);
        Artist artist = reqArtist.getSingleResult();
        Label label = reqLabel.getSingleResult();
        Album album = new Album(name, artist, label);
        this.albums.add(album);
    }

    public void addSong(String name, String nameAlbum) {
        TypedQuery<Album> reqAlbum = (TypedQuery<Album>) em.createNativeQuery("SELECT a FROM Album a WHERE a.name LIKE : " + nameAlbum, Album.class)
                .setMaxResults(1);
        Album album = reqAlbum.getSingleResult();
        Song song = new Song(name, album);
        this.songs.add(song);
    }
}
