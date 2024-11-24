/*
package mk.ukim.finki.mk.lab.web.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.ukim.finki.mk.lab.model.Artist;
import mk.ukim.finki.mk.lab.model.Song;
import mk.ukim.finki.mk.lab.service.ArtistService;
import mk.ukim.finki.mk.lab.service.SongService;
import mk.ukim.finki.mk.lab.service.helper.CustomHandler;
import mk.ukim.finki.mk.lab.service.helper.WebContextBuilder;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.MissingResourceException;
import java.util.Optional;

@WebServlet(name = "artistDetails", urlPatterns = "/artistDetails")
public class ArtistDetails extends HttpServlet
{

    private final ArtistService artistService;
    private final SongService songService;
    private final SpringTemplateEngine engine;

    public ArtistDetails(ArtistService artistService, SongService songService, SpringTemplateEngine engine)
    {
        this.artistService = artistService;
        this.songService = songService;
        this.engine = engine;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        String artistIdString = req.getParameter("artistId");

        if (CustomHandler.isNullOrEmpty(artistIdString))
        {
            resp.sendRedirect("/allArtists");
            return;
        }

        long artistId = Long.parseLong(artistIdString);

        List<Song> artistsSongs = null;

        try{
            artistsSongs = this.artistService.findArtistSongs(artistId);
        }
        catch (MissingResourceException e)
        {
            resp.sendRedirect("/allArtists");
            return;
        }

        Optional<Artist> artist = this.artistService.findById(artistId);

        WebContext context = WebContextBuilder.getContext(req, resp, this.getServletContext());
        context.setVariable("artisSongs", artistsSongs);
        context.setVariable("foundArtist", artist.get());

        engine.process("artistDetails.html", context, resp.getWriter());

    }
}
*/
