package mainClasses;

import javax.ejb.Singleton;
import javax.persistence.*;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Collection;
import java.util.List;

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

    public Playlist addPlaylistoSong(String idSong, Playlist playlist){
        transac.begin();
        Playlist playlist1 = em.find(Playlist.class,playlist.getId());
        Song song = em.find(Song.class,Integer.parseInt(idSong));
        song.addPlaylist(playlist1);
        em.merge(song);
        transac.commit();
        return playlist1;
    }

    public User findUser(String pseudo, String password) {
        // In order to destroy our database, comment rest of this code and add a return null; statement at the end
        /*transac.begin();
        em.createQuery("delete from User user").executeUpdate();
        em.createQuery("delete from Album album").executeUpdate();
        em.createQuery("delete from Artist artist").executeUpdate();
        em.createQuery("delete from Playlist playlist").executeUpdate();
        em.createQuery("delete from Song song").executeUpdate();
        em.createQuery("delete from Label label").executeUpdate();
        transac.commit();*/
        TypedQuery<User> reqUser = (TypedQuery<User>) em.createQuery("select user from User user where user.pseudo = :pseudo ", User.class).setParameter("pseudo",pseudo);
        List<User> users = reqUser.getResultList();
        if (users.isEmpty()) {
            System.out.println("incorrect user");
            return null;
        }
        User user = users.get(0);
        if (user.getPassword().equals(password)){
            System.out.println("correct user correct password");
            //TypedQuery<Playlist> reqPlaylist = (TypedQuery<Playlist>) em.createQuery("select playlists from Playlist playlists JOIN playlists.user user where user.pseudo = :pseudo ", Playlist.class).setParameter("pseudo",pseudo);
            // List<Playlist> playlist2 = reqPlaylist.getResultList();
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
        List<Artist> artists = reqArtist.getResultList();
        if (artists.isEmpty()) {
            Artist artist = new Artist(artistName);
            em.persist(artist);
            return artist;
        } else {
            return artists.get(0);
        }
    }

    public Album findAlbumOfArtist(String albumName, String artistName) {
        Artist artist = findArtistByName(artistName);
        if (artist != null && artist.getAlbums() != null) {
            for (Album album: artist.getAlbums()) {

                if (album.getName() != null && album.getName().equals(albumName)) {
                    return album;
                }
            }
        }
        Album album = new Album(albumName, artist, null);
        em.persist(album);
        return album;
    }
    public Playlist findPlaylist(String idPlaylist) {
        int idPlaylist1 = Integer.parseInt(idPlaylist);
        Playlist playlist = em.find(Playlist.class, idPlaylist1);
        return playlist;
    }

    public Song findSongById(int idSong) {
//        TypedQuery<Song> reqSong = (TypedQuery<Song>) em.createQuery("select song from Song song where song.id = :id", Song.class).setParameter("id", idSong);
//        if (!reqSong.getResultList().isEmpty()) {
//            return reqSong.getResultList().get(0);
//        }
        Song son = em.find(Song.class, idSong);

        return son;
    }

    public List<Artist> getAllArtists() {
        TypedQuery<Artist> reqArtist = (TypedQuery<Artist>) em.createQuery("select artist from Artist artist", Artist.class);
        System.out.println("Test taille :" + reqArtist.getResultList().size());
        return reqArtist.getResultList();
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

    public void deleteAll(){
        System.out.println("Test");
        List<User> users = em.createQuery("select user from User user  ").getResultList();
        List<Album> albums = em.createQuery("select album from Album album  ").getResultList();
        List<Artist> artists = em.createQuery("select artist from Artist artist  ").getResultList();
        List<Label> labels = em.createQuery("select label from Label label  ").getResultList();
        List<Playlist> playlists = em.createQuery("select playlist from Playlist playlist  ").getResultList();
        List<Song> songs = em.createQuery("select song from Song song  ").getResultList();
        transac.begin();
        System.out.println("Test1 user");
        for ( User user : users) {
            user.setPlaylists(null);
            em.merge(user);
        }
        System.out.println("Test2 user");
        System.out.println("Test1 album");
        for ( Album album : albums) {
            album.setArtist(null);
            album.setSongs(null);
            em.merge(album);
        }
        System.out.println("Test2 album");
        System.out.println("Test1 artist");
        for ( Artist artist : artists) {
            artist.setAlbums(null);
            em.merge(artist);
        }
        System.out.println("Test2 artist");
        System.out.println("Test1 label");
        for ( Label label : labels) {
            label.setAlbums(null);
            em.merge(label);
        }
        System.out.println("Test2 label");
        System.out.println("Test1 playlist");
        for ( Playlist playlist : playlists) {
            playlist.setSongs(null);
            playlist.setUser(null);
            em.merge(playlist);
        }
        System.out.println("Test2 playlist");
        System.out.println("Test1 song");
        for ( Song song : songs) {
            song.setArtists(null);
            song.setArtists(null);
            song.setPlaylists(null);
            em.merge(song);
        }
        System.out.println("Test2 song");
        transac.commit();

        System.out.println("Debut supression");
        transac.begin();
        em.createQuery("delete from User").executeUpdate();
        em.createQuery("delete from Album").executeUpdate();
        em.createQuery("delete from Artist").executeUpdate();
        em.createQuery("delete from Playlist").executeUpdate();
        em.createQuery("delete from Song").executeUpdate();
        em.createQuery("delete from Label").executeUpdate();
        transac.commit();
        System.out.println("Fin supression");
    }

    public void removeSongOfDB(Song song){
        transac.begin();
        Song songsuppr = em.find(Song.class,song.getId());
        TypedQuery<Artist> reqArtist = (TypedQuery<Artist>) em.createQuery("select artist from Artist artist", Artist.class);
        for(Artist artist : reqArtist.getResultList()){
            Artist artist1 = em.find(Artist.class, artist.getId());
            for( Album album : artist1.getAlbums()) {
                Album album1 = em.find(Album.class,album.getId());
                album1.removeSong(songsuppr);
                em.merge(album1);
            }
            em.merge(artist1);
        }

        TypedQuery<Playlist> reqPlaylist = (TypedQuery<Playlist>) em.createQuery("select playlist from Playlist playlist", Playlist.class);
        for(Playlist playlist : reqPlaylist.getResultList()){
            Playlist playlist1 = em.find(Playlist.class, playlist.getId());
            playlist1.removeSong(songsuppr);
            em.merge(playlist1);
        }
        songsuppr.setUrl(null);
        songsuppr.setArtists(null);
        songsuppr.setPlaylists(null);
        songsuppr.setAlbum(null);
        em.merge(songsuppr);
        transac.commit();

        System.out.println(songsuppr.getAlbum() +" "+ songsuppr.getPlaylists());

        transac.begin();
        songsuppr = em.find(Song.class,songsuppr.getId());
        em.remove(songsuppr);
        transac.commit();


    }
}
