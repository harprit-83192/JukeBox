package com.crio.jukebox.services;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import com.crio.jukebox.entities.Album;
import com.crio.jukebox.entities.Songs;
import com.crio.jukebox.repo.IAlbumRepository;
import com.crio.jukebox.repo.ISongsRepository;

public class SongsService implements ISongsService{

    ISongsRepository songsRepository;
    IAlbumRepository albumRepository;

    public SongsService(ISongsRepository songsRepository, IAlbumRepository albumRepository){
        this.songsRepository = songsRepository;
        this.albumRepository = albumRepository;
    }
    
    public void LoadData(String filename){
        BufferedReader reader;
        Songs song;
        Album album;

        try {
            reader = new BufferedReader(new FileReader(filename));
            String line = reader.readLine();
            while(line != null){
                List<String> tokens = Arrays.asList(line.split(","));
                String songId = tokens.get(0);
                String songName = tokens.get(1);
                String genre = tokens.get(2);
                String albumName = tokens.get(3);
                // String albumOwner = tokens.get(4);
                List<String> artists = Arrays.asList(tokens.get(5).split("#"));
                song = new Songs(songName, songId, genre, albumName, artists);
                // song.setAlbum(albumName);
                Songs saveSong = songsRepository.save(song);
                album = albumRepository.findByName(albumName);
                if(album == null){
                    album = new Album(albumName, null, Arrays.asList(saveSong));
                }
                albumRepository.save(album);
                line = reader.readLine();
            }
            reader.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
