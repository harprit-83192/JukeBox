package com.crio.jukebox.entities;

public class Artist extends BaseEntity{
    public final String name;

    public Artist(String name, String id){
        this.name = name;
        this.id = id;
    }

    public String getName(){
        return name;
    }

    @Override
    public String toString(){
        return "Artist [ id=" + id + ", name=" + name + "]";
    }
}
