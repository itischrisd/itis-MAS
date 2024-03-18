package zad1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class IntStorage {

    public static void main(String[] args) {
        List<Integer> numbers = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        numbers.add(6);
        numbers.remove(3);
        for (Integer number : numbers) {
            System.out.println(number);
        }

        int[] numbersArray = {1, 2, 3, 4, 5};
        int[] newNumbersArray = new int[numbersArray.length + 1];
        System.arraycopy(numbersArray, 0, newNumbersArray, 0, numbersArray.length);
        newNumbersArray[numbersArray.length] = 6;
        numbersArray = newNumbersArray;
        int[] newNumbersArray2 = new int[numbersArray.length - 1];
        System.arraycopy(numbersArray, 0, newNumbersArray2, 0, newNumbersArray2.length);
        numbersArray = newNumbersArray2;
        for (int number : numbersArray) {
            System.out.println(number);
        }
    }
}
