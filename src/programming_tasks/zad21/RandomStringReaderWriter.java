package programming_tasks.zad21;

import programming_tasks.zad2.RandomStringGenerator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class RandomStringReaderWriter {

    public static void main(String[] args) {
        String fileName = "randomStrings.txt";
        String encoding = "UTF-8";

        try (PrintWriter printWriter = new PrintWriter(fileName, encoding)) {
            for (int i = 0; i < 10; i++) {
                printWriter.println(RandomStringGenerator.randomString(10));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (Scanner scanner = new Scanner(new File(fileName), encoding)){
            while (scanner.hasNextLine()) {
                System.out.println(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
}
