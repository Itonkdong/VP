package mk.ukim.finki.mk.lab.bootstrap;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.mk.lab.model.Artist;
import mk.ukim.finki.mk.lab.model.Song;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder
{
    public static List<Artist> artists;
    public static long nextArtistId = 0;

    public static List<Song> songs;
    public static long nextSongId = 0;


    @PostConstruct
    public void init()
    {
        artists = new ArrayList<>();
        artists.add(new Artist(nextArtistId++, "Bon", "Jovi", "It's his life"));
        artists.add(new Artist(nextArtistId++, "Freddie", "Mercury", "Legendary Queen front-man"));
        artists.add(new Artist(nextArtistId++, "Elvis", "Presley", "The King of Rock 'n' Roll"));
        artists.add(new Artist(nextArtistId++, "Aretha", "Franklin", "The Queen of Soul"));
        artists.add(new Artist(nextArtistId++, "David", "Bowie", "Iconic singer and songwriter"));

        songs = new ArrayList<>();


        ArrayList<Artist> songArtis = new ArrayList<>();
        songArtis.add(artists.get(0));

        ArrayList<Artist> songArtis1 = new ArrayList<>();
        songArtis1.add(artists.get(1)); // Freddie Mercury

        ArrayList<Artist> songArtis2 = new ArrayList<>();
        songArtis2.add(artists.get(2)); // Elvis Presley

        ArrayList<Artist> songArtis3 = new ArrayList<>();
        songArtis3.add(artists.get(3)); // Aretha Franklin

        ArrayList<Artist> songArtis4 = new ArrayList<>();
        songArtis4.add(artists.get(4)); // David Bowie

        songs.add(new Song(String.valueOf(nextSongId++), "It's My Life", "Rock", 2000, songArtis));
        songs.add(new Song(String.valueOf(nextSongId++), "Bohemian Rhapsody", "Rock", 1975, songArtis1));
        songs.add(new Song(String.valueOf(nextSongId++), "Jailhouse Rock", "Rock 'n' Roll", 1957, songArtis2));
        songs.add(new Song(String.valueOf(nextSongId++), "Respect", "Soul", 1967, songArtis3));
        songs.add(new Song(String.valueOf(nextSongId++), "Heroes", "Rock", 1977, songArtis4));

    }
}
