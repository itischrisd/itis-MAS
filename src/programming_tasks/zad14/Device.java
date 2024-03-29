package programming_tasks.zad14;

import java.util.List;

public class Device {

    private final String name;
    private final String producer;

    public Device(String name, String producer) {
        this.name = name;
        this.producer = producer;
    }

    public static void main(String[] args) {
        List<Device> devices = List.of(
                new Television("Bravia", "Sony", 55),
                new MobilePhone("Galaxy", "Samsung", "Android"),
                new Laptop("MacBook", "Apple", 16),
                new Television("OLED", "LG", 65),
                new MobilePhone("iPhone", "Apple", "iOS"),
                new Laptop("ThinkPad", "Lenovo", 8)
        );

        for (Device device : devices) {
            System.out.println(device);
        }
    }

    public String getName() {
        return name;
    }

    public String getProducer() {
        return producer;
    }

    @Override
    public String toString() {
        return "Device " + getName() + " is made by " + getProducer();
    }
}

