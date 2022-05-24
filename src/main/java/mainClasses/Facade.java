package mainClasses;

import javax.ejb.Singleton;
import javax.persistence.*;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.metamodel.Metamodel;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

@Singleton
public class Facade {

    private  EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
    private EntityManager em = entityManagerFactory.createEntityManager();



    //TypedQuery<User> req = em.createQuery();
    //private Collection<User> users = req.getResultList();

    public Facade(){
    }

    public void addUser(String pseudo, String email, String password) {
        System.out.println("Test");
        User user = new User(pseudo, email, password);
        System.out.println("Test237");
        Playlist playlist = new Playlist("Bibliotheque");
        System.out.println("Test238");
        user.addPlaylist(playlist);
        System.out.println("Test236");
        em.persist(user);
        System.out.println("Test235");
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
        //user.addPlaylist(playlist);
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

    public User findUser(String pseudo, String password) {
        System.out.println("Test11");
        TypedQuery<User> reqUser = (TypedQuery<User>) em.createNativeQuery("SELECT a FROM User a WHERE a.pseudo LIKE : " + pseudo, User.class)
                .setMaxResults(1);
        System.out.println(reqUser);
        System.out.println("Test12");
        User user = reqUser.getSingleResult();
        System.out.println("Test13");
        System.out.println(user.getPseudo());
        return user;
        }
    }

