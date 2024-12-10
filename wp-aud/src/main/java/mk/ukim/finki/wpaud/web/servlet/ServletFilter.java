package mk.ukim.finki.wpaud.web.servlet;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "ServletFilter", urlPatterns = "/servlet/filter")
public class ServletFilter extends HttpServlet
{

    public ServletFilter()
    {
        System.out.println("My Servlet 3 ");
    }

    @Override
    public void init(ServletConfig config) throws ServletException
    {
        System.out.println("My Servlet 3 init");
        super.init(config);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        System.out.println("In Servlet Filter 1");
    }

    @Override
    public void destroy()
    {
        System.out.println("Destroying My Servlet 3 ");
    }
}
