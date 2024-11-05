package mk.ukim.finki.mk.lab.repository;

import mk.ukim.finki.mk.lab.bootstrap.DataHolder;
import mk.ukim.finki.mk.lab.model.Artist;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class InMemoryArtistRepository
{
    public List<Artist> findAll()
    {
        return DataHolder.artists;
    }

    public Optional<Artist> findById(long id)
    {
        return DataHolder
                .artists
                .stream()
                .filter(artist -> artist.getId() == id)
                .findFirst();
    }
}
