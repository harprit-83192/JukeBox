package com.crio.jukebox.repo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import com.crio.jukebox.entities.Playlist;

public class PlaylistRepository implements IPlaylistRepository{
    private final Map<String, Playlist> playlistMap;
    private int count = 0;

    public PlaylistRepository(){
        this.playlistMap = new HashMap<>();
        this.count = playlistMap.size();
    }

    @Override
    public Playlist save(Playlist playlist) {
        if(playlist.getId() == null){
            count++;
            Playlist newplaylist = new Playlist(Integer.toString(count), playlist.getName(), playlist.getSongs());
            playlistMap.put(newplaylist.getId(), newplaylist);
            return newplaylist;
        }
        playlistMap.put(playlist.getId(),playlist);
        return playlist;
    }

    @Override
    public List<Playlist> findAll() {
        return playlistMap.values().stream().collect(Collectors.toList());
    }

    @Override
    public Optional<Playlist> findById(String id) {
        return null;
    }

    @Override
    public boolean existsById(String id) {
        return false;
    }

    @Override
    public void delete(Playlist entity) {
    }

    @Override
    public void deleteById(String id) {   
    }

    @Override
    public long count() {
        return 0;
    }

    public Playlist findPlaylistById(String id){
        return playlistMap.get(id);
    }

    public void replacePlaylist(Playlist playlist){
        playlistMap.remove(playlist.getId());
        playlistMap.put(playlist.getId(), playlist);
    }
    
}
