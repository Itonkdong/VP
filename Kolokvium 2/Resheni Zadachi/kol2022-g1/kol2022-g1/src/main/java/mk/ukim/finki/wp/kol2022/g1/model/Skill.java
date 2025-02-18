package mk.ukim.finki.wp.kol2022.g1.model;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Skill {

    public Skill() {
    }

    public Skill(String name) {
        this.name = name;
        this.employees = new ArrayList<>();
    }


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToMany(mappedBy = "skills")
    private List<Employee> employees;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
