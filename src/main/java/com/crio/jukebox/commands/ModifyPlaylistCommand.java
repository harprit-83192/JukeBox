package com.crio.jukebox.commands;

import java.util.ArrayList;
import java.util.List;
import com.crio.jukebox.entities.Playlist;
import com.crio.jukebox.entities.Songs;
import com.crio.jukebox.services.IUserPlaylistService;

public class ModifyPlaylistCommand implements ICommand{
    private final IUserPlaylistService playlistService;

    public ModifyPlaylistCommand(IUserPlaylistService playlistService){
        this.playlistService = playlistService;
    }

    @Override
    public void execute(List<String> tokens) {
        List<String> listofSongs = new ArrayList<>();
        for(int i=4;i<tokens.size();i++){
            listofSongs.add(tokens.get(i));
        }
        Playlist playlist;
        if(tokens.get(1).equals("ADD-SONG")){
            try {
                StringBuilder sb = new StringBuilder();
                playlist = playlistService.addSongs(tokens.get(2), tokens.get(3), listofSongs);
                System.out.println("Playlist ID - " + playlist.getId());
                System.out.println("Playlist Name - " + playlist.getName());
                System.out.print("Song IDs - ");
                for(Songs song:playlist.getSongs()){
                    sb.append(song.getId() + " ");
                }
                sb.deleteCharAt(sb.length()-1);
                System.out.print(sb.toString().trim());
            } catch (NullPointerException e) {
                e.getMessage();
            }
        }else if(tokens.get(1).equals("DELETE-SONG")){
            try {
                StringBuilder sb = new StringBuilder();
                playlist = playlistService.deleteSongs(tokens.get(2), tokens.get(3), listofSongs);
                System.out.println("\n" + "Playlist ID - " + playlist.getId());
                System.out.println("Playlist Name - " + playlist.getName());
                System.out.print("Song IDs - ");
                for(Songs song:playlist.getSongs()){
                    sb.append(song.getId() + " ");
                }
                sb.deleteCharAt(sb.length()-1);
                System.out.print(sb);
                System.out.println("\n");
            } catch (NullPointerException e) {
                e.getMessage();
            }
        }  
    }
    
}
