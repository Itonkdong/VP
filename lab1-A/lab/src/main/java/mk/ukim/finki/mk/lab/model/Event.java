package mk.ukim.finki.mk.lab.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
@AllArgsConstructor
public class Event
{
    private String name;
    private String description;
    private double popularityScore;
}
