package mk.ukim.finki.mk.lab.repository.specifications;

import mk.ukim.finki.mk.lab.model.Song;
import org.springframework.data.jpa.domain.Specification;

public class SongSpecification {

    public static Specification<Song> belongsToAlbum(Long albumId) {
        return (root, query, criteriaBuilder) ->
                albumId == null
                        ? null
                        : criteriaBuilder.equal(root.get("album").get("id"), albumId);
    }

    public static Specification<Song> releasedInYear(Integer year) {
        return (root, query, criteriaBuilder) ->
                year == null
                        ? null
                        : criteriaBuilder.greaterThan(root.get("releaseYear"), year);
    }
}
