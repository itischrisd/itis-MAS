import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class IntStorage {

    public static void main(String[] args) {
        int[] fixedSizeStorage = new int[]{1, 2, 3, 4, 5};
        List<Integer> variableSizeStorage = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));

        for (int e : fixedSizeStorage) {
            System.out.print(e + " ");
        }

        System.out.println("\n" + Arrays.toString(fixedSizeStorage));

        for (Integer e : variableSizeStorage) {
            System.out.print(e + " ");
        }

        System.out.println("\n" + variableSizeStorage);
    }
}
