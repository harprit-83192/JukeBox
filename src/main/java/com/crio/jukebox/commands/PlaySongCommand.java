package com.crio.jukebox.commands;

import java.util.List;
import com.crio.jukebox.dto.Songsdto;
import com.crio.jukebox.entities.SongsOrder;
import com.crio.jukebox.exception.SongNotFoundException;
import com.crio.jukebox.services.IUserPlaylistService;

public class PlaySongCommand implements ICommand{
    private final IUserPlaylistService playlistService;

    public PlaySongCommand (IUserPlaylistService playlistService){
        this.playlistService = playlistService;
    }

    @Override
    public void execute(List<String> tokens) {
        try {
            Songsdto songsdto;
            if(tokens.get(2).equals("NEXT")){
                songsdto=playlistService.playSongbyOrder(tokens.get(1), SongsOrder.NEXT);
            }else if(tokens.get(2).equals("BACK")){
                songsdto=playlistService.playSongbyOrder(tokens.get(1), SongsOrder.BACK);
            }else{
                songsdto=playlistService.playSongbyId(tokens.get(1), tokens.get(2));
            }
            System.out.println(songsdto);
        } catch (SongNotFoundException e) {
            System.out.println("Given song id is not a part of the active playlist");
        } catch (Exception e){
            e.getMessage();
        }
    }
    
}
