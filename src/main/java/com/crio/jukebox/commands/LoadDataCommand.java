package com.crio.jukebox.commands;

import java.util.List;
import com.crio.jukebox.services.ISongsService;

public class LoadDataCommand implements ICommand{
    private final ISongsService songsService;

    public LoadDataCommand(ISongsService songsService){
        this.songsService = songsService;
    }

    @Override
    public void execute(List<String> tokens) {
        songsService.LoadData(tokens.get(1));
        System.out.println("Songs Loaded successfully");
    }
    
}
