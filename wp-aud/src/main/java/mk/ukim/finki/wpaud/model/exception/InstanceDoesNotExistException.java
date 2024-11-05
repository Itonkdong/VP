package mk.ukim.finki.wpaud.model.exception;

public class InstanceDoesNotExistException extends Exception
{
    public InstanceDoesNotExistException(String instanceName, long id)
    {
        super(String.format("Instance of type: %s and id: %d does not exist", instanceName, id));
    }
}
