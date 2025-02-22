package mk.ukim.finki.wp.kol2023.g2.service.impl;

import mk.ukim.finki.wp.kol2023.g2.model.Director;
import mk.ukim.finki.wp.kol2023.g2.model.exceptions.InvalidDirectorIdException;
import mk.ukim.finki.wp.kol2023.g2.repository.DirectorRepository;
import mk.ukim.finki.wp.kol2023.g2.service.DirectorService;
import org.apache.tomcat.jni.Directory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DirectorServiceImpl implements DirectorService
{
    private final DirectorRepository directorRepository;

    public DirectorServiceImpl(DirectorRepository directorRepository)
    {
        this.directorRepository = directorRepository;
    }

    @Override
    public Director findById(Long id)
    {
        return this.directorRepository
                .findById(id)
                .orElseThrow(InvalidDirectorIdException::new);
    }

    @Override
    public List<Director> listAll()
    {
        return this.directorRepository
                .findAll();
    }

    @Override
    public Director create(String name)
    {
        Director director = new Director(name);
        this.directorRepository
                .save(director);
        return director;
    }
}
