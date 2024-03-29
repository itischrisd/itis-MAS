package programming_tasks.zad25;

import java.util.ArrayList;
import java.util.List;

public class DiamondNotationExample<T> {

    private final T value;
    private final List<Integer> integers;

    public DiamondNotationExample(T value) {
        this.value = value;
        this.integers = new ArrayList<>();
    }

    public T getValue() {
        return value;
    }

    public <U> U echo(U value) {
        System.out.println("Methods can also use diamond notation");
        return value;
    }



    public static void main(String[] args) {
        DiamondNotationExample<String> stringExample = new DiamondNotationExample<>("Hello");
        System.out.println(stringExample.getValue());
        System.out.println(stringExample.echo("World"));

        DiamondNotationExample<Integer> integerExample = new DiamondNotationExample<>(42);
        System.out.println(integerExample.getValue());
        System.out.println(integerExample.echo(24));
    }
}
