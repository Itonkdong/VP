package mk.ukim.finki.wpaud.model.exception;

public class InvalidArgumentsException extends Exception
{
    public InvalidArgumentsException(Object value)
    {
        super(String.format("Argument has invalid value: %s", value.toString()));
    }
}
