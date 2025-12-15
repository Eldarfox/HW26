import com.google.gson.Gson;
import model.Movie;
import java.io.FileReader;
import java.util.List;

public class JsonLoader {
    public static List<Movie> loadMovies() {
        try {
            Gson gson = new Gson();
            FileReader reader = new FileReader("src/movies.json");

            MoviesWrapper wrapper =
                    gson.fromJson(reader, MoviesWrapper.class);

            return wrapper.movies;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static class MoviesWrapper {
        List<Movie> movies;
    }
}
