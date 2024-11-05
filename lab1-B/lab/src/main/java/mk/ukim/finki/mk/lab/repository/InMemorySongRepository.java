package mk.ukim.finki.mk.lab.repository;

import mk.ukim.finki.mk.lab.bootstrap.DataHolder;
import mk.ukim.finki.mk.lab.model.Artist;
import mk.ukim.finki.mk.lab.model.Song;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.MissingResourceException;
import java.util.Optional;

@Repository
public class InMemorySongRepository
{
    public List<Song> findAll()
    {
        return DataHolder.songs;
    }
    public Optional<Song> findByTrackId(String trackId)
    {
        return DataHolder
                .songs
                .stream()
                .filter(song -> song.getTrackId().equals(trackId))
                .findFirst();
    }

    public Artist addArtistToSong(Artist artist, Song song) throws MissingResourceException
    {
        Optional<Song> songOptional = this.findByTrackId(song.getTrackId());

        if (songOptional.isEmpty())
        {
            throw new MissingResourceException("No such song in database.", "Song", song.getTrackId());
        }

        songOptional
                .get()
                .getPerformers()
                .add(artist);

        return artist;
    }
}
