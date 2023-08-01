package com.crio.jukebox.repo;

import com.crio.jukebox.entities.Playlist;

public interface IPlaylistRepository extends CRUDRepository<Playlist,String>{
    public Playlist findPlaylistById(String id);
    public void replacePlaylist(Playlist playlist);
}