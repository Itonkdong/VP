package mk.ukim.finki.mk.lab.web.controller;

import mk.ukim.finki.mk.lab.model.Album;
import mk.ukim.finki.mk.lab.model.Artist;
import mk.ukim.finki.mk.lab.model.Song;
import mk.ukim.finki.mk.lab.service.AlbumService;
import mk.ukim.finki.mk.lab.service.ArtistService;
import mk.ukim.finki.mk.lab.service.SongService;
import mk.ukim.finki.mk.lab.service.helper.CustomHandler;
import mk.ukim.finki.mk.lab.service.helper.Result;
import mk.ukim.finki.mk.lab.service.helper.WebContextBuilder;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.context.WebContext;

import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(path = "/songs")
public class SongController
{
    private final SongService songService;
    private final AlbumService albumService;
    private final ArtistService artistService;

    public SongController(SongService songService, AlbumService albumService, ArtistService artistService)
    {
        this.songService = songService;
        this.albumService = albumService;
        this.artistService = artistService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String getSongsPage(@RequestParam(required = false) String error, Model model)
    {
        model.addAttribute("error", error);
        List<Song> allSongs = songService.listSongs();
        model.addAttribute("allSongs", allSongs);

        return "listSongs";
    }

    @RequestMapping(method = RequestMethod.GET,path = "/add-form")
    public String getAddSongPage(Model model)
    {
        List<Album> allAlbums = this.albumService.findAll();

        model.addAttribute("allAlbums", allAlbums);


        return "add-song";
    }

    @RequestMapping(method = RequestMethod.GET,path = "/edit-form/{id}")
    public String getEditSongForm(@PathVariable Long id, Model model)
    {
        List<Album> allAlbums = this.albumService.findAll();
        Optional<Song> songOptional = this.songService.findSongById(id);
        if (songOptional.isEmpty())
        {
            String errorMessage = URLEncoder.encode(String.format("Song with id: %d does not exist", id), StandardCharsets.UTF_8);
            return String.format("redirect:/songs?error=%s", errorMessage);
        }
        Song song = songOptional.get();

        model.addAttribute("editSong", song);
        model.addAttribute("allAlbums", allAlbums);

        return "add-song";
    }


    @RequestMapping(method = RequestMethod.POST,path = "/add")
    public String saveSong(@RequestParam() String title,
                           @RequestParam() String trackId,
                           @RequestParam() String genre,
                           @RequestParam() Integer releaseYear,
                           @RequestParam() Long albumId)
    {

        Result<Song> result = this.songService.addSong(title, trackId, genre, releaseYear, albumId);

        if (!result.isSuccessful())
        {
            String errorMessage = URLEncoder.encode(result.getErrorMessage(), StandardCharsets.UTF_8);
            return String.format("redirect:/songs?error=%s", errorMessage);
        }

        return "redirect:/songs";
    }

    @RequestMapping(method = RequestMethod.POST, path = "/edit/{songId}")
    public String editSong(@PathVariable Long songId,
                           @RequestParam() String title,
                           @RequestParam() String trackId,
                           @RequestParam() String genre,
                           @RequestParam() Integer releaseYear,
                           @RequestParam() Long albumId)
    {
        try
        {
            Song updatedSong = this.songService.editSong(songId, title, trackId, genre, releaseYear, albumId);
        }
        catch (RuntimeException e)
        {
            return CustomHandler.sendRedirect("/songs", e.getMessage());
        }

        return CustomHandler.sendRedirect("/songs",null);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/delete/{id}")
    public String deleteSong(@PathVariable Long id)
    {
        Song deleteSong;
        try
        {
          deleteSong = this.songService.deleteSong(id);
        }
        catch (RuntimeException e)
        {
            return CustomHandler.sendRedirect("/songs", e.getMessage());
        }

        return CustomHandler.sendRedirect("/songs", null);
    }

    @RequestMapping(method = RequestMethod.POST, path = "/add-artist-to-song")
    public String addArtistToSong(@RequestParam(required = false) String trackId, @RequestParam(required = false) Long artistId)
    {

        if (CustomHandler.isNullOrEmpty(trackId))
        {
            return CustomHandler.sendRedirect("/songs", "You must select song with a valid track Id.");
        }

        if (artistId == null)
        {
            return String.format("redirect:/artists?trackId=%s", trackId);
        }


        Optional<Artist> artist = artistService.findById(artistId);

        if (artist.isEmpty())
        {
            return "redirect:/artists";
        }

        Optional<Song> song = songService.findByTrackId(trackId);

        if (song.isEmpty())
        {
            return "redirect:/songs";
        }

        songService.addArtistToSong(artist.get(), song.get());

        return String.format("redirect:/songs/song-details?trackId=%s", trackId);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/song-details")
    public String songDetails(@RequestParam(required = false) String trackId, Model model)
    {
        if (CustomHandler.isNullOrEmpty(trackId))
        {
            return CustomHandler.sendRedirect("/songs", "You must select song with a valid track Id.");
        }

        Optional<Song> song = songService.findByTrackId(trackId);

        if (song.isEmpty())
        {
            return CustomHandler.sendRedirect("/songs", "You must select song with a valid track Id.");
        }

        model.addAttribute("song", song.get());

        return "details-song";
    }


//    @RequestMapping(path = "/songs/add")
//    public String saveSong(@ModelAttribute Song song)
//    {
//
//        song.
//
//        String title1 = song.getTitle();
//        return "listSongs";
//    }
}
