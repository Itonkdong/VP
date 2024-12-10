package mk.ukim.finki.mk.lab.service;

import mk.ukim.finki.mk.lab.model.Album;
import mk.ukim.finki.mk.lab.model.Song;

import java.util.List;

public interface AlbumService
{
    List<Album> findAll();
    Album findById(Long id);
    List<Song> filterSongs(Long albumId, Integer releaseYear);
}
