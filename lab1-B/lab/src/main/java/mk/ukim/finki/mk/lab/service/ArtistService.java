package mk.ukim.finki.mk.lab.service;

import mk.ukim.finki.mk.lab.model.Artist;
import mk.ukim.finki.mk.lab.model.Song;

import java.util.List;
import java.util.Optional;

public interface ArtistService
{
    List<Artist> listAll();
    Optional<Artist> findById(long id);
    List<Song> findArtistSongs(long artistId);

    List<Artist> findArtistsByName(String artistName);
}
