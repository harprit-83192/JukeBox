package com.crio.jukebox.repo;

import java.util.List;
import com.crio.jukebox.entities.Playlist;
import com.crio.jukebox.entities.Songs;
import com.crio.jukebox.entities.UserPlaylist;

public interface IUserPlaylistRepository extends CRUDRepository<UserPlaylist,String>{
    public List<Playlist> findPlaylists(String id);
    public Playlist createPlaylist(Playlist entity);
    public void deletePlaylist(String userId, String PlaylistId);
    public Playlist addSongstoPlaylist(String userId, String PlaylistId, List<Songs> songs);
    public Playlist RemoveSongsFromPlaylist(String userId, String PlaylistId, List<Songs> songs);
}    
