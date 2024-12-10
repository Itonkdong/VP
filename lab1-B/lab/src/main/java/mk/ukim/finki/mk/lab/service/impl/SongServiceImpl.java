package mk.ukim.finki.mk.lab.service.impl;


import mk.ukim.finki.mk.lab.model.Album;
import mk.ukim.finki.mk.lab.model.Artist;
import mk.ukim.finki.mk.lab.model.Song;
import mk.ukim.finki.mk.lab.model.exception.AlbumNotFoundException;
import mk.ukim.finki.mk.lab.model.exception.ArtistNotFoundException;
import mk.ukim.finki.mk.lab.model.exception.SongNotFoundException;
import mk.ukim.finki.mk.lab.repository.jpa.AlbumRepository;
import mk.ukim.finki.mk.lab.repository.jpa.ArtistRepository;
import mk.ukim.finki.mk.lab.repository.jpa.SongRepository;
import mk.ukim.finki.mk.lab.service.SongService;
import mk.ukim.finki.mk.lab.service.helper.Result;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.MissingResourceException;
import java.util.Optional;

@Service
public class SongServiceImpl implements SongService
{

    private final SongRepository songRepository;
    private final AlbumRepository albumRepository;
    private final ArtistRepository artistRepository;

    public SongServiceImpl(SongRepository repository, AlbumRepository albumRepository, ArtistRepository artistRepository)
    {
        this.songRepository = repository;
        this.albumRepository = albumRepository;
        this.artistRepository = artistRepository;
    }

    @Override
    public List<Song> listSongs()
    {
        return songRepository
                .findAll();
    }

    @Override
    public Artist addArtistToSong(Long artistId, String trackId)
    {
        Song song = this.songRepository
                .findByTrackId(trackId)
                .orElseThrow(() -> new SongNotFoundException(Long.parseLong(trackId)));

        Artist artist = this.artistRepository
                .findById(artistId)
                .orElseThrow(() -> new ArtistNotFoundException(artistId));

        if (song.getPerformers().contains(artist))
        {
            return artist;
        }

        song.getPerformers().add(artist);
        this.songRepository.save(song);

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

        Song saveSong = new Song(trackId, title, genre, releaseYear, album);
        saveSong = this.songRepository
                .save(saveSong);

        return Result.successfulResult(saveSong);
    }

    @Override
    public Optional<Song> findSongById(Long id)
    {
        return this.songRepository
                .findById(id);
    }

    @Override
    public Song editSong(Long songId, String title, String trackId, String genre, Integer releaseYear, Long albumId) throws AlbumNotFoundException, SongNotFoundException
    {

        Album album = this.albumRepository
                .findById(albumId)
                .orElseThrow(() -> new AlbumNotFoundException(albumId));

        Song songToUpdate = this.songRepository
                .findById(songId)
                .orElseThrow(() -> new SongNotFoundException(songId));

        songToUpdate.setTitle(title);
        songToUpdate.setTrackId(trackId);
        songToUpdate.setGenre(genre);
        songToUpdate.setReleaseYear(releaseYear);
        songToUpdate.setAlbum(album);

        return this.songRepository.save(songToUpdate);
    }

    @Override
    public Song deleteSong(Long id) throws SongNotFoundException
    {
        Song deletedSong = this.songRepository
                .findById(id)
                .orElseThrow(() -> new SongNotFoundException(id));
        this.songRepository.deleteById(id);
        return deletedSong;
    }

}
