package com.crio.jukebox.dto;

public class Userdto {
    private final String userId;
    private final String username;

    public Userdto(String userId, String username){
        this.userId =  userId;
        this.username = username;
    }

    public String getId(){
        return userId;
    }

    public String getUsername(){
        return username;
    }

    @Override
    public String toString(){
        return userId + " " + username;
    }
}

