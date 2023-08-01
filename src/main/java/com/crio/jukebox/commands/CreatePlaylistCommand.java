package com.crio.jukebox.commands;

import java.util.ArrayList;
import java.util.List;
import com.crio.jukebox.entities.Playlist;
import com.crio.jukebox.exception.SongNotFoundException;
import com.crio.jukebox.exception.UserNotFoundException;
import com.crio.jukebox.services.IUserPlaylistService;

public class CreatePlaylistCommand implements ICommand{
    private final IUserPlaylistService playlistService;

    public CreatePlaylistCommand(IUserPlaylistService playlistService){
        this.playlistService = playlistService;
    }

    @Override
    public void execute(List<String> tokens) {
        try {
            List<String> listofSongs = new ArrayList<>();
            for(int i=3;i<tokens.size();i++){
                listofSongs.add(tokens.get(i));
            }
            Playlist playlist = playlistService.createPlaylist(tokens.get(1), tokens.get(2), listofSongs);
            System.out.println(playlist);
        } catch (UserNotFoundException e) {
            System.out.println("User not Found");
        } catch (SongNotFoundException e) {
            System.out.println("Song not Found");
        } catch (Exception e){
            e.getMessage();
        }
    }
    
}
