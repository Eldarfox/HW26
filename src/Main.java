import model.Movie;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        List<Movie> movies = JsonLoader.loadMovies();
        MovieService service = new MovieService(movies);
        Scanner sc = new Scanner(System.in);

        boolean run = true;

        while (run) {
            System.out.println("\n===== MENU =====");
            System.out.println("1 - Show all movies");
            System.out.println("2 - Sort movies");
            System.out.println("3 - Movies by actor");
            System.out.println("4 - Movies by director");
            System.out.println("5 - Movies by year");
            System.out.println("6 - Actors and roles");
            System.out.println("0 - Exit");
            System.out.print("Choose: ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:
                    for (Movie m : movies) {
                        System.out.println(m);
                    }
                    break;

                case 2:
                    sortMenu(sc, service);
                    break;

                case 3:
                    Map<String, List<Movie>> byActor = service.getMoviesByActor();
                    for (String actor : byActor.keySet()) {
                        System.out.println(actor + ":");
                        for (Movie m : byActor.get(actor)) {
                            System.out.println("  " + m.getName());
                        }
                    }
                    break;

                case 4:
                    Map<String, List<Movie>> byDirector = service.getMoviesByDirector();
                    for (String director : byDirector.keySet()) {
                        System.out.println(director + ":");
                        for (Movie m : byDirector.get(director)) {
                            System.out.println("  " + m.getName());
                        }
                    }
                    break;

                case 5:
                    Map<Integer, List<Movie>> byYear = service.getMoviesByYear();
                    for (Integer year : byYear.keySet()) {
                        System.out.println(year + ":");
                        for (Movie m : byYear.get(year)) {
                            System.out.println("  " + m.getName());
                        }
                    }
                    break;

                case 6:
                    Map<String, List<String>> actors = service.getAllActorsSorted();
                    for (String actor : actors.keySet()) {
                        System.out.println(actor + " -> " + actors.get(actor));
                    }
                    break;

                case 0:
                    run = false;
                    break;

                default:
                    System.out.println("Wrong choice");
            }
        }
    }

    private static void sortMenu(Scanner sc, MovieService service) {

        System.out.println("Sort by:");
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

        for (Movie m : result) {
            System.out.println(m);
        }
    }
}
