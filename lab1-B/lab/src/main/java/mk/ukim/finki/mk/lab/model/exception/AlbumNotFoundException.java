package mk.ukim.finki.mk.lab.model.exception;

public class AlbumNotFoundException extends RuntimeException
{
    public AlbumNotFoundException(Long albumId)
    {
        super(String.format("Album with id: %s not found", albumId));
    }
}
