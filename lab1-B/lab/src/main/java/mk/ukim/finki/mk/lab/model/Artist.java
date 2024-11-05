package mk.ukim.finki.mk.lab.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
@Data

public class Artist
{
    private Long id;
    private String firstName;
    private String lastName;
    private String bio;
}
