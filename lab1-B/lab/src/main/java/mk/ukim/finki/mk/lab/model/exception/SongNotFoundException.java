package mk.ukim.finki.mk.lab.model.exception;

public class SongNotFoundException extends RuntimeException
{
    public SongNotFoundException(Long songId)
    {
        super(String.format("Song with id: %s not found", songId));
    }
}
