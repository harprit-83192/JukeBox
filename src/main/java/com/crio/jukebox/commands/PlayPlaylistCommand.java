package com.crio.jukebox.commands;

import java.util.List;
import com.crio.jukebox.dto.Songsdto;
import com.crio.jukebox.exception.PlaylistNotFoundException;
import com.crio.jukebox.services.IUserPlaylistService;

public class PlayPlaylistCommand implements ICommand{
    private final IUserPlaylistService playlistService;

    public PlayPlaylistCommand(IUserPlaylistService playlistService){
        this.playlistService = playlistService;
    }
    @Override
    public void execute(List<String> tokens) {
        // System.out.println("Current Song Playing");
        try {
            Songsdto songsdto =playlistService.playPlaylist(tokens.get(1), tokens.get(2));
            System.out.println(songsdto);
        } catch (PlaylistNotFoundException e) {
            System.out.println("Playlist not Found");
        } catch(Exception e){
            e.getMessage();
        }
    }
    
}
