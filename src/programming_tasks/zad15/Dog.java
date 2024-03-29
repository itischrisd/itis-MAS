package programming_tasks.zad15;

public class Dog implements Movement {

    private final String name;
    private final String breed;

    public Dog(String name, String breed) {
        this.name = name;
        this.breed = breed;
    }

    public String getName() {
        return name;
    }

    public String getBreed() {
        return breed;
    }

    @Override
    public void move() {
        System.out.println("Dog " + getName() + " is running");
    }
}
