/*
package mk.ukim.finki.mk.lab.web.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.ukim.finki.mk.lab.model.Artist;
import mk.ukim.finki.mk.lab.service.ArtistService;
import mk.ukim.finki.mk.lab.service.helper.CustomHandler;
import mk.ukim.finki.mk.lab.service.helper.WebContextBuilder;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "allArtists", urlPatterns = "/allArtists")
public class AllArtists extends HttpServlet
{
    private final ArtistService artistService;

    private final SpringTemplateEngine engine;

    public AllArtists(ArtistService artistService, SpringTemplateEngine engine)
    {
        this.artistService = artistService;
        this.engine = engine;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        WebContext context = WebContextBuilder.getContext(req, resp, this.getServletContext());

        List<Artist> artists = this.artistService.listAll();
        context.setVariable("allArtists", artists);

        this.engine.process("allArtists.html", context, resp.getWriter());


    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        String artistId = req.getParameter("artistId");

        if (CustomHandler.isNullOrEmpty(artistId))
        {
            resp.sendRedirect("/allArtists");
            return;
        }

        resp.sendRedirect(String.format("/artistDetails?artistId=%s", artistId));
    }
}
*/
