package zad30;

import java.util.ArrayList;

public class ArrayListBenchmark extends ListBenchmark {

    public ArrayListBenchmark(int collectionSize, int executions, int bound) {
        super(collectionSize, executions, bound, new ArrayList<>());
        initialize();
    }
}
