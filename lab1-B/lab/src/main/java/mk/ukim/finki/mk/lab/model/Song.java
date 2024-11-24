package mk.ukim.finki.mk.lab.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import mk.ukim.finki.mk.lab.service.helper.CustomHandler;

import java.util.List;

@Data
@AllArgsConstructor
@Getter
@Setter
public class Song
{
    private Long id;
    private String trackId;
    private String title;
    private String genre;
    private int releaseYear;
    private List<Artist> performers;

    private Long albumId;
    private Album album;

    public Song()
    {
        this.id = CustomHandler.getRandomId();
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
}
