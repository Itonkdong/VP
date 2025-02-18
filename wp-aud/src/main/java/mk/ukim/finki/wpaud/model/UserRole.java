package mk.ukim.finki.wpaud.model;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@NoArgsConstructor
public class UserRole
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    public String roleName;

    @ManyToMany(mappedBy = "roles")
    public List<User> users;
}
