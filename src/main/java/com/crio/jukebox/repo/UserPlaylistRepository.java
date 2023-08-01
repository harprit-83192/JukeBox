package com.crio.jukebox.repo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import com.crio.jukebox.entities.Playlist;
import com.crio.jukebox.entities.Songs;
import com.crio.jukebox.entities.UserPlaylist;

public class UserPlaylistRepository implements IUserPlaylistRepository {
    
    private final IPlaylistRepository playlistRepository;
    private final Map<String, List<Playlist>> userToPlaylistMap = new HashMap<>();

    public UserPlaylistRepository(IPlaylistRepository playlistRepository){
        this.playlistRepository = playlistRepository;
    }

    @Override
    public UserPlaylist save(UserPlaylist userPlaylist) {
        if(!userToPlaylistMap.containsKey(userPlaylist.getId())){
            userToPlaylistMap.put(userPlaylist.getId(), userPlaylist.getPlaylist());
        }
        return null;
    }

    @Override
    public List<UserPlaylist> findAll() {
        return null;
    }

    @Override
    public Optional<UserPlaylist> findById(String id) {
        return null;
    }

    @Override
    public boolean existsById(String id) {
        return false;
    }

    @Override
    public void delete(UserPlaylist entity) {
    }

    @Override
    public void deleteById(String id) {    
    }

    @Override
    public long count() {
        return 0;
    }

    public List<Playlist> findPlaylists(String userid){
        if(userToPlaylistMap.containsKey(userid)){
            return userToPlaylistMap.get(userid);
        }
        return null;
    }

    public Playlist createPlaylist(Playlist newPlaylist){
        if( newPlaylist.getId() == null ){
            Playlist p = new Playlist(null, newPlaylist.getName(), newPlaylist.getSongs());
            Playlist c =playlistRepository.save(p);
            return c;
        }
        return null;
    }

    public void deletePlaylist(String userId, String PlaylistId){
        if(userToPlaylistMap.containsKey(userId)){
            List<Playlist> listofPlaylists = userToPlaylistMap.get(userId);
            if(!listofPlaylists.isEmpty()){
                listofPlaylists.removeIf(playlist -> playlist.getId().equals(PlaylistId));
            }
        }
    }

    public Playlist addSongstoPlaylist(String userId, String PlaylistId, List<Songs> songs){
        if(userToPlaylistMap.containsKey(userId)){
            List<Playlist> list = userToPlaylistMap.get(userId);
            if(!list.isEmpty()){
                for(Playlist playlist:list){
                    if(playlist.getId().equals(PlaylistId)){
                        List<Songs> listOfSongs = playlist.getSongs();
                        for(Songs song:songs){
                            listOfSongs.add(song);
                        }
                        Playlist modifiedPlaylist = new Playlist(PlaylistId, playlist.getName(), listOfSongs);
                        playlistRepository.replacePlaylist(modifiedPlaylist);
                        return modifiedPlaylist;
                    }
                }
            }
        }
        return null;
    }

    public Playlist RemoveSongsFromPlaylist(String userId, String PlaylistId, List<Songs> songs){
        if(userToPlaylistMap.containsKey(userId)){
            List<Playlist> list = userToPlaylistMap.get(userId);
            if(!list.isEmpty()){
                for(Playlist playlist:list){
                    if(playlist.getId().equals(PlaylistId)){
                        List<Songs> listOfSongs = playlist.getSongs();
                        for(Songs song:songs){
                            listOfSongs.removeIf(remsong -> remsong.getId().equals(song.getId()));
                        }
                        Playlist modifiedPlaylist = new Playlist(PlaylistId, playlist.getName(), listOfSongs);
                        playlistRepository.replacePlaylist(modifiedPlaylist);
                        return modifiedPlaylist;
                    }
                }
            }
        }
        return null;
    }
}
