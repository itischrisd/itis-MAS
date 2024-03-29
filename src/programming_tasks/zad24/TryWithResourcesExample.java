package programming_tasks.zad24;

import java.io.FileOutputStream;

public class TryWithResourcesExample {

    public static void main(String[] args) {
        try (FileOutputStream resource = new FileOutputStream("file.txt")) {
            System.out.println(resource.getFD() + " is open");
            System.out.println("Why did I open this file?");
            System.out.println("To try it... with resources 😎");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
