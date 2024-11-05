package mk.ukim.finki.mk.lab.web.servlet;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.ukim.finki.mk.lab.model.Event;
import mk.ukim.finki.mk.lab.model.helper.WebContextBuilder;
import mk.ukim.finki.mk.lab.service.EventService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@WebServlet(name = "eventList", urlPatterns = "")
public class EventListServlet extends HttpServlet
{
    private final EventService eventService;
    private final SpringTemplateEngine engine;


    public EventListServlet(EventService eventService, SpringTemplateEngine engine)
    {
        this.eventService = eventService;
        this.engine = engine;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        ServletContext servletContext = this.getServletContext();
        WebContext context = WebContextBuilder.getContext(req, resp, servletContext);

        String filterName = req.getParameter("filterName");
        String filterRatingString = req.getParameter("filterRating");

        List<Event> events = this.eventService.filterEvents(filterName, filterRatingString);

        context.setVariable("events", events);

        this.engine.process("listEvents.html", context, resp.getWriter());
    }
}
