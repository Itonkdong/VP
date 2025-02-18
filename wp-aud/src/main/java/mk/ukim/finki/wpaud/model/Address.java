package mk.ukim.finki.wpaud.model;


import jakarta.persistence.*;

@Entity
public class Address
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    public String address;
    public String city;

    @ManyToOne(fetch = FetchType.EAGER)
    public User user;
}
