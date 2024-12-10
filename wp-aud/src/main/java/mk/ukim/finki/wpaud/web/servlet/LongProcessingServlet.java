package mk.ukim.finki.wpaud.web.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "longProcessingServlet", urlPatterns = "/servlet/long-processing")
public class LongProcessingServlet extends HttpServlet
{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        PrintWriter writer = resp.getWriter();
        String processTime = req.getParameter("processTime");
        if (processTime == null) return;

        int processTimeInt = Integer.parseInt(processTime);

        System.out.println("Processing...");
        try
        {
            Thread.sleep(processTimeInt);
        } catch (InterruptedException e)
        {
            throw new RuntimeException(e);
        }

        writer.println("Processing done");
        System.out.println("Processing done");
    }
}
