package zad19;

public class Generics<T> {

    private T t;

    public static void main(String[] args) {
        Generics<Integer> integerBox = new Generics<>();
        Generics<String> stringBox = new Generics<>();

        integerBox.set(10);
        stringBox.set("Hello World");

        System.out.println("Wartość w integerBox: " + integerBox.get());
        System.out.println("Wartość w stringBox: " + stringBox.get());
    }

    public void set(T t) {
        this.t = t;
    }

    public T get() {
        return t;
    }

}
