package com.crio.jukebox.entities;

public class User extends BaseEntity {
    private final String name;

    public User(String id, String name){
        this.name = name;
        this.id = id;
    }

    public String getName(){
        return name;
    }

    @Override
    public String toString(){
        return "User [ UserId=" + id + ", Username=" + name + " ]";
    }
}
