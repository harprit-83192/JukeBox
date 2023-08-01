package com.crio.jukebox.AppConfig;

import com.crio.jukebox.commands.CommandInvoker;
import com.crio.jukebox.commands.CreatePlaylistCommand;
import com.crio.jukebox.commands.CreateUserCommand;
import com.crio.jukebox.commands.DeletePlaylistCommand;
import com.crio.jukebox.commands.LoadDataCommand;
import com.crio.jukebox.commands.ModifyPlaylistCommand;
import com.crio.jukebox.commands.PlayPlaylistCommand;
import com.crio.jukebox.commands.PlaySongCommand;
import com.crio.jukebox.repo.AlbumRepository;
import com.crio.jukebox.repo.IAlbumRepository;
import com.crio.jukebox.repo.IPlaylistRepository;
import com.crio.jukebox.repo.IUserPlaylistRepository;
import com.crio.jukebox.repo.ISongsRepository;
import com.crio.jukebox.repo.IUserRepository;
import com.crio.jukebox.repo.PlaylistRepository;
import com.crio.jukebox.repo.UserPlaylistRepository;
import com.crio.jukebox.repo.SongsRepository;
import com.crio.jukebox.repo.UserRepository;
import com.crio.jukebox.services.IUserPlaylistService;
import com.crio.jukebox.services.ISongsService;
import com.crio.jukebox.services.IUserService;
import com.crio.jukebox.services.UserPlaylistService;
import com.crio.jukebox.services.SongsService;
import com.crio.jukebox.services.UserService;

public class ApplicationConfig {
    private final IAlbumRepository albumRepository = new AlbumRepository();
    private final ISongsRepository songsRepository = new SongsRepository();
    private final IPlaylistRepository playlistRepository = new PlaylistRepository();
    private final IUserPlaylistRepository userPlaylistRepository = new UserPlaylistRepository(playlistRepository);
    private final IUserRepository userRepository = new UserRepository(userPlaylistRepository);

    private final IUserService userService = new UserService(userRepository);
    private final ISongsService songsService = new SongsService(songsRepository, albumRepository);
    private final IUserPlaylistService playlistService = new UserPlaylistService(userPlaylistRepository, userRepository, songsRepository);

    private final CreatePlaylistCommand createPlaylistCommand = new CreatePlaylistCommand(playlistService);
    private final CreateUserCommand createUserCommand = new CreateUserCommand(userService);
    private final DeletePlaylistCommand deletePlaylistCommand = new DeletePlaylistCommand(playlistService);
    private final LoadDataCommand loadDataCommand = new LoadDataCommand(songsService);
    private final PlayPlaylistCommand playPlaylistCommand = new PlayPlaylistCommand(playlistService);
    private final PlaySongCommand playSongCommand = new PlaySongCommand(playlistService);
    private final ModifyPlaylistCommand modifyPlaylistCommand = new ModifyPlaylistCommand(playlistService);

    private CommandInvoker commandInvoker = new CommandInvoker();

    public CommandInvoker getCommandInvoker(){
        commandInvoker.register("LOAD-DATA", loadDataCommand);
        commandInvoker.register("CREATE-USER", createUserCommand);
        commandInvoker.register("CREATE-PLAYLIST", createPlaylistCommand);
        commandInvoker.register("DELETE-PLAYLIST", deletePlaylistCommand);
        commandInvoker.register("PLAY-PLAYLIST", playPlaylistCommand);
        commandInvoker.register("MODIFY-PLAYLIST", modifyPlaylistCommand);
        commandInvoker.register("PLAY-SONG", playSongCommand);
        return commandInvoker;
    }
}
