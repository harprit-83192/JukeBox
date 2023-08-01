package com.crio.jukebox.services;

import java.util.ArrayList;
import java.util.List;
import com.crio.jukebox.dto.Songsdto;
import com.crio.jukebox.entities.Playlist;
import com.crio.jukebox.entities.Songs;
import com.crio.jukebox.entities.SongsOrder;
import com.crio.jukebox.entities.User;
import com.crio.jukebox.entities.UserPlaylist;
import com.crio.jukebox.exception.SongNotFoundException;
import com.crio.jukebox.repo.IUserPlaylistRepository;
import com.crio.jukebox.repo.ISongsRepository;
import com.crio.jukebox.repo.IUserRepository;

public class UserPlaylistService implements IUserPlaylistService {
    IUserPlaylistRepository userPlaylistRepository;
    IUserRepository userRepository;
    ISongsRepository songsRepository;
    private int songIndex;

    public UserPlaylistService(IUserPlaylistRepository userPlaylistRepository, IUserRepository userRepository, ISongsRepository songsRepository){
        this.userPlaylistRepository = userPlaylistRepository;
        this.userRepository = userRepository;
        this.songsRepository = songsRepository;
    }

    public Playlist createPlaylist(String userId, String playlistName, List<String> songs){
        User user = userRepository.findUserById(userId);
        if(user != null){
            List<Songs> listofSongs = new ArrayList<>();
            for(String songId:songs){
                Songs song = songsRepository.findSongById(songId);
                if(song != null){
                    listofSongs.add(song);
                }else throw new SongNotFoundException("Some Requested Songs Not Available. Please try again.");
            }
            Playlist playlist = new Playlist(null, playlistName, listofSongs);
            Playlist savePlaylist = userPlaylistRepository.createPlaylist(playlist);
            List<Playlist> listofPlaylists = new ArrayList<>();
            try {
                listofPlaylists.add(savePlaylist);
                UserPlaylist userPlaylist = new UserPlaylist(userId, listofPlaylists);
                userPlaylistRepository.save(userPlaylist);
            } catch (NullPointerException e) {
                e.getMessage();
            }
            return savePlaylist;
        }
        return null;
        
    }

    public void deletePlaylist(String userID, String playlistId){
        userPlaylistRepository.deletePlaylist(userID, playlistId);
    }

    public Playlist addSongs(String userId, String playlistId, List<String> songs){
        List<Songs> listofSongs = new ArrayList<>();
        for(String songId:songs){
            Songs song = songsRepository.findSongById(songId);
            listofSongs.add(song);
        }
        Playlist playlist = userPlaylistRepository.addSongstoPlaylist(userId, playlistId, listofSongs);
        return playlist;
    }

    public Playlist deleteSongs(String userId, String playlistId, List<String> songs){
        List<Songs> listofSongs = new ArrayList<>();
        for(String songId:songs){
            Songs song = songsRepository.findSongById(songId);
            listofSongs.add(song);
        }
        Playlist playlist = userPlaylistRepository.RemoveSongsFromPlaylist(userId, playlistId, listofSongs);
        return playlist;
    }
    
    public Songsdto playSongbyId(String userId, String songId){
        User user = userRepository.findUserById(userId);
        if(user != null){
            List<Playlist> listofPlaylists = userPlaylistRepository.findPlaylists(userId);
            for(Playlist playlist:listofPlaylists){
                List<Songs> listofSongs = playlist.getSongs();
                for(int i=0;i<listofSongs.size();i++){
                    if(listofSongs.get(i).getId().equals(songId)){
                        songIndex = i;
                        StringBuilder sb = new StringBuilder();
                        for(String artist:listofSongs.get(i).getArtist()){
                            sb.append(artist + ",");
                        }
                        sb.deleteCharAt(sb.length()-1);
                        Songsdto songsdto = new Songsdto(listofSongs.get(i).getName(), listofSongs.get(i).getAlbum(), sb.toString().trim());
                        return songsdto;
                    }
                }
                throw new SongNotFoundException();
            }
        }
        return null;
    }

    public Songsdto playSongbyOrder(String userId, SongsOrder songsOrder){
        if(songsOrder == SongsOrder.NEXT){
            User user = userRepository.findUserById(userId);
            if(user != null){
                List<Playlist> listofPlaylists = userPlaylistRepository.findPlaylists(userId);
                for(Playlist playlist:listofPlaylists){
                    List<Songs> listofSongs = playlist.getSongs();
                    if(songIndex == listofSongs.size()-1){
                        songIndex = 0;
                    }else{
                        songIndex++;
                    }
                    StringBuilder sb = new StringBuilder();
                    for(String artist:listofSongs.get(songIndex).getArtist()){
                        sb.append(artist + ",");
                    }
                    sb.deleteCharAt(sb.length()-1);
                    Songsdto songsdto = new Songsdto(listofSongs.get(songIndex).getName(), listofSongs.get(songIndex).getAlbum(), sb.toString().trim());
                    return songsdto;
                }
            }
        }else if(songsOrder == SongsOrder.BACK){
            User user = userRepository.findUserById(userId);
            if(user != null){
                List<Playlist> listofPlaylists = userPlaylistRepository.findPlaylists(userId);
                for(Playlist playlist:listofPlaylists){
                    List<Songs> listofSongs = playlist.getSongs();
                    if(songIndex == 0){
                        songIndex = listofSongs.size()-1;
                    }else{
                        songIndex--;
                    }
                    StringBuilder sb = new StringBuilder();
                    for(String artist:listofSongs.get(songIndex).getArtist()){
                        sb.append(artist + ",");
                    }
                    sb.deleteCharAt(sb.length()-1);
                    Songsdto songsdto = new Songsdto(listofSongs.get(songIndex).getName(), listofSongs.get(songIndex).getAlbum(), sb.toString().trim());
                    return songsdto;
                }
            }
        }
        return null;
    }

    public Songsdto playPlaylist(String userId, String playlistId){
        songIndex = 0;
        Songsdto songsdto=null;
        List<Playlist> allPlaylist = userPlaylistRepository.findPlaylists(userId);
        for(Playlist playlist:allPlaylist){
            if(playlist.getId().equals(playlistId)){
                List<Songs> song = playlist.getSongs();
                Songs currentSong = song.get(songIndex);
                StringBuilder sb = new StringBuilder();
                for(String artist:currentSong.getArtist()){
                    sb.append(artist + ",");
                }
                sb.deleteCharAt(sb.length()-1);
                songsdto = new Songsdto(currentSong.getName(), currentSong.getAlbum(), sb.toString().trim());
                // return songsdto;   
            }
        }
        return songsdto;
    }
}
