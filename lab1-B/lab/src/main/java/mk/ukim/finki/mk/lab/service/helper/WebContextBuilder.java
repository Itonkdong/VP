package mk.ukim.finki.mk.lab.service.helper;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.web.servlet.IServletWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

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
