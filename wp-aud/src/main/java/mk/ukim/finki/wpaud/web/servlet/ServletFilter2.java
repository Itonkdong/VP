package mk.ukim.finki.wpaud.web.servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "ServletFilter2", urlPatterns = "/servlet/filter2")
public class ServletFilter2 extends HttpServlet
{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
//        System.out.println("In Servlet Filter 2");
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/servlet/filter");
        requestDispatcher.include(req,resp);
//        System.out.println("In Servlet Filter 2 LAST PRINT");

    }
}
