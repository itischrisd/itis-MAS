package zad9;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class SemesterCounter {

    private final LocalDateTime semesterStart;
    private final LocalDateTime semesterEnd;

    public SemesterCounter(LocalDateTime semesterStart, LocalDateTime semesterEnd) {
        this.semesterStart = semesterStart;
        this.semesterEnd = semesterEnd;
    }

    public static void main(String[] args) {
        LocalDateTime semesterStart = LocalDateTime.of(2024, 3, 1, 0, 0);
        LocalDateTime semesterEnd = LocalDateTime.of(2024, 6, 23, 0, 0);
        SemesterCounter counter = new SemesterCounter(semesterStart, semesterEnd);
        System.out.println("Days from start of semester: " + counter.daysFromStart());
        System.out.println("Days to end of semester: " + counter.daysToEnd());
    }

    public long daysFromStart() {
        LocalDateTime now = LocalDateTime.now();
        return ChronoUnit.DAYS.between(semesterStart, now);
    }

    public long daysToEnd() {
        LocalDateTime now = LocalDateTime.now();
        return ChronoUnit.DAYS.between(now, semesterEnd);
    }

}
