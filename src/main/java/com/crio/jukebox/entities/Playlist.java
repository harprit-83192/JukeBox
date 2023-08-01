package com.crio.jukebox.entities;

import java.util.List;

public class Playlist extends BaseEntity{
    private final String name;
    private final List<Songs> songs;

    public Playlist(String id, String name, List<Songs> songs){
        this.name = name;
        this.id = id;
        this.songs = songs;
    }

    public String getName(){
        return name;
    }

    public List<Songs> getSongs(){
        return songs;
    }

    @Override
    public String toString(){
        return "Playlist ID - " + id;
    }
}