package programming_tasks.zad12;

import java.util.Random;

public class SwitchExample {

    public static void main(String[] args) {
        Random random = new Random();
        int number = random.nextInt(1, 5);
        String numberName;
        switch (number) {
            case 1:
                numberName = "one";
                break;
            case 2:
                numberName = "two";
                break;
            case 3:
                numberName = "three";
                break;
            default:
                numberName = "greater than three";
                break;
        }
        System.out.println("Number " + number + " is " + numberName);

        number = random.nextInt(1, 5);
        numberName = switch (number) {
            case 1 -> "one";
            case 2 -> "two";
            case 3 -> "three";
            default -> "greater than three";
        };
        System.out.println("Number " + number + " is " + numberName);
    }
}
