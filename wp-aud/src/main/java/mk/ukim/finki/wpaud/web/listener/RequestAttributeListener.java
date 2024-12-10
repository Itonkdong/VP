package mk.ukim.finki.wpaud.web.listener;

import jakarta.servlet.ServletRequestAttributeEvent;
import jakarta.servlet.ServletRequestAttributeListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class RequestAttributeListener implements ServletRequestAttributeListener
{
    @Override
    public void attributeAdded(ServletRequestAttributeEvent srae)
    {
        System.out.printf("Added request attribute %s with value %s\n", srae.getName(), srae.getValue());

    }

    @Override
    public void attributeRemoved(ServletRequestAttributeEvent srae)
    {
        System.out.printf("Removed request attribute %s with value %s\n", srae.getName(), srae.getValue());

    }

    @Override
    public void attributeReplaced(ServletRequestAttributeEvent srae)
    {
        System.out.printf("Updated request attribute %s with value %s\n", srae.getName(), srae.getValue());

    }
}
