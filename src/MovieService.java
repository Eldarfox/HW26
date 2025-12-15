import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import model.Movie;

public class MovieService {

    private List<Movie> movies;

    public MovieService(List<Movie> movies) {
        this.movies = movies;
    }

    public List<Movie> sortByYearAsc() {
        List<Movie> copy = new ArrayList<>(movies);
        Collections.sort(copy, new Comparator<Movie>() {
            @Override
            public int compare(Movie a, Movie b) {
                return a.getYear() - b.getYear();
            }
        });
        return copy;
    }

    public List<Movie> sortByYearDesc() {
        List<Movie> copy = new ArrayList<>(movies);
        Collections.sort(copy, new Comparator<Movie>() {
            @Override
            public int compare(Movie a, Movie b) {
                return b.getYear() - a.getYear();
            }
        });
        return copy;
    }

    public List<Movie> sortByNameAsc() {
        List<Movie> copy = new ArrayList<>(movies);
        Collections.sort(copy, new Comparator<Movie>() {
            @Override
            public int compare(Movie a, Movie b) {
                return a.getName().compareTo(b.getName());
            }
        });
        return copy;
    }

    public List<Movie> sortByNameDesc() {
        List<Movie> copy = new ArrayList<>(movies);
        Collections.sort(copy, new Comparator<Movie>() {
            @Override
            public int compare(Movie a, Movie b) {
                return b.getName().compareTo(a.getName());
            }
        });
        return copy;
    }

    public List<Movie> sortByDirectorAsc() {
        List<Movie> copy = new ArrayList<>(movies);
        Collections.sort(copy, new Comparator<Movie>() {
            @Override
            public int compare(Movie a, Movie b) {
                return a.getDirector().getFullName()
                        .compareTo(b.getDirector().getFullName());
            }
        });
        return copy;
    }

    public List<Movie> sortByDirectorDesc() {
        List<Movie> copy = new ArrayList<>(movies);
        Collections.sort(copy, new Comparator<Movie>() {
            @Override
            public int compare(Movie a, Movie b) {
                return b.getDirector().getFullName()
                        .compareTo(a.getDirector().getFullName());
            }
        });
        return copy;
    }
}
