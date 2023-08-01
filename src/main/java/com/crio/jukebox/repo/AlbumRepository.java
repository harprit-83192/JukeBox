package com.crio.jukebox.repo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import com.crio.jukebox.entities.Album;

public class AlbumRepository implements IAlbumRepository {
    private final Map<String,Album> albumMap;
    private final Map<String,String> albumMapbyName;
    private int count = 0;

    public AlbumRepository(){
        albumMap = new HashMap<String,Album>();
        albumMapbyName = new HashMap<String,String>();
    }

    // public AlbumRepository(Map<String, Album> albumMap, Map<String,String> albumMapbyName) {
    //     this.albumMap = albumMap;
    //     this.albumMapbyName = albumMapbyName;
    //     this.autoIncrement = albumMap.size();
    // }

    @Override
    public Album save(Album album) {
        if( album.getId() == null ){
            count++;
            Album newAlbum = new Album(album.getName(), Integer.toString(count), album.getSongs());
            albumMap.put(newAlbum.getId(),newAlbum);
            albumMapbyName.put(newAlbum.getName(),newAlbum.getId());
            return newAlbum;
        }
        albumMap.put(album.getId(),album);
        albumMapbyName.put(album.getName(), album.getId());
        return album;
    }

    @Override
    public List<Album> findAll() {
        return albumMap.values().stream().collect(Collectors.toList());
    }

    @Override
    public Optional<Album> findById(String id) {
        return Optional.of(albumMap.get(id));
    }

    @Override
    public boolean existsById(String id) {
        return false;
    }

    @Override
    public void delete(Album entity) {
    }

    @Override
    public void deleteById(String id) {    
    }

    @Override
    public long count() {
        return 0;
    }

    public Album findByName(String name){
        if(albumMapbyName.containsKey(name)){
            return albumMap.get(albumMapbyName.get(name));
        }
        return null;
    }
}
