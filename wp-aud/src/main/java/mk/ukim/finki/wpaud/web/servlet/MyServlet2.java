package mk.ukim.finki.wpaud.web.servlet;


import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

@WebServlet(name = "MYServlet2", urlPatterns = "/myservlet2", loadOnStartup = 2)
public class MyServlet2 extends HttpServlet
{
    public MyServlet2()
    {
//        System.out.println("My Servlet 2 const");
    }

    @Override
    public void init() throws ServletException
    {
//        System.out.println("My Servlet 2 init");
        super.init();

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        PrintWriter writer = resp.getWriter();
        ServletContext servletContext = this.getServletContext();

        writer.println("Print This");

//        ======You can not do this and then do requestDispatcher.forward=========
//        writer.flush();

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/myservlet");
        requestDispatcher.forward(req, resp);





        // ====== ova nema da se prikazhi, bidejki so forward mu se zatvora output stremot bez flush =====
        writer.printf("This is shown ");

//        Enumerations are the old version of Iterators
//        Enumeration<String> parameterNames = servletContext.getInitParameterNames();
//        parameterNames.asIterator().forEachRemaining(s->writer.println(s));

//        requestDispatcher.forward(req,resp);

        //Ovie linii se izbrshuvaat, no ne mozhe da se menuva outputstremot se zatvora za izvorniot servlet
        // Destinaciskiot servlet mozhe da prodolzhi da go ureduva resp, i toj direktno go isprakja na klientot

        // So .include izborniot servlet mozhe da prodolzhi so ureduvanje na odogovort. Destinaciskiot mozhe da go menuva samo
        //teloto no ne i headerite
        writer.println("This is not shown");
//        System.out.println("This is printed");
//        servletContext.removeAttribute("Visits");

    }

    @Override
    public void destroy()
    {
//        System.out.println("Destroying My Servlet 2 ");

    }
}
