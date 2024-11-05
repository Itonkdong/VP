package mk.ukim.finki.wpaud.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Result
{
    private boolean isSuccessful;
    private Exception exception;

    public Result(Exception exception)
    {
        this.isSuccessful = false;
        this.exception = exception;
    }

    public static Result successfulResult()
    {
        return new Result(true,null);
    }

    public String getErrorMessage()
    {
        return this.exception.getMessage();
    }


}
