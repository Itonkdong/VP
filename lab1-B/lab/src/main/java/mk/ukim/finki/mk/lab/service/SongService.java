package mk.ukim.finki.mk.lab.service;

import mk.ukim.finki.mk.lab.model.Artist;
import mk.ukim.finki.mk.lab.model.Song;
import mk.ukim.finki.mk.lab.service.helper.Result;

import java.util.List;
import java.util.Optional;

public interface SongService
{
    List<Song> listSongs();

    Artist addArtistToSong(Artist artist, Song song);

    Optional<Song> findByTrackId(String trackId);

    Result<Song> addSong(String title, String trackId, String genre, int releaseYear, Long albumId);

    Optional<Song> findSongById(Long id);

    Song editSong(Long songId, String title, String trackId, String genre, Integer releaseYear, Long albumId);

    Song deleteSong(Long id);
}
