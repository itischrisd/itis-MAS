package model.employee;

public abstract class Worker {

    private Employee employee;

    public Worker(Employee employee) {
        setEmployee(employee);
    }

    public Employee getEmployee() {
        return employee;
    }

    private void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
