package mk.ukim.finki.wpaud.web.listener;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletRequestEvent;
import jakarta.servlet.ServletRequestListener;
import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpServletRequest;
import mk.ukim.finki.wpaud.model.User;

@WebListener
public class RequestListener implements ServletRequestListener
{


    @Override
    public void requestInitialized(ServletRequestEvent sre)
    {
        ServletRequest servletRequest = sre.getServletRequest();

        //Ima reference i do servletContext
        ServletContext servletContext = sre.getServletContext();

        ServletRequest servletRequest1 = (HttpServletRequest) sre.getServletRequest();
        servletRequest1.setAttribute("attr", 1);

        Object reqAtr = servletRequest.getAttribute("ReqAtr");
        if (reqAtr == null)
        {
//            System.out.println("Created an request, with not attr");

        }
        else
        {
//            System.out.printf("Created an request, with attr: %s\n", reqAtr.toString());

        }

        sre.getServletRequest().setAttribute("requestUser", new User("request", "request"));

    }

    @Override
    public void requestDestroyed(ServletRequestEvent sre)
    {
//        System.out.println("Request Destroyed");
    }
}
