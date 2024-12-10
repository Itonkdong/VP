package mk.ukim.finki.mk.lab.web.controller;

import mk.ukim.finki.mk.lab.model.Album;
import mk.ukim.finki.mk.lab.model.Song;
import mk.ukim.finki.mk.lab.service.AlbumService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller()
public class AlbumController
{
    private final AlbumService albumService;

    public AlbumController(AlbumService albumService)
    {
        this.albumService = albumService;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/albums")
    public String showAlbumsPage(Model model)
    {
        List<Album> allAlbums = albumService.findAll();
        model.addAttribute("allAlbums", allAlbums);
        return "albums";
    }

    @RequestMapping(method = RequestMethod.GET, path = "/filteredSongs")
    public String showFilteredSongs(@RequestParam(required = false) Long albumId, @RequestParam(required = false) Integer releaseYear, Model model)
    {

        Album selectedAlbum =null;

        if (albumId != null)
        {
            selectedAlbum = albumService.findById(albumId);
        }
        List<Song> filteredSongs = albumService.filterSongs(albumId, releaseYear);

        model.addAttribute("selectedAlbum", selectedAlbum);
        model.addAttribute("filteredSongs", filteredSongs);
        return "filtered-songs";
    }
}
