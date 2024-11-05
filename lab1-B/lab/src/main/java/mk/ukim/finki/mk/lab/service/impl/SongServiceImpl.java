package mk.ukim.finki.mk.lab.service.impl;


import mk.ukim.finki.mk.lab.model.Artist;
import mk.ukim.finki.mk.lab.model.Song;
import mk.ukim.finki.mk.lab.repository.InMemorySongRepository;
import mk.ukim.finki.mk.lab.service.SongService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.MissingResourceException;
import java.util.Optional;

@Service
public class SongServiceImpl implements SongService
{

    private final InMemorySongRepository repository;

    public SongServiceImpl(InMemorySongRepository repository)
    {
        this.repository = repository;
    }

    @Override
    public List<Song> listSongs()
    {
        return repository
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
            this.repository.addArtistToSong(artist, song);
        } catch (MissingResourceException e)
        {
            return null;
        }

        return artist;
    }

    @Override
    public Optional<Song> findByTrackId(String trackId)
    {
        return this.repository.findByTrackId(trackId);
    }
}
