/*
package mk.ukim.finki.mk.lab.web.servlet;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import mk.ukim.finki.mk.lab.model.Song;
import mk.ukim.finki.mk.lab.service.SongService;
import mk.ukim.finki.mk.lab.service.helper.CustomHandler;
import mk.ukim.finki.mk.lab.service.helper.WebContextBuilder;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "songServlet", urlPatterns = "/listSongs")
public class SongListServlet extends HttpServlet
{
    private final SongService songService;
    private final SpringTemplateEngine engine;

    public SongListServlet(SongService songService, SpringTemplateEngine engine)
    {
        this.songService = songService;
        this.engine = engine;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        ServletContext servletContext = this.getServletContext();
        WebContext context = WebContextBuilder.getContext(req, resp, servletContext);


        List<Song> songs = songService.listSongs();

        context.setVariable("songs", songs);

        engine.process("listSongs.html", context, resp.getWriter());


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        String trackId = req.getParameter("trackId");

        if (CustomHandler.isNullOrEmpty(trackId))
        {
            resp.sendRedirect("/listSongs");
            return;
        }

        HttpSession session = req.getSession();
        session.setAttribute("trackId", trackId);

        resp.sendRedirect("/artist");
    }
}
*/
