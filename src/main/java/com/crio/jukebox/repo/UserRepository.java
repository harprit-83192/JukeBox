package com.crio.jukebox.repo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import com.crio.jukebox.entities.Playlist;
import com.crio.jukebox.entities.User;

public class UserRepository implements IUserRepository {
    private final Map<String,User> userMap=new HashMap<>();
    private int count = 0;
    private final IUserPlaylistRepository userPlaylistRepository;

    public UserRepository(IUserPlaylistRepository playlistRepository) {
        this.userPlaylistRepository = playlistRepository;
    }

    @Override
    public User save(User user) {
        if( user.getId() == null){
            count++;
            User u = new User(Integer.toString(count), user.getName());
            userMap.put(u.getId(),u);
            return u;
        }
        userMap.put(user.getId(),user);
        return user;
    }

    @Override
    public List<User> findAll() {
        return userMap.values().stream().collect(Collectors.toList());
    }

    @Override
    public Optional<User> findById(String id) {
        return Optional.of(userMap.get(id));
    }

    @Override
    public boolean existsById(String id) {
        return false;
    }

    @Override
    public void delete(User entity) {
    }

    @Override
    public void deleteById(String id) {    
    }

    @Override
    public long count() {
        return 0;
    }

    public List<Playlist> findAllPlaylists(String id) {
        return userPlaylistRepository.findPlaylists(id);
    }

    public User findUserById(String id){
        return userMap.get(id);
    }
    
}
