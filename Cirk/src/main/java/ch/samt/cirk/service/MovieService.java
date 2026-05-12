package ch.samt.cirk.service;

import ch.samt.cirk.data.DirectorRepository;
import ch.samt.cirk.data.MovieRepository;
import ch.samt.cirk.domain.Director;
import ch.samt.cirk.domain.Movie;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {
    private final MovieRepository movieRepository;

    @Autowired
    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }
    public Movie getDirectorById(long id){
        return movieRepository.getById(id);
    }
    public void save(@Valid Movie movie) {
        try{
            movieRepository.save(movie);
        } catch(Exception e){
            e.printStackTrace();
        }
    }
}
