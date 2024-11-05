package mk.ukim.finki.wpaud.model.helper;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.ukim.finki.wpaud.model.exception.InvalidArgumentsException;
import org.thymeleaf.context.WebContext;

import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

public class ErrorHandler
{

    public static void checkNull(Object... arguments) throws InvalidArgumentsException
    {
        Optional<Object> firstNull = null;
        try
        {
            firstNull = Arrays.stream(arguments)
                    .filter(Objects::isNull).findFirst();
        } catch (Exception e)
        {
            throw new InvalidArgumentsException("null");
        }

        if (firstNull.isPresent())
        {
            throw new InvalidArgumentsException("null");
        }
    }

    public static void handleError(HttpServletRequest request, WebContext context)
    {
        String error = request.getParameter("error");
        context.setVariable("error", error);
    }
}
