package programming_tasks.zad30;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class SetBenchmark extends ContainerBenchmark {

    protected final Set<Integer> list;

    public SetBenchmark(int collectionSize, int executions, int bound) {
        super(collectionSize, executions, bound);
        this.list = new HashSet<>();
        initialize();
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
