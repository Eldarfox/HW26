import model.Movie;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        List<Movie> movies = JsonLoader.loadMovies();
        MovieService service = new MovieService(movies);

        System.out.println("=== ALL MOVIES ===");
        for (Movie movie : movies) {
            System.out.println(movie);
        }

        System.out.println("=== SORT BY YEAR (ASC) ===");
        for (Movie movie : service.sortByYearAsc()) {
            System.out.println(movie);
        }

        System.out.println("=== SORT BY YEAR (DESC) ===");
        for (Movie movie : service.sortByYearDesc()) {
            System.out.println(movie);
        }
    }
}
