package mk.ukim.finki.wpaud.web.servlet;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebInitParam;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.web.servlet.IServletWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "MYServlet",
        urlPatterns = {"/myservlet"},
        loadOnStartup = 1,
        initParams = {
        @WebInitParam(name = "ServletParam1", value = "Viktor")
})
public class MYServlet extends HttpServlet
{

    public MYServlet()
    {
//        System.out.println("My Servlet 1 const");

    }

    @Override
    public void init(ServletConfig config) throws ServletException
    {
//        System.out.println("My Servlet 1 init");
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        this.getServletConfig().getInitParameter("ServletParam1");
        PrintWriter writer = resp.getWriter();
        writer.println("Hello word");

        HttpSession session = req.getSession();
        if (session.isNew())
        {
            writer.println("New Session");
        } else
        {
            writer.println("Session is not new");
        }

/*
        =====Context and request attributes=====
        Object myLove =  this.getServletContext().getAttribute("Name");
        String reqAttribute = (String)  req.getAttribute("ReqAtr");
        writer.println(reqAttribute);
        writer.println();
*/

//        ======Request ima reference i do servlet (aplicakciski) context
//        ServletContext servletContext = req.getServletContext();


    }

    @Override
    public void destroy()
    {
//        System.out.println("Destroying My Servlet 1 ");

    }
}
