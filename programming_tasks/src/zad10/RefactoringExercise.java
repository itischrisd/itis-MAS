package zad10;

public class RefactoringExercise {

    private String name;
    private int age;
    private String city;

    public RefactoringExercise(String name, int age, String city) {
        this.name = name;
        this.age = age;
        this.city = city;
    }

    public void printDetails() {
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("City: " + city);
        double discount = 0;
        if (age > 60) {
            discount = 0.1;
        } else if (age > 50) {
            discount = 0.05;
        }
        System.out.println("Discount: " + discount * 100 + "%");
    }
}