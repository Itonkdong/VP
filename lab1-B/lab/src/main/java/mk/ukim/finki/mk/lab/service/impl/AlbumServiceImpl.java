package mk.ukim.finki.mk.lab.service.impl;

import mk.ukim.finki.mk.lab.model.Album;
import mk.ukim.finki.mk.lab.repository.impl.InMemoryAlbumRepository;
import mk.ukim.finki.mk.lab.repository.jpa.AlbumRepository;
import mk.ukim.finki.mk.lab.service.AlbumService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlbumServiceImpl implements AlbumService
{
    private final AlbumRepository albumRepository;

    public AlbumServiceImpl(AlbumRepository albumRepository)
    {
        this.albumRepository = albumRepository;
    }

    @Override
    public List<Album> findAll()
    {
        return this.albumRepository.findAll();
    }
}
