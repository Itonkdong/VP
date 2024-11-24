/*
package mk.ukim.finki.mk.lab.web.servlet;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import mk.ukim.finki.mk.lab.model.Artist;
import mk.ukim.finki.mk.lab.model.Song;
import mk.ukim.finki.mk.lab.repository.InMemoryArtistRepository;
import mk.ukim.finki.mk.lab.repository.InMemorySongRepository;
import mk.ukim.finki.mk.lab.service.ArtistService;
import mk.ukim.finki.mk.lab.service.SongService;
import mk.ukim.finki.mk.lab.service.helper.CustomHandler;
import mk.ukim.finki.mk.lab.service.helper.WebContextBuilder;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.function.LongPredicate;

@WebServlet(name = "artist", urlPatterns = "/artist")
public class ArtistServlet extends HttpServlet
{
    private final SongService songService;
    private final ArtistService artistService;
    private final SpringTemplateEngine engine;

    public ArtistServlet(SongService songService, ArtistService artistService, SpringTemplateEngine engine)
    {
        this.songService = songService;
        this.artistService = artistService;
        this.engine = engine;
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        HttpSession session = req.getSession();
        Object trackIdObj =  session.getAttribute("trackId");


        if (trackIdObj == null)
        {
            resp.sendRedirect("/listSongs");
            return;
        }
        String trackId = (String) trackIdObj;

        if (CustomHandler.isNullOrEmpty(trackId))
        {
            resp.sendRedirect("/listSongs");
            return;
        }

        ServletContext servletContext = this.getServletContext();
        WebContext context = WebContextBuilder.getContext(req, resp, servletContext);

        List<Artist> artists = this.artistService.listAll();

        context.setVariable("artists", artists);
        context.setVariable("trackId", trackId);

        engine.process("listArtists.html", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        String artistIdString = req.getParameter("artistId");

        if (CustomHandler.isNullOrEmpty(artistIdString))
        {
            resp.sendRedirect("/artist");
            return;
        }

        long artistId = Long.parseLong(artistIdString);


        Optional<Artist> artist = artistService.findById(artistId);

        if (artist.isEmpty())
        {
            resp.sendRedirect("/artist");
            return;
        }

        String trackId = (String) req.getSession().getAttribute("trackId");

        Optional<Song> song = songService.findByTrackId(trackId);

        if (song.isEmpty())
        {
            resp.sendRedirect("/listSongs");
            return;
        }

        songService.addArtistToSong(artist.get(), song.get());

        resp.sendRedirect(String.format("/songDetails?trackId=%s", trackId));
    }
}
*/
