package zad8;

import java.util.Arrays;

public class ModifyIntegers {

    public static void main(String[] args) {
        int[] fixedSizeStorage = new int[]{1, 2, 3, 4, 5};

        for (int i = 0; i < fixedSizeStorage.length; i++) {
            fixedSizeStorage[i] += 1;
        }

        for (int e : fixedSizeStorage) {
            System.out.print(e + " ");
        }

        System.out.println();

        int delta = -2;

        fixedSizeStorage = Arrays.stream(fixedSizeStorage).map(e -> e + delta).peek(e -> System.out.print(e + " ")).toArray();

        System.out.println();

        Arrays.stream(fixedSizeStorage).forEach(e -> System.out.print(e + " "));
    }
}
