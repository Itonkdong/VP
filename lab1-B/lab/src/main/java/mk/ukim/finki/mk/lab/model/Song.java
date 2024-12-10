package mk.ukim.finki.mk.lab.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import mk.ukim.finki.mk.lab.service.helper.CustomHandler;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Song
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String trackId;
    private String title;
    private String genre;
    private int releaseYear;

    @ManyToMany
    private List<Artist> performers;

    @Transient
    private Long albumId;

    @ManyToOne
    private Album album;

    public Song()
    {
//        this.id = CustomHandler.getRandomId();
    }

    public Song(String trackId, String title, String genre, int releaseYear, List<Artist> performers, Album album)
    {
        this.id = CustomHandler.getRandomId();
        this.trackId = trackId;
        this.title = title;
        this.genre = genre;
        this.releaseYear = releaseYear;
        this.performers = performers;
        this.album = album;
        this.albumId = album.getId();
    }

    public Song(String trackId, String title, String genre, int releaseYear, Album album)
    {
        this.id = CustomHandler.getRandomId();
        this.trackId = trackId;
        this.title = title;
        this.genre = genre;
        this.releaseYear = releaseYear;
        this.performers = new ArrayList<>();
        this.album = album;
        this.albumId = album.getId();
    }
}
