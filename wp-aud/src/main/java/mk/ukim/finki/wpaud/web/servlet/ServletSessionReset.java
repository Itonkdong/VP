package mk.ukim.finki.wpaud.web.servlet;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "MyServlet4", urlPatterns = "/servlet/session-reset")
public class ServletSessionReset extends HttpServlet
{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        ServletContext servletContext = this.getServletContext();
        PrintWriter writer = resp.getWriter();
        Object visits = servletContext.getAttribute("Visits");
        if (visits == null)
        {
            servletContext.setAttribute("Visits", 0);
        }
        int visits1 = (int) servletContext.getAttribute("Visits");
        servletContext.setAttribute("Visits", ++visits1);

        writer.printf("Num Visits: %d", visits1);

        if (visits1 == 2)
        {
            HttpSession session = req.getSession();
        }
        else if (visits1 == 3)
        {
            req.getSession().invalidate();
            servletContext.setAttribute("Visits", 0);

        }


    }
}
