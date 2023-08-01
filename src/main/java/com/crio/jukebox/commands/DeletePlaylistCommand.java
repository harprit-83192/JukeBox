package com.crio.jukebox.commands;

import java.util.List;
import com.crio.jukebox.services.IUserPlaylistService;

public class DeletePlaylistCommand implements ICommand{
    private final IUserPlaylistService playlistService;

    public DeletePlaylistCommand(IUserPlaylistService playlistService){
        this.playlistService = playlistService;
    }

    @Override
    public void execute(List<String> tokens) {
        playlistService.deletePlaylist(tokens.get(1), tokens.get(2));
        System.out.println("Delete Successful");  
    }  
}
