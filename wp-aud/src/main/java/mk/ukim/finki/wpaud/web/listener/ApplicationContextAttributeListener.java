package mk.ukim.finki.wpaud.web.listener;

import jakarta.servlet.ServletContextAttributeEvent;
import jakarta.servlet.ServletContextAttributeListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class ApplicationContextAttributeListener implements ServletContextAttributeListener
{
    @Override
    public void attributeAdded(ServletContextAttributeEvent scae)
    {

//        System.out.printf("Added application attribute %s with value %s\n", scae.getName(), scae.getValue());
    }

    @Override
    public void attributeRemoved(ServletContextAttributeEvent scae)
    {
//        System.out.printf("Removed application attribute %s with value %s\n", scae.getName(), scae.getValue());

    }

    @Override
    public void attributeReplaced(ServletContextAttributeEvent scae)
    {
//        System.out.printf("Updated application attribute %s with value %s\n", scae.getName(), scae.getValue());

    }
}
