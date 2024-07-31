
import model.Actor;
import model.Director;
import model.Movie;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws Exception {
        Actor actor = new Actor("Tom Hanks", "Drama");
        Movie movie = new Movie("Forrest Gump");

        actor.addLink("playedIn", "casted", movie);

        System.out.println("Movie associations:");
        System.out.println(Arrays.toString(actor.getLinks("playedIn")));

        System.out.println("\nActor associations:");
        System.out.println(Arrays.toString(movie.getLinks("casted")));

        Director director = new Director(actor, false);

        System.out.println("\n----- After transfer -----");

        System.out.println("\nDirector associations:");
        System.out.println(Arrays.toString(director.getLinks("playedIn")));

        System.out.println("\nActor associations:");
        try {
            System.out.println(Arrays.toString(actor.getLinks("playedIn")));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        System.out.println("\nMovie associations:");
        System.out.println(Arrays.toString(movie.getLinks("casted")));
    }
}
