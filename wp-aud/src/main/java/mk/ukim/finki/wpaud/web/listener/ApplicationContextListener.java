package mk.ukim.finki.wpaud.web.listener;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class ApplicationContextListener implements ServletContextListener
{
    @Override
    public void contextInitialized(ServletContextEvent sce)
    {
        ServletContext context = sce.getServletContext();
        context.setAttribute("categoriesPageVisits", 0);
//        context.setInitParameter("paramparam", 123);
        System.out.println("App Context Created");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce)
    {
        System.out.println("App Context Destroyed");

    }
}
