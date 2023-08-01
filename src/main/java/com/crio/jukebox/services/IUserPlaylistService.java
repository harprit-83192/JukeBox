package com.crio.jukebox.services;

import java.util.List;
import com.crio.jukebox.dto.Songsdto;
import com.crio.jukebox.entities.Playlist;
import com.crio.jukebox.entities.SongsOrder;

public interface IUserPlaylistService {
    public Playlist createPlaylist(String userId, String playlistName, List<String> songs);
    public void deletePlaylist(String userID, String playlistId);
    public Playlist addSongs(String userId, String playlistId, List<String> songs);
    public Playlist deleteSongs(String userId, String playlistId, List<String> songs);
    public Songsdto playSongbyId(String userId, String songId);
    public Songsdto playSongbyOrder(String userId, SongsOrder songsOrder);
    public Songsdto playPlaylist(String userId, String playlistId);
}
