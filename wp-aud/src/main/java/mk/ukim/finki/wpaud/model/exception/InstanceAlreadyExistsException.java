package mk.ukim.finki.wpaud.model.exception;

public class InstanceAlreadyExistsException extends Exception
{
    public InstanceAlreadyExistsException(String instanceName)
    {
        super(String.format("Instance of %s already exists in the database", instanceName));
    }
}
