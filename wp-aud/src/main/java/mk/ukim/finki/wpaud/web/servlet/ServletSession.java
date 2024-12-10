package mk.ukim.finki.wpaud.web.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "MyServlet3", urlPatterns = "/servlet/session")
public class ServletSession extends HttpServlet
{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        HttpSession session = req.getSession();
        PrintWriter writer = resp.getWriter();
        if (session.isNew())
        {
            writer.printf("New Session with ID: %s\n", session.getId());
            writer.printf("Created At: %s\n", session.getCreationTime());

            session.setAttribute("Visits", 0);

            //=============Vo sekundi==================
            session.setMaxInactiveInterval(5);

//            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/myservlet");
//            requestDispatcher.forward(req,resp);
        }

        int visits = (int) session.getAttribute("Visits");
        session.setAttribute("Visits", ++visits);
        writer.printf("Number of visits = %d\n",visits);
        writer.printf("Last time accesses session at %s", session.getLastAccessedTime());

        if (visits == 3)
        {
            session.invalidate();
        }



    }
}
