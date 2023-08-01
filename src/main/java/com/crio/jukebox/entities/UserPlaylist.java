package com.crio.jukebox.entities;

import java.util.List;

public class UserPlaylist extends BaseEntity{
    // private final User user;
    private final List<Playlist> playlist;

    public UserPlaylist(String userId, List<Playlist> playlist){
        this.id = userId;
        // this.user = user;
        this.playlist = playlist;
    }

    // public User getUser(){
    //     return user;
    // }

    public List<Playlist> getPlaylist(){
        return playlist;
    }

    @Override
    public String toString(){
        return "UserPlaylist [ id=" + id + ", playlists=" + playlist + " ]";
    }
}
