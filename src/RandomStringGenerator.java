import java.util.Random;

public class RandomStringGenerator {

    public static String generate(int length) {
        StringBuilder sb = new StringBuilder(length);
        Random random = new Random();

        for (int i = 0; i < length; i++) {
            sb.append((char) (random.nextInt(26) + 'a'));
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        int lengthLimit = 15;

        for (int i = 1; i < lengthLimit; i++) {
            System.out.println("For length " + i + " random string is: " + generate(i));
        }
    }
}
