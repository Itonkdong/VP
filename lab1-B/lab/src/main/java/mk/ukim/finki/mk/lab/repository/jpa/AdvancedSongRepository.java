package mk.ukim.finki.mk.lab.repository.jpa;

import mk.ukim.finki.mk.lab.model.Song;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdvancedSongRepository extends JpaRepository<Song, Long>
{
    List<Song> findAll(Specification<Song> specification);
}
