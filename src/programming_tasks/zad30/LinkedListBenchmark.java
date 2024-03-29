package programming_tasks.zad30;

import java.util.LinkedList;

public class LinkedListBenchmark extends ListBenchmark {

    public LinkedListBenchmark(int collectionSize, int executions, int bound) {
        super(collectionSize, executions, bound, new LinkedList<>());
        initialize();

    }
}
