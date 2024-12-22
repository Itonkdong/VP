package mk.ukim.finki.mk.lab.model.exception;

public class UserAlreadyExistsException extends RuntimeException
{
    public UserAlreadyExistsException()
    {
        super("There already exists an user with those credentials");
    }
}
