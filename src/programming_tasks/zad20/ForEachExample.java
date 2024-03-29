package programming_tasks.zad20;

import java.util.Arrays;

public class ForEachExample {

    public static void main(String[] args) {
        String[] names = {"John", "Paul", "George", "Ringo"};
        for (String name : names) {
            System.out.println(name);
        }

        System.out.println("But that was actually, as per Oracle documentation, an enhanced for loop, not a for-each loop.");

        Arrays.stream(names).forEach(System.out::println);

        System.out.println("That's a for-each loop.");
    }
}
