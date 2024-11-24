package mk.ukim.finki.mk.lab.service.impl;


import mk.ukim.finki.mk.lab.model.Album;
import mk.ukim.finki.mk.lab.model.Artist;
import mk.ukim.finki.mk.lab.model.Song;
import mk.ukim.finki.mk.lab.model.exception.AlbumNotFoundException;
import mk.ukim.finki.mk.lab.model.exception.SongNotFoundException;
import mk.ukim.finki.mk.lab.repository.InMemoryAlbumRepository;
import mk.ukim.finki.mk.lab.repository.InMemorySongRepository;
import mk.ukim.finki.mk.lab.service.SongService;
import mk.ukim.finki.mk.lab.service.helper.Result;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.MissingResourceException;
import java.util.Optional;

@Service
public class SongServiceImpl implements SongService
{

    private final InMemorySongRepository songRepository;
    private final InMemoryAlbumRepository albumRepository;

    public SongServiceImpl(InMemorySongRepository repository, InMemoryAlbumRepository albumRepository)
    {
        this.songRepository = repository;
        this.albumRepository = albumRepository;
    }

    @Override
    public List<Song> listSongs()
    {
        return songRepository
                .findAll();
    }

    @Override
    public Artist addArtistToSong(Artist artist, Song song)
    {

        if (song.getPerformers().contains(artist))
        {
            return artist;
        }

        try
        {
            this.songRepository.addArtistToSong(artist, song);
        } catch (MissingResourceException e)
        {
            return null;
        }

        return artist;
    }

    @Override
    public Optional<Song> findByTrackId(String trackId)
    {
        return this.songRepository.findByTrackId(trackId);
    }

    @Override
    public Result<Song> addSong(String title, String trackId, String genre, int releaseYear, Long albumId)
    {
        Album album = this.albumRepository
                .findById(albumId)
                .orElseThrow(() -> new AlbumNotFoundException(albumId));

        Song song = this.songRepository
                .addSong(title, trackId, genre, releaseYear, album);

        return Result.successfulResult(song);
    }

    @Override
    public Optional<Song> findSongById(Long id)
    {
        return this.songRepository
                .findSongById(id);
    }

    @Override
    public Song editSong(Long songId, String title, String trackId, String genre, Integer releaseYear, Long albumId) throws AlbumNotFoundException, SongNotFoundException
    {

        Album album = this.albumRepository
                .findById(albumId)
                .orElseThrow(() -> new AlbumNotFoundException(albumId));

        return this.songRepository.updateSong(songId, title, trackId, genre, releaseYear, album);
    }

    @Override
    public Song deleteSong(Long id) throws SongNotFoundException
    {
       return this.songRepository.deleteSong(id);
    }
}
