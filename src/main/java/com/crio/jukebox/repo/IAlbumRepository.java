package com.crio.jukebox.repo;

import com.crio.jukebox.entities.Album;

public interface IAlbumRepository extends CRUDRepository<Album,String>{
    public Album findByName(String name);
}
