package com.crio.jukebox.repo;

import java.util.List;
import com.crio.jukebox.entities.Playlist;
import com.crio.jukebox.entities.User;

public interface IUserRepository extends CRUDRepository<User,String>{
    public List<Playlist> findAllPlaylists(String id);
    public User findUserById(String id);
}
