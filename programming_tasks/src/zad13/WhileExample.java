package zad13;

public class WhileExample {

    public static void main(String[] args) {
        int i = 0;
        while (i < 5) {
            System.out.println("While loop: " + i);
            i++;
        }

        int j = 0;
        do {
            System.out.println("Do/while loop: " + j);
            j++;
        } while (j < 5);

        System.out.println("While loop will check the condition first and then execute the code block. Do/while loop will execute the code block first and then check the condition.");
    }
}
