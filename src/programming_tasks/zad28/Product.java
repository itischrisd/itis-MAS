package programming_tasks.zad28;

import java.util.List;

public class Product {

    private final String name;
    private final double price;

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public static void main(String[] args) {
        List<Product> products = List.of(
                new Product("Laptop", 3000),
                new Product("Smartphone", 2000),
                new Product("Tablet", 1500),
                new Product("Smartwatch", 1000),
                new Product("Headphones", 500),
                new Product("Mouse", 100),
                new Product("Keyboard", 200),
                new Product("Monitor", 1000)
        );

        products.stream()
                .filter(product -> product.getPrice() > 1000)
                .map(Product::getName)
                .forEach(System.out::println);

        double sum = products.stream()
                .mapToDouble(Product::getPrice)
                .sum();

        System.out.println("Sum: " + sum);
        System.out.println("Sum is " + (sum > 5000 ? "greater" : "less") + " than 5000");

    }
}
