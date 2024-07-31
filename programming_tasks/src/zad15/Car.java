package zad15;

public class Car implements Movement {

    private final String name;
    private final String producer;

    public Car(String name, String producer) {
        this.name = name;
        this.producer = producer;
    }

    public String getName() {
        return name;
    }

    public String getProducer() {
        return producer;
    }

    @Override
    public void move() {
        System.out.println("Car " + getName() + " is driving");
    }
}
