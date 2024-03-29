package programming_tasks.zad15;

import java.util.List;

public interface Movement {

    void move();

    static void main(String[] args) {
        List<Movement> movements = List.of(
            new Dog("Buddy", "Golden Retriever"),
            new Bird("Tweety", "Canary"),
            new Car("Civic", "Honda")
        );

        for (Movement movement : movements) {
            movement.move();
        }
    }
}
