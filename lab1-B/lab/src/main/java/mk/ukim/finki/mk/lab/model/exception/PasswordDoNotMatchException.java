package mk.ukim.finki.mk.lab.model.exception;

public class PasswordDoNotMatchException extends RuntimeException
{
    public PasswordDoNotMatchException()
    {
        super("Passwords do not match");
    }
}
