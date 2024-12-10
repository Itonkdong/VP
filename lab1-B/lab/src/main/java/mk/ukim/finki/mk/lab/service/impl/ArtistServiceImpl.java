package mk.ukim.finki.mk.lab.service.impl;

import mk.ukim.finki.mk.lab.model.Artist;
import mk.ukim.finki.mk.lab.model.Song;
import mk.ukim.finki.mk.lab.model.exception.ArtistNotFoundException;
import mk.ukim.finki.mk.lab.repository.jpa.ArtistRepository;
import mk.ukim.finki.mk.lab.repository.jpa.SongRepository;
import mk.ukim.finki.mk.lab.service.ArtistService;
import mk.ukim.finki.mk.lab.service.helper.CustomHandler;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ArtistServiceImpl implements ArtistService
{

    private final ArtistRepository artistRepository;
    private final SongRepository songRepository;

    public ArtistServiceImpl(ArtistRepository artistRepository, SongRepository songRepository)
    {
        this.artistRepository = artistRepository;
        this.songRepository = songRepository;
    }

    @Override
    public List<Artist> listAll()
    {
        return this.artistRepository
                .findAll();
    }

    @Override
    public Optional<Artist> findById(long id)
    {
        return this.artistRepository
                .findById(id);
    }

    @Override
    public List<Song> findArtistSongs(long artistId)
    {
        Artist artist = this.findById(artistId)
                .orElseThrow(() -> new ArtistNotFoundException(artistId));
        List<Song> artistsSongs = new ArrayList<>();

        List<Song> songs = songRepository.findAll();

        for (Song song : songs)
        {
            Optional<Artist> foundArtist = song.getPerformers().
                    stream()
                    .filter(p -> p.getId().equals(artist.getId()))
                    .findFirst();

            if (foundArtist.isPresent())
            {
                artistsSongs.add(song);
            }
        }

        return artistsSongs;
    }

    @Override
    public List<Artist> findArtistsByName(String artistName)
    {
        if (CustomHandler.isNullOrEmpty(artistName))
        {
            return this.listAll();
        }

        return this.artistRepository
                .findByFirstNameLikeOrLastNameLike(artistName, artistName);
    }
}
