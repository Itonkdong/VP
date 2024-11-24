package mk.ukim.finki.mk.lab.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import mk.ukim.finki.mk.lab.service.helper.CustomHandler;

import java.util.ArrayList;
import java.util.List;


@Data
@Getter
@Setter
public class Album
{
    private Long id;
    private String name;
    private String genre;
    private String releaseYear;

//    private List<Song> songs;

    public Album()
    {
        this.id = CustomHandler.getRandomId();
//        this.songs = new ArrayList<>();
    }

    public Album(String name, String genre, String releaseYear)
    {
        this.name = name;
        this.genre = genre;
        this.releaseYear = releaseYear;

        this.id = CustomHandler.getRandomId();
//        this.songs = new ArrayList<>();
    }
}
