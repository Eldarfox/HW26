import model.Actor;
import model.Movie;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MovieService {

    private List<Movie> movies;

    public MovieService(List<Movie> movies) {
        this.movies = movies;
    }

    public List<Movie> searchByName(String query) {
        List<Movie> result = new ArrayList<>();
        for (Movie movie : movies) {
            if (movie.getName().toLowerCase().contains(query.toLowerCase())) {
                result.add(movie);
            }
        }
        return result;
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

    public Map<String, List<Movie>> getMoviesByActor() {
        Map<String, List<Movie>> result = new HashMap<>();
        for (Movie movie : movies) {
            for (Actor actor : movie.getCast()) {
                if (!result.containsKey(actor.getFullName())) {
                    result.put(actor.getFullName(), new ArrayList<>());
                }
                result.get(actor.getFullName()).add(movie);
            }
        }
        return result;
    }

    public Map<String, List<Movie>> getMoviesByDirector() {
        Map<String, List<Movie>> result = new HashMap<>();
        for (Movie movie : movies) {
            String director = movie.getDirector().getFullName();
            if (!result.containsKey(director)) {
                result.put(director, new ArrayList<>());
            }
            result.get(director).add(movie);
        }
        return result;
    }

    public Map<Integer, List<Movie>> getMoviesByYear() {
        Map<Integer, List<Movie>> result = new HashMap<>();
        for (Movie movie : movies) {
            int year = movie.getYear();
            if (!result.containsKey(year)) {
                result.put(year, new ArrayList<>());
            }
            result.get(year).add(movie);
        }
        return result;
    }

    public Map<String, Map<String, String>> getActorRoles() {
        Map<String, Map<String, String>> result = new HashMap<>();
        for (Movie movie : movies) {
            for (Actor actor : movie.getCast()) {
                if (!result.containsKey(actor.getFullName())) {
                    result.put(actor.getFullName(), new HashMap<>());
                }
                result.get(actor.getFullName())
                        .put(movie.getName(), actor.getRole());
            }
        }
        return result;
    }

    public Map<String, List<String>> getAllActorsSorted() {
        Map<String, List<String>> map = new HashMap<>();
        for (Movie movie : movies) {
            for (Actor actor : movie.getCast()) {
                if (!map.containsKey(actor.getFullName())) {
                    map.put(actor.getFullName(), new ArrayList<>());
                }
                map.get(actor.getFullName()).add(actor.getRole());
            }
        }

        List<String> names = new ArrayList<>(map.keySet());
        Collections.sort(names);

        Map<String, List<String>> result = new HashMap<>();
        for (String name : names) {
            result.put(name, map.get(name));
        }
        return result;
    }
}
