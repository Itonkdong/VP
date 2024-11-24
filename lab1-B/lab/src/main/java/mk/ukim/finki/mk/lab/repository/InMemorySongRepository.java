package mk.ukim.finki.mk.lab.repository;

import mk.ukim.finki.mk.lab.bootstrap.DataHolder;
import mk.ukim.finki.mk.lab.model.Album;
import mk.ukim.finki.mk.lab.model.Artist;
import mk.ukim.finki.mk.lab.model.Song;
import mk.ukim.finki.mk.lab.model.exception.SongNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
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

    public Song addSong(String title, String trackId, String genre, int releaseYear, Album album)
    {
        Song newSong = new Song(trackId, title, genre, releaseYear, new ArrayList<>(), album);
        DataHolder.songs.add(newSong);
        return newSong;
    }

    public Optional<Song> findSongById(Long id)
    {
        return DataHolder
                .songs
                .stream()
                .filter(song -> song.getId().equals(id))
                .findFirst();
    }

    public Song updateSong(Long songId, String title, String trackId, String genre, Integer releaseYear, Album album) throws SongNotFoundException
    {
        Song song = this
                .findSongById(songId)
                .orElseThrow(() -> new SongNotFoundException(songId));

        song.setTitle(title);
        song.setTrackId(trackId);
        song.setGenre(genre);
        song.setReleaseYear(releaseYear);
        song.setAlbum(album);
        song.setAlbumId(album.getId());

        return song;
    }

    public Song deleteSong(Long id) throws SongNotFoundException
    {
        Song deleteSong = DataHolder.songs
                .stream()
                .filter(song -> song.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new SongNotFoundException(id));

        DataHolder.songs.remove(deleteSong);
        return deleteSong;
    }
}
