package mainClasses;

import org.hibernate.jpa.boot.internal.EntityManagerFactoryBuilderImpl;

import javax.ejb.Singleton;
import javax.persistence.*;

@Singleton
public class Facade {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("BaseDeDonnees");
    @PersistenceContext(name = "BaseDeDonnee")
    EntityManager em = emf.createEntityManager();

    //TypedQuery<User> req = em.createQuery();
    //private Collection<User> users = req.getResultList();

    public Facade(){
    }

    public void addUser(String pseudo, String email, String password) {
        System.out.println("TestFacade1/5");
        User user = new User(pseudo, email, password);
        System.out.println("TestFacade2/5");
        Playlist playlist = new Playlist("Bibliotheque");
        System.out.println("TestFacade3/5");
        user.addPlaylist(playlist);
        System.out.println("TestFacade4/5");
        System.out.println(em);
        System.out.println("TestFacade4.1/5");
        System.out.println(em.toString());
        System.out.println("TestFacade4.5/5");
        em.persist(user);
        System.out.println("TestFacade5/5");
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

    public User findUser(String pseudo, String password) {
        System.out.println("Test11");
        TypedQuery<User> reqUser = (TypedQuery<User>) em.createNativeQuery("SELECT a FROM User a WHERE a.pseudo LIKE : " + pseudo, User.class)
                .setMaxResults(1);
        System.out.println(reqUser);
        System.out.println("Test12");
        User user = reqUser.getSingleResult();
        System.out.println("Test13");
        return user;
        }
    }

