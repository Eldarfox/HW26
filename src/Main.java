import model.Movie;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        List<Movie> movies = null;
        MovieService service = null;

        boolean run = true;

        while (run) {
            System.out.println("=========== MOVIE APPLICATION ===========");
            System.out.println("1 - Load movies from file");
            System.out.println("2 - Show all movies");
            System.out.println("3 - Search movie by name");
            System.out.println("4 - Sort movies");
            System.out.println("5 - Movies by actor");
            System.out.println("6 - Movies by director");
            System.out.println("7 - Movies by year");
            System.out.println("8 - Actors and their roles");
            System.out.println("0 - Exit");
            System.out.print("Choose option: ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:
                    movies = JsonLoader.loadMovies();
                    service = new MovieService(movies);
                    System.out.println("Movies loaded successfully!");
                    break;

                case 2:
                    if (checkLoaded(service)) {
                        printMovies(movies);
                    }
                    break;

                case 3:
                    if (checkLoaded(service)) {
                        System.out.print("Enter movie name: ");
                        String query = sc.nextLine();
                        printMovies(service.searchByName(query));
                    }
                    break;

                case 4:
                    if (checkLoaded(service)) {
                        sortMenu(sc, service);
                    }
                    break;

                case 5:
                    if (checkLoaded(service)) {
                        Map<String, List<Movie>> byActor = service.getMoviesByActor();
                        for (String actor : byActor.keySet()) {
                            System.out.println("\n" + actor + ":");
                            for (Movie m : byActor.get(actor)) {
                                System.out.println("  - " + m.getName());
                            }
                        }
                    }
                    break;

                case 6:
                    if (checkLoaded(service)) {
                        Map<String, List<Movie>> byDirector = service.getMoviesByDirector();
                        for (String director : byDirector.keySet()) {
                            System.out.println("\n" + director + ":");
                            for (Movie m : byDirector.get(director)) {
                                System.out.println("  - " + m.getName());
                            }
                        }
                    }
                    break;

                case 7:
                    if (checkLoaded(service)) {
                        Map<Integer, List<Movie>> byYear = service.getMoviesByYear();
                        for (Integer year : byYear.keySet()) {
                            System.out.println("\n" + year + ":");
                            for (Movie m : byYear.get(year)) {
                                System.out.println("  - " + m.getName());
                            }
                        }
                    }
                    break;

                case 8:
                    if (checkLoaded(service)) {
                        Map<String, List<String>> actors = service.getAllActorsSorted();
                        for (String actor : actors.keySet()) {
                            System.out.println(actor + " -> " + actors.get(actor));
                        }
                    }
                    break;

                case 0:
                    run = false;
                    break;

                default:
                    System.out.println("Wrong option. Try again.");
            }
        }
    }

    private static boolean checkLoaded(MovieService service) {
        if (service == null) {
            System.out.println("Please load movies first!");
            return false;
        }
        return true;
    }

    private static void sortMenu(Scanner sc, MovieService service) {

        System.out.println("\nSort by:");
        System.out.println("1 - Year");
        System.out.println("2 - Name");
        System.out.println("3 - Director");
        System.out.print("Choose: ");

        int type = sc.nextInt();
        sc.nextLine();

        System.out.println("1 - Ascending");
        System.out.println("2 - Descending");
        System.out.print("Choose order: ");

        int order = sc.nextInt();
        sc.nextLine();

        List<Movie> result = null;

        if (type == 1 && order == 1) result = service.sortByYearAsc();
        if (type == 1 && order == 2) result = service.sortByYearDesc();
        if (type == 2 && order == 1) result = service.sortByNameAsc();
        if (type == 2 && order == 2) result = service.sortByNameDesc();
        if (type == 3 && order == 1) result = service.sortByDirectorAsc();
        if (type == 3 && order == 2) result = service.sortByDirectorDesc();

        if (result == null) {
            System.out.println("Wrong choice");
            return;
        }

        printMovies(result);
    }

    private static void printMovies(List<Movie> movies) {
        if (movies.isEmpty()) {
            System.out.println("No movies found.");
            return;
        }

        for (Movie m : movies) {
            System.out.println(m);
        }
    }
}
