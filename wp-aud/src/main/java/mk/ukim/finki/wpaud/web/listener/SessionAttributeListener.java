package mk.ukim.finki.wpaud.web.listener;

import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpSessionAttributeListener;
import jakarta.servlet.http.HttpSessionBindingEvent;

@WebListener
public class SessionAttributeListener implements HttpSessionAttributeListener
{
    @Override
    public void attributeAdded(HttpSessionBindingEvent se)
    {
        //Ima reference i do session
        HttpSession session = se.getSession();

//        System.out.printf("Added session attribute %s with value %s\n", se.getName(), se.getValue());

    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent se)
    {
//        System.out.printf("Added session attribute %s with value %s\n", se.getName(), se.getValue());

    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent se)
    {
//        System.out.printf("Added session attribute %s with value %s\n", se.getName(), se.getValue());

    }
}
