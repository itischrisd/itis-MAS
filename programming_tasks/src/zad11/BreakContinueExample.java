package zad11;

public class BreakContinueExample {

    public static void main(String[] args) {
        int[] integers = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

        System.out.println("Odd numbers (we skip even numbers by continue): ");

        for (int integer : integers) {
            if (integer % 2 == 0) {
                continue;
            }
            System.out.print(integer + " ");
        }

        System.out.println("\n\nNumbers before first multiple of 5 (we stop when we find it by break): ");

        for (int integer : integers) {
            if (integer % 5 == 0) {
                break;
            }
            System.out.print(integer + " ");
        }

    }
}
