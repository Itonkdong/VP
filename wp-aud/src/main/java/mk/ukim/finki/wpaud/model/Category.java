package mk.ukim.finki.wpaud.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

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
