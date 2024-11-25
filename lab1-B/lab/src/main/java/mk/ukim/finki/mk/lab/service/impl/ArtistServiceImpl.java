package mk.ukim.finki.mk.lab.service.impl;

import mk.ukim.finki.mk.lab.model.Artist;
import mk.ukim.finki.mk.lab.model.Song;
import mk.ukim.finki.mk.lab.repository.InMemoryArtistRepository;
import mk.ukim.finki.mk.lab.service.ArtistService;
import mk.ukim.finki.mk.lab.service.SongService;
import mk.ukim.finki.mk.lab.service.helper.CustomHandler;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.MissingResourceException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ArtistServiceImpl implements ArtistService
{

    private final InMemoryArtistRepository repository;
    private final SongService songService;

    public ArtistServiceImpl(InMemoryArtistRepository repository, SongService songService)
    {
        this.repository = repository;
        this.songService = songService;
    }

    @Override
    public List<Artist> listAll()
    {
        return this.repository
                .findAll();
    }

    @Override
    public Optional<Artist> findById(long id)
    {
        return this.repository
                .findById(id);
    }

    @Override
    public List<Song> findArtistSongs(long artistId)
    {
        Optional<Artist> artist = this.findById(artistId);
        List<Song> artistsSongs = new ArrayList<>();

        if (artist.isEmpty())
        {
//            resp.sendRedirect("/allArtists");
            throw new MissingResourceException("Artist not found", "Artist", String.valueOf(artistId));
//            return;
        }

        List<Song> songs = songService.listSongs();

        for (Song song : songs)
        {
            Optional<Artist> foundArtist = song.getPerformers().
                    stream()
                    .filter(p -> p.getId() == artistId)
                    .findFirst();

            if (foundArtist.isPresent())
            {
                artistsSongs.add(song);
            }
        }

        return artistsSongs;
    }

    @Override
    public List<Artist> findArtistsByName(String artistName) {
        if (CustomHandler.isNullOrEmpty(artistName))
        {
            return this.listAll();
        }

        return this.listAll().stream().filter(artist -> artist.getFirstName().contains(artistName)).collect(Collectors.toList());
    }
}
