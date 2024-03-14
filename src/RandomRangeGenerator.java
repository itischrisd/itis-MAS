import java.util.Random;

public class RandomRangeGenerator {

    public static int[] generate(int origin, int bound) {
        int[] randomNumbers = new int[10];
        Random random = new Random();

        for (int i = 0; i < randomNumbers.length; i++) {
            randomNumbers[i] = random.nextInt(bound - origin + 1) + origin;
        }

        return randomNumbers;
    }

    public static int min(int[] numbers) {
        int min = numbers[0];
        for (int number : numbers) {
            if (number < min) {
                min = number;
            }
        }
        return min;
    }

    public static int max(int[] numbers) {
        int max = numbers[0];
        for (int number : numbers) {
            if (number > max) {
                max = number;
            }
        }
        return max;
    }

    public static void main(String[] args) {
        int origin = 1;
        int bound = 10;
        int[] randomNumbers = generate(origin, bound);

        for (int number : randomNumbers) {
            System.out.print(number + " ");
        }

        System.out.println("\nMin: " + min(randomNumbers));
        System.out.println("Max: " + max(randomNumbers));
    }
}
