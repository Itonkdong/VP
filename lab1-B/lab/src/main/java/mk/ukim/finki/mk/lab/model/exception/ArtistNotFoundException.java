package mk.ukim.finki.mk.lab.model.exception;

public class ArtistNotFoundException extends RuntimeException
{
    public ArtistNotFoundException(Long artistId)
    {
        super(String.format("Artist with id: %s not found", artistId));
    }
}
