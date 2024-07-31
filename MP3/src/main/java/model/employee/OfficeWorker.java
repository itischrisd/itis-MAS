package model.employee;

public class OfficeWorker extends Worker {

    private boolean isEligibleForGlassesRefund;

    private OfficeWorker(Employee employee, boolean isEligibleForGlassesRefund) {
        super(employee);
        setEligibleForGlassesRefund(isEligibleForGlassesRefund);
    }

    public static OfficeWorker createOfficeWorker(Employee employee, boolean isEligibleForGlassesRefund) {
        if (employee == null) {
            throw new IllegalArgumentException("Employee cannot be null");
        }
        if (Employee.getExtent().contains(employee)) {
            throw new IllegalArgumentException("Employee already exists");
        }
        return new OfficeWorker(employee, isEligibleForGlassesRefund);
    }

    public boolean isEligibleForGlassesRefund() {
        return isEligibleForGlassesRefund;
    }

    public void setEligibleForGlassesRefund(boolean isEligibleForGlassesRefund) {
        this.isEligibleForGlassesRefund = isEligibleForGlassesRefund;
    }
}
