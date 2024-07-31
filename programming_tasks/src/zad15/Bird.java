package zad15;

public class Bird implements Movement {

    private final String name;
    private final String species;

    public Bird(String name, String species) {
        this.name = name;
        this.species = species;
    }

    public String getName() {
        return name;
    }

    public String getSpecies() {
        return species;
    }

    @Override
    public void move() {
        System.out.println("Bird " + getName() + " is flying");
    }
}
