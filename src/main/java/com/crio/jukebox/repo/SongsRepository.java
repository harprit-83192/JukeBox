package com.crio.jukebox.repo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import com.crio.jukebox.entities.Songs;

public class SongsRepository implements ISongsRepository {
    private final Map<String,Songs> songsMap;
    private int count = 0;

    public SongsRepository(){
        songsMap = new HashMap<String,Songs>();
    }

    public SongsRepository(Map<String,Songs> songsMap) {
        this.songsMap = songsMap;
        this.count = songsMap.size();
    }

    @Override
    public Songs save(Songs song) {
        if( song.getId() == null ){
            count++;
            Songs newSong = new Songs(song.getName(), Integer.toString(count), song.getGenre(), song.getAlbum(), song.getArtist());
            songsMap.put(newSong.getId(),newSong);
            return newSong;
        }
        songsMap.put(song.getId(),song);
        return song;
    }

    @Override
    public List<Songs> findAll() {
        return songsMap.values().stream().sorted((a,b) -> Integer.valueOf(a.getId())-Integer.valueOf(b.getId())).collect(Collectors.toList());
    }

    @Override
    public Optional<Songs> findById(String id) {
        return Optional.of(songsMap.get(id));
    }

    @Override
    public boolean existsById(String id) {
        return false;
    }

    @Override
    public void delete(Songs entity) {
    }

    @Override
    public void deleteById(String id) {    
    }

    @Override
    public long count() {
        return 0;
    }

    public Songs findSongById(String id){
        return songsMap.get(id);
    }

}
