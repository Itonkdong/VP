package mk.ukim.finki.wpaud.model.helper;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.web.servlet.IServletWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class WebContextBuilder
{
    public static WebContext getContext(HttpServletRequest request, HttpServletResponse response, ServletContext servletContext)
    {
        IServletWebExchange exchange = JakartaServletWebApplication
                .buildApplication(servletContext)
                .buildExchange(request, response);

        return new WebContext(exchange);
    }
}
