package zad14;

public class Laptop extends Device {

    private final int ram;

    public Laptop(String name, String type, int ram) {
        super(name, type);
        this.ram = ram;
    }

    public int getRam() {
        return ram;
    }

    @Override
    public String toString() {
        return "Laptop " + getName() + " is made by " + getProducer() + " and has " + getRam() + " GB RAM";
    }
}
