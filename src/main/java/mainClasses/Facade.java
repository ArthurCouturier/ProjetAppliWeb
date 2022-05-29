package mainClasses;

import javax.ejb.Singleton;
import javax.persistence.*;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

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
            playlist.setUser(user);
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
        transac.begin();
        Playlist playlist = new Playlist(name);
        playlist.setUser(user);
        em.persist(playlist);
        user.addPlaylist(playlist);
        em.merge(user);
        transac.commit();
    }

    public Playlist addSongPlaylist(String idSong, Playlist playlist) {
        transac.begin();
        Playlist playlist1 = em.find(Playlist.class,playlist.getId());
        Song son = em.find(Song.class,Integer.parseInt(idSong));
        playlist1.addSong(son);
        em.merge(playlist1);
        transac.commit();
        return playlist1;
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

    public Song addSong(String name, String nameAlbum, String urlvideo) {
        Album album = null;
        if (nameAlbum != null){
            TypedQuery<Album> reqAlbum = (TypedQuery<Album>) em.createNativeQuery("select album from Album album where album.name = :nameAlbum ", Album.class).setParameter("nameAlbum",nameAlbum);
            if (reqAlbum.getResultList().isEmpty()) {
                album = new Album(nameAlbum, null, null);
            } else {
                album = reqAlbum.getResultList().get(0);
            }
        }

        Song song = new Song(name, album, urlvideo);
        em.persist(song);
        return song;
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
            //TypedQuery<Playlist> reqPlaylist = (TypedQuery<Playlist>) em.createQuery("select playlists from Playlist playlists JOIN playlists.user user where user.pseudo = :pseudo ", Playlist.class).setParameter("pseudo",pseudo);
            // List<Playlist> playlist2 = reqPlaylist.getResultList();
            //System.out.println("Test" + playlist2);
            return user;
        } else {
            System.out.println("correct user incorrect password");
            return null;
        }
    }


    public Song findSong(String idSong) {
        int idSong1 = Integer.parseInt(idSong);
        Song son = em.find(Song.class, idSong1);
        return son;
    }

    public Artist findArtistByName(String artistName) {
        TypedQuery<Artist> reqArtist = (TypedQuery<Artist>) em.createQuery("select artist from Artist artist where artist.name = :pseudo ", Artist.class).setParameter("pseudo",artistName);
        Artist artist = reqArtist.getSingleResult();
        if (artist == null) {
            artist = new Artist(artistName);
        }
        return artist;
    }

    public Album findAlbumOfArtist(String albumName, String artistName) {
        Artist artist = findArtistByName(artistName);
        for (Album album: artist.getAlbums()) {
            if (album.getName() == albumName) {
                return album;
            }
        }
        return new Album(albumName, artist, null);
    }
    public Playlist findPlaylist(String idPlaylist) {
        int idPlaylist1 = Integer.parseInt(idPlaylist);
        Playlist playlist = em.find(Playlist.class, idPlaylist1);
        return playlist;
    }

    public void changePlaylistName(Playlist playlist, String newName) {
        transac.begin();
        Playlist playlistmodif = em.find(Playlist.class,playlist.getId());
        playlistmodif.setName(newName);
        em.merge(playlistmodif);
        transac.commit();
    }

    public User removePlaylist(Playlist playlist,User user){
        transac.begin();
        User usermodif = em.find(User.class,user.getId());
        Playlist playlistsuppr = em.find(Playlist.class,playlist.getId());
        playlistsuppr.setUser(null);
        usermodif.removePlaylist(playlistsuppr);
        em.remove(playlistsuppr);
        em.merge(usermodif);
        transac.commit();
        return usermodif;
    }

    public Playlist removeSong(Playlist playlist,String idSong){
        transac.begin();
        Playlist playlist1 = em.find(Playlist.class,playlist.getId());
        Song son = em.find(Song.class,Integer.parseInt(idSong));
        playlist1.removeSong(son);
        em.merge(playlist1);
        transac.commit();
        return playlist1;
    }
}
