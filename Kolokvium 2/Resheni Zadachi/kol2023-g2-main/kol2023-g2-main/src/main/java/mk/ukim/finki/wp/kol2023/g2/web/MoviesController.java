package mk.ukim.finki.wp.kol2023.g2.web;

import mk.ukim.finki.wp.kol2023.g2.model.Director;
import mk.ukim.finki.wp.kol2023.g2.model.Genre;
import mk.ukim.finki.wp.kol2023.g2.model.Movie;
import mk.ukim.finki.wp.kol2023.g2.service.DirectorService;
import mk.ukim.finki.wp.kol2023.g2.service.MovieService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


@Controller
@RequestMapping({"/", "/movies"})
public class MoviesController
{

    private final MovieService movieService;

    private final DirectorService directorService;

    public MoviesController(MovieService movieService, DirectorService directorService)
    {
        this.movieService = movieService;
        this.directorService = directorService;
    }

    /**
     * This method should use the "list.html" template to display all movies.
     * The method should be mapped on paths '/' and '/movies'.
     * The arguments that this method takes are optional and can be 'null'.
     * In the case when the arguments are not passed (both are 'null') all movies should be displayed.
     * If one, or both of the arguments are not 'null', the movies that are the result of the call
     * to the method 'listMoviesWithRatingGreaterThenAndGenre' from the MovieService should be displayed.
     *
     * @param rating
     * @param genre
     * @return The view "list.html".
     */
    @GetMapping()
    public String showMovies(
            @RequestParam(required = false) Double rating,
            @RequestParam(required = false) Genre genre,
            Model model

    )
    {
        List<Movie> movies;

        if (rating == null && genre == null)
        {
            movies = this.movieService.listAllMovies();
        }
        else
        {
            movies = this.movieService.listMoviesWithRatingLessThenAndGenre(rating, genre);
        }

        model.addAttribute("rating", rating);
        model.addAttribute("genre", genre);

        List<Genre> genres = Arrays.stream(Genre.values()).collect(Collectors.toList());

        model.addAttribute("genres", genres);
        model.addAttribute("movies", movies);

        return "list.html";
    }

    /**
     * This method should display the "form.html" template.
     * The method should be mapped on path '/movies/add'.
     *
     * @return The view "form.html".
     */

    @GetMapping("/add")
    public String showAdd(Model model)
    {

         List<Genre> genres = Arrays.stream(Genre.values()).collect(Collectors.toList());

        model.addAttribute("genres", genres);

        List<Director> directors = this.directorService
                .listAll();

        model.addAttribute("directors", directors);
        return "form.html";
    }

    /**
     * This method should display the "form.html" template.
     * However, in this case all 'input' elements should be filled with the appropriate value for the movie that is updated.
     * The method should be mapped on path '/movies/[id]/edit'.
     *
     * @return The view "form.html".
     */

    @GetMapping("/{id}/edit")
    public String showEdit(@PathVariable Long id, Model model)
    {
        Movie movie = this.movieService.findById(id);

        model.addAttribute("movie", movie);

        List<Genre> genres = Arrays.stream(Genre.values()).collect(Collectors.toList());

        model.addAttribute("genres", genres);

        List<Director> directors = this.directorService
                .listAll();

        model.addAttribute("directors", directors);


        return "form.html";
    }

    /**
     * This method should create a movie given the arguments it takes.
     * The method should be mapped on path '/movies'.
     * After the movie is created, all movies should be displayed.
     *
     * @return The view "list.html".
     */

    @PostMapping()
    public String create(String name, String description, Double rating, Genre genre, Long director)
    {
        Movie movie = this.movieService.create(name, description, rating, genre, director);
        return "redirect:/movies";
    }

    /**
     * This method should update a movie given the arguments it takes.
     * The method should be mapped on path '/movies/[id]'.
     * After the movie is updated, all movies should be displayed.
     *
     * @return The view "list.html".
     */
    @PostMapping("/{id}")
    public String update(@PathVariable Long id, String name, String description, Double rating, Genre genre, Long director)
    {
        this.movieService.update(id, name, description, rating, genre, director);
        return "redirect:/movies";
    }

    /**
     * This method should delete the movie that has the appropriate identifier.
     * The method should be mapped on path '/movies/[id]/delete'.
     * After the movie is deleted, all movies should be displayed.
     *
     * @return The view "list.html".
     */
    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id)
    {
        this.movieService.delete(id);
        return "redirect:/movies";
    }

    /**
     * This method should increase the number of votes of the appropriate movie by 1.
     * The method should be mapped on path '/movies/[id]/vote'.
     * After the operation, all movies should be displayed.
     *
     * @return The view "list.html".
     */
    @PostMapping("/{id}/vote")
    public String vote(@PathVariable Long id)
    {
        this.movieService.vote(id);
        return "redirect:/movies";
    }
}
