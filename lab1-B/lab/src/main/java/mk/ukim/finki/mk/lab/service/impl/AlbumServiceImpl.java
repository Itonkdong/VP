package mk.ukim.finki.mk.lab.service.impl;

import mk.ukim.finki.mk.lab.model.Album;
import mk.ukim.finki.mk.lab.model.Song;
import mk.ukim.finki.mk.lab.model.exception.AlbumNotFoundException;
import mk.ukim.finki.mk.lab.repository.jpa.AlbumRepository;
import mk.ukim.finki.mk.lab.repository.jpa.AdvancedSongRepository;
import mk.ukim.finki.mk.lab.service.AlbumService;
import mk.ukim.finki.mk.lab.repository.specifications.SongSpecification;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlbumServiceImpl implements AlbumService
{
    private final AlbumRepository albumRepository;
    private final AdvancedSongRepository advancedSongRepository;

    public AlbumServiceImpl(AlbumRepository albumRepository, AdvancedSongRepository advancedSongRepository)
    {
        this.albumRepository = albumRepository;
        this.advancedSongRepository = advancedSongRepository;
    }


    @Override
    public List<Album> findAll()
    {
        return this.albumRepository.findAll();
    }

    @Override
    public Album findById(Long id)
    {
        return albumRepository.findById(id)
                .orElseThrow(()->new AlbumNotFoundException(id));
    }

    @Override
    public List<Song> filterSongs(Long albumId, Integer releaseYear)
    {
        Specification<Song> spec = Specification
                .where(SongSpecification.belongsToAlbum(albumId))
                .and(SongSpecification.releasedInYear(releaseYear));

        return advancedSongRepository.findAll(spec);
    }
}
