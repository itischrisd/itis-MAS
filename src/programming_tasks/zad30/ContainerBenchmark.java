package programming_tasks.zad30;

import java.util.*;

public abstract class ContainerBenchmark {

    protected Random random;
    protected int collectionSize;
    protected int executions;
    protected int bound;

    public ContainerBenchmark(int collectionSize, int executions, int bound) {
        this.random = new Random();
        this.collectionSize = collectionSize;
        this.executions = executions;
        this.bound = bound;
    }

    public abstract void initialize();

    public abstract List<Long> insert();

    public abstract List<Long> search();

    public abstract List<Long> delete();

    public static void main(String[] args) {
        int collectionSize = 2000000;
        int executions = 1000;
        int bound = 1000;
        List<ContainerBenchmark> containerBenchmarks = new ArrayList<>(Arrays.asList(
                new LinkedListBenchmark(collectionSize, executions, bound),
                new ArrayListBenchmark(collectionSize, executions, bound),
                new SetBenchmark(collectionSize, executions, bound),
                new MapBenchmark(collectionSize, executions, bound)
        ));

        for (ContainerBenchmark containerBenchmark : containerBenchmarks) {
            System.out.println(containerBenchmark.getClass().getSimpleName());
            List<Long> times = containerBenchmark.insert();
            System.out.println("Insert total time: " + times.stream().mapToLong(Long::longValue).sum());
            System.out.println("Insert average time: " + times.stream().mapToLong(Long::longValue).average().getAsDouble());
            System.out.println("Insert max time: " + times.stream().mapToLong(Long::longValue).max().getAsLong());
            System.out.println("Insert min time: " + times.stream().mapToLong(Long::longValue).min().getAsLong());
            System.out.println();
            times = containerBenchmark.search();
            System.out.println("Search total time: " + times.stream().mapToLong(Long::longValue).sum());
            System.out.println("Search average time: " + times.stream().mapToLong(Long::longValue).average().getAsDouble());
            System.out.println("Search max time: " + times.stream().mapToLong(Long::longValue).max().getAsLong());
            System.out.println("Search min time: " + times.stream().mapToLong(Long::longValue).min().getAsLong());
            System.out.println();
            times = containerBenchmark.delete();
            System.out.println("Delete total time: " + times.stream().mapToLong(Long::longValue).sum());
            System.out.println("Delete average time: " + times.stream().mapToLong(Long::longValue).average().getAsDouble());
            System.out.println("Delete max time: " + times.stream().mapToLong(Long::longValue).max().getAsLong());
            System.out.println("Delete min time: " + times.stream().mapToLong(Long::longValue).min().getAsLong());
            System.out.println();
        }
    }
}
