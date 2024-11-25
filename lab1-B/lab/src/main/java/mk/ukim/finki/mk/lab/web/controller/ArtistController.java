package mk.ukim.finki.mk.lab.web.controller;

import mk.ukim.finki.mk.lab.model.Artist;
import mk.ukim.finki.mk.lab.service.ArtistService;
import mk.ukim.finki.mk.lab.service.helper.CustomHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ArtistController
{
    private final ArtistService artistService;

    public ArtistController(ArtistService artistService)
    {
        this.artistService = artistService;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/artists")
    private String getArtistPage(@RequestParam(required = false) String trackId, @RequestParam(required = false) String artistName ,Model model)
    {
        if (CustomHandler.isNullOrEmpty(trackId))
        {
            return CustomHandler.sendRedirect("/songs", "You must select song with a valid track Id.");
        }

//        List<Artist> artists = this.artistService.listAll();
        List<Artist> artists = this.artistService.findArtistsByName(artistName);

        model.addAttribute("artists", artists);
        model.addAttribute("trackId", trackId);

        return "listArtists";

    }

}
