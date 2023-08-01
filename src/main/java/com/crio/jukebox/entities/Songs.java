package com.crio.jukebox.entities;

import java.util.List;

public class Songs extends BaseEntity{
    private final String name;
    private String album;
    private final List<String> artists;
    private final String genre;

    public Songs(String name, String id, String genre, String album, List<String> artists){
        this.name = name;
        this.id = id;
        this.artists = artists;
        this.genre = genre;
        this.album = album;
    }

    public String getName(){
        return name;
    }

    public String getGenre(){
        return genre;
    }

    public List<String> getArtist(){
        return artists;
    }

    public String getAlbum(){
        return album;
    }
    // public void setAlbum(String album){
    //     this.album = album;
    // }

    @Override
    public String toString(){
        return "Song [ id=" + id + ", name=" + name + ", genre=" + genre + ", artist=" + artists + ", album=" + album + "]";
    }
}
