import model.Actor;
import model.Movie;
import model.MovieAssociations;

public class Main {

    public static void main(String[] args) {
        Actor actor = new Actor();
        Movie movie = new Movie();
        movie.addLink(MovieAssociations.MovieRoles.STARRED, actor);
        System.out.println(movie.getLinks(MovieAssociations.MovieRoles.STARRED));
    }
}
