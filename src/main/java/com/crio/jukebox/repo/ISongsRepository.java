package com.crio.jukebox.repo;

import com.crio.jukebox.entities.Songs;

public interface ISongsRepository extends CRUDRepository<Songs,String>{
    public Songs findSongById(String id);
}
