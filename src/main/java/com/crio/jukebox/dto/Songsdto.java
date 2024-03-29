package com.crio.jukebox.dto;

public class Songsdto{
    private final String songName;
    private final String albumName;
    private final String artistName;

    public Songsdto(String songName, String albumName, String artistName){
        this.songName = songName;
        this.albumName = albumName;
        this.artistName = artistName;
    } 

    public String getSongName(){
        return songName;
    }

    public String getAlbumName(){
        return albumName;
    }

    public String getArtistName(){
        return artistName;
    }

    @Override
    public String toString(){
        return "Current Song Playing" + "\n" +
         "Song - " + songName + "\n" +
         "Album - " + albumName + "\n" +
         "Artists - " + artistName;
    }
}