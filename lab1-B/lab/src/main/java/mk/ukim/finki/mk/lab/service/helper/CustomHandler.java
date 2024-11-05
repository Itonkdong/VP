package mk.ukim.finki.mk.lab.service.helper;

import jakarta.servlet.http.HttpServletRequest;
import org.thymeleaf.context.WebContext;

import java.util.Optional;
import java.util.function.Predicate;


public class CustomHandler
{

    public static void handleError(HttpServletRequest request, WebContext context)
    {
        String error = request.getParameter("error");
        context.setVariable("error", error);
    }

    public static<T,E> boolean filterHandle(Optional<T> value, E object, Predicate<E> predicate)
    {
        if (value.isEmpty())
        {
            return true;
        }

        return predicate.test(object);
    }

    public static<E> Optional<E> createOptional(E value)
    {
        if (value == null) return Optional.empty();
        if (value instanceof String && ((String) value).isEmpty()) return Optional.empty();

        return Optional.of(value);
    }

    public static boolean isNullOrEmpty(String s)
    {
        return s == null || s.isEmpty();
    }
}
