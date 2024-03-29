package programming_tasks.zad30;

import java.util.*;

public class MapBenchmark extends ContainerBenchmark {

    protected final Map<Integer, Integer> list;

    public MapBenchmark(int collectionSize, int executions, int bound) {
        super(collectionSize, executions, bound);
        this.list = new HashMap<>();
        initialize();
    }

    @Override
    public void initialize() {
        for (int i = 0; i < collectionSize; i++) {
            this.list.put(random.nextInt(bound), random.nextInt(bound));
        }
    }

    @Override
    public List<Long> insert() {
        List<Long> times = new LinkedList<>();
        long start, end;
        for (int i = 0; i < executions; i++) {
            start = System.nanoTime();
            this.list.put(random.nextInt(bound), random.nextInt(bound));
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
            this.list.containsKey(random.nextInt(bound));
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
            this.list.remove(random.nextInt(bound));
            end = System.nanoTime();
            times.add(end - start);
        }
        return times;
    }
}
