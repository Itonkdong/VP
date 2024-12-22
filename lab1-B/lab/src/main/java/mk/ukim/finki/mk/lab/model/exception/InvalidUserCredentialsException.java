package mk.ukim.finki.mk.lab.model.exception;

public class InvalidUserCredentialsException extends RuntimeException
{
    public InvalidUserCredentialsException()
    {
        super("Username with those credentials does not exists");
    }
}
