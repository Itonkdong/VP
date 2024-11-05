package mk.ukim.finki.wpaud.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@Data
public class Category
{
    private Long Id;
    private String name;
    private String description;
}
