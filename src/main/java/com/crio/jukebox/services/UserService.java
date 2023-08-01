package com.crio.jukebox.services;

import com.crio.jukebox.dto.Userdto;
import com.crio.jukebox.entities.User;
import com.crio.jukebox.repo.IUserRepository;

public class UserService implements IUserService{
    private final IUserRepository userRepository;

    public UserService( IUserRepository userRepository){
        this.userRepository = userRepository;
    }

    public Userdto create(String name){
        User user = userRepository.save(new User(null, name));
        Userdto userdto = new Userdto(user.getId(), user.getName());
        return userdto;
    }

}
