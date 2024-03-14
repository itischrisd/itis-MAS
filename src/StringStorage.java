import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class StringStorage {

    private final List<String> strings;

    public StringStorage(List<String> strings) {
        this.strings = strings;
    }

    public void display() {
        for (String string : strings) {
            System.out.print(string + " ");
        }
        System.out.println();
    }

    public void removeRandom() {
        Random random = new Random();
        int toRemove = random.nextInt(strings.size());

        for(; toRemove > 0; toRemove--) {
            strings.remove(random.nextInt(strings.size()));
        }
    }

    public static void main(String[] args) {
        List<String> strings = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            strings.add(RandomStringGenerator.generate(10));
        }

        StringStorage stringStorage = new StringStorage(strings);
        stringStorage.display();
        stringStorage.removeRandom();
        stringStorage.display();
    }
}
