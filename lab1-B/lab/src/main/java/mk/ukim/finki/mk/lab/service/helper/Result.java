package mk.ukim.finki.mk.lab.service.helper;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Result<E>
{
    private boolean isSuccessful;
    private Exception exception;
    private E value;

    public Result(Exception exception)
    {
        this.isSuccessful = false;
        this.exception = exception;
    }

    public static<E> Result<E> successfulResult(E value)
    {
        return new Result<>(true,null, value);
    }

    public String getErrorMessage()
    {
        return this.exception.getMessage();
    }


}
