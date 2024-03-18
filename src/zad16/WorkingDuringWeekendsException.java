package zad16;

public class WorkingDuringWeekendsException extends Exception {

    public WorkingDuringWeekendsException(String message) {
        super(message);
    }

    public static void main(String[] args) {
        try {
            if (java.time.LocalDate.now().getDayOfWeek().getValue() > 5) {
                throw new WorkingDuringWeekendsException("You are working during weekends!");
            }
        } catch (WorkingDuringWeekendsException e) {
            e.printStackTrace();
        }
    }
}
