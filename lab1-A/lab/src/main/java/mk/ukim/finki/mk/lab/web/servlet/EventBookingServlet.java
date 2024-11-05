package mk.ukim.finki.mk.lab.web.servlet;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.ukim.finki.mk.lab.model.EventBooking;
import mk.ukim.finki.mk.lab.model.helper.WebContextBuilder;
import mk.ukim.finki.mk.lab.service.EventBookingService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.io.IOException;

@WebServlet(name = "eventBooking", urlPatterns = "/eventBooking")
public class EventBookingServlet extends HttpServlet
{
    private final EventBookingService eventBookingService;
    private final SpringTemplateEngine engine;

    public EventBookingServlet(EventBookingService eventBookingService, SpringTemplateEngine engine)
    {
        this.eventBookingService = eventBookingService;
        this.engine = engine;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        resp.sendRedirect("");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        ServletContext servletContext = this.getServletContext();
        WebContext context = WebContextBuilder.getContext(req, resp, servletContext);



        String eventName = req.getParameter("eventName");
        String attendeeName = req.getParameter("attendeeName");
        String numTicketsString = req.getParameter("numTickets");
        String attendeeAddress = req.getRemoteAddr();

        if (eventName == null || attendeeName == null || numTicketsString == null)
        {
            resp.sendRedirect("");
            return;
        }

        int numTickets = Integer.parseInt(numTicketsString);

        EventBooking eventBooking = eventBookingService.placeBooking(eventName, attendeeName, attendeeAddress, numTickets);

        context.setVariable("eventBooking", eventBooking);

        engine.process("bookingConfirmation.html", context, resp.getWriter());

    }
}
