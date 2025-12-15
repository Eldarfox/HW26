import model.Movie;
import java.util.List;
import java.util.Map;

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
        System.out.println("\n=== MOVIES BY ACTOR ===");
        Map<String, List<Movie>> byActor = service.getMoviesByActor();
        for (String actor : byActor.keySet()) {
            System.out.println(actor + ": " + byActor.get(actor).size());
        }

        System.out.println("\n=== ACTORS AND ROLES ===");
        Map<String, List<String>> actors = service.getAllActorsSorted();
        for (String actor : actors.keySet()) {
            System.out.println(actor + " -> " + actors.get(actor));
        }
    }
}
