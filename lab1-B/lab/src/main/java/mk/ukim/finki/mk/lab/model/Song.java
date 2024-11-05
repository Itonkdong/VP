package mk.ukim.finki.mk.lab.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@AllArgsConstructor
@Getter
@Setter
public class Song
{
    private String trackId;
    private String title;
    private String genre;
    private int releaseYear;
    private List<Artist> performers;
}
