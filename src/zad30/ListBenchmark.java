package zad30;

import java.util.LinkedList;
import java.util.List;

public class ListBenchmark extends ContainerBenchmark {

    protected final List<Integer> list;

    public ListBenchmark(int collectionSize, int executions, int bound, List<Integer> list) {
        super(collectionSize, executions, bound);
        this.list = list;
    }

    @Override
    public void initialize() {
        for (int i = 0; i < collectionSize; i++) {
            this.list.add(random.nextInt(bound));
        }
    }

    @Override
    public List<Long> insert() {
        List<Long> times = new LinkedList<>();
        long start, end;
        for (int i = 0; i < executions; i++) {
            start = System.nanoTime();
            this.list.add(random.nextInt(bound));
            end = System.nanoTime();
            times.add(end - start);
        }
        return times;
    }

    @Override
    public List<Long> search() {
        List<Long> times = new LinkedList<>();
        long start, end;
        for (int i = 0; i < executions; i++) {
            start = System.nanoTime();
            this.list.contains(random.nextInt(bound));
            end = System.nanoTime();
            times.add(end - start);
        }
        return times;
    }

    @Override
    public List<Long> delete() {
        List<Long> times = new LinkedList<>();
        long start, end;
        for (int i = 0; i < executions; i++) {
            start = System.nanoTime();
            this.list.remove(random.nextInt(list.size()));
            end = System.nanoTime();
            times.add(end - start);
        }
        return times;
    }
}
