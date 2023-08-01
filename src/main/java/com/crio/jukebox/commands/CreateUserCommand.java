package com.crio.jukebox.commands;

import java.util.List;
import com.crio.jukebox.dto.Userdto;
import com.crio.jukebox.services.IUserService;

public class CreateUserCommand implements ICommand{

    private final IUserService userService;

    public CreateUserCommand(IUserService userService){
        this.userService = userService;
    }

    @Override
    public void execute(List<String> tokens) {
        Userdto userdto = userService.create(tokens.get(1));
        System.out.println(userdto);
    }
    
}
