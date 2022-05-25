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
import java.util.List;
import java.util.Map;

@Singleton
public class Facade {

    private  EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
    private EntityManager em = entityManagerFactory.createEntityManager();

    private EntityTransaction transac = em.getTransaction();

    public Facade(){
    }

    public void addUser(String pseudo, String email, String password) throws PseudoInvalidException {
        TypedQuery<User> reqUser = (TypedQuery<User>) em.createQuery("select user from User user where user.pseudo = :pseudo ", User.class).setParameter("pseudo",pseudo);
        if (!reqUser.getResultList().isEmpty()) {
            throw new PseudoInvalidException();
        } else {
            transac.begin();
            User user = new User(pseudo, email, password);
            Playlist playlist = new Playlist("Bibliotheque");
            em.persist(playlist);
            user.addPlaylist(playlist);
            System.out.println("Facade affichage nb playlists en haut 1 "+user.getPlaylists().size());
            em.persist(user);
            System.out.println("Facade affichage nb playlists en haut 2 "+user.getPlaylists().size());
            transac.commit();
        }
    }

    public void addLabel(String name) {
        Label label = new Label(name);
        em.persist(label);
    }

    public void addArtist(String name) {
        Artist artist = new Artist(name);
        em.persist(artist);
    }

    public void addPlaylist(String name, User user) {
        Playlist playlist = new Playlist(name);
        em.persist(playlist);
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

    public User findUser(String pseudo, String password) {
        // In order to destroy our database, comment rest of this code and add a return null; statement at the end
        /*transac.begin();
        em.createQuery("delete from User user").executeUpdate();
        transac.commit();*/
        TypedQuery<User> reqUser = (TypedQuery<User>) em.createQuery("select user from User user where user.pseudo = :pseudo ", User.class).setParameter("pseudo",pseudo);
        User user = reqUser.getResultList().get(0);
        if (user == null) {
            System.out.println("incorrect user");
            return null;
        } else if (user.getPassword().equals(password)){
            System.out.println("correct user correct password");
            TypedQuery<Playlist> reqPlaylist = (TypedQuery<Playlist>) em.createQuery("select user.playlists from User user where user.pseudo = :pseudo ", Playlist.class).setParameter("pseudo",pseudo);
            user.setPlaylists(reqPlaylist.getResultList());
            return user;
        } else {
            System.out.println("correct user incorrect password");
            return null;
        }
    }
}
