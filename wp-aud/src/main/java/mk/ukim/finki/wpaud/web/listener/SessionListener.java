package mk.ukim.finki.wpaud.web.listener;

import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionIdListener;
import jakarta.servlet.http.HttpSessionListener;

@WebListener
public class SessionListener implements HttpSessionListener
{


    //Sesijata se kreira prviot pat koga se pristapuva, pri shto Servlet kontenjerot vo responsnot stava header 'Set-cookie:JSSESSIONID:ID koga kje se kreira sessijata'
    @Override
    public void sessionCreated(HttpSessionEvent se)
    {
        HttpSession session = se.getSession();
//        System.out.printf("CREATED NEW SESSION WITH ID %S\n", session.getId());
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se)
    {
        HttpSession session = se.getSession();
//        System.out.printf("DESTROYED SESSION WITH ID %S\n", session.getId());
    }
}
