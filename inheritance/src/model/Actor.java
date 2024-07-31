package model;

public class Actor extends Person {
    private final String typicalGenre;

    public Actor(String name, String typicalGenre) {
        super(name);
        this.typicalGenre = typicalGenre;
    }

    public Actor(Director director, String typicalGenre) {
        super(director.getName());
        director.transferLinks(this);
        this.typicalGenre = typicalGenre;
    }

    public String getTypicalGenre() {
        return typicalGenre;
    }

    @Override
    public String toString() {
        return super.toString() + " that is an Actor from genre: " + typicalGenre;
    }
}
