package zad23;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class DirectoryDisplay {

    public static void displayFiles(String directoryPath, String text) {
        Path dirPath = Paths.get(directoryPath);
        try (Stream<Path> fileStream = Files.list(dirPath)) {
            System.out.println("Files in directory:");

            boolean fileExists = fileStream
                    .peek(System.out::println)
                    .anyMatch(path -> path.getFileName().toString().contains(text));

            if (fileExists) {
                System.out.println("A file containing \"" + text + "\" exists in the directory.");
            } else {
                System.out.println("No file containing \"" + text + "\" exists in the directory.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred while accessing the directory.");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        displayFiles("/", "Wind");
    }
}
