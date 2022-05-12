class Artiste {
    constructor(name, id) {
        this.name = name;
        this.id = id;
        this.albums = [];
    }

    addAlbum(album) {
        this.albums.push(album);
    }
}