package com.crio.jukebox.entities;

import java.util.List;

public class Album extends BaseEntity {
    private final String name;
    private final List<Songs> songs;

    public Album(String name, String id, List<Songs> songs){
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
        return "Album [ id=" + id + ", name=" + name + ", songs=" + songs + " ]";
    }
}
