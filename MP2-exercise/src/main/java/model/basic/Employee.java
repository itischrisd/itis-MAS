package model.basic;

public class Employee {

    private String lastName;
    private Department worksIn;

    public Employee(String lastName) {
        this.lastName = lastName;
    }

    public String getLastName() {
        return lastName;
    }

    public Department getWorksIn() {
        return worksIn;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setWorksIn(Department newDepartment) {
        if (worksIn == newDepartment) {
            return;
        }
        if (worksIn == null) {
            newWorksIn(newDepartment);
        } else if (newDepartment == null) {
            removeWorksIn();
        } else {
            replaceWorksIn(newDepartment);
        }
    }

    private void newWorksIn(Department newDepartment) {
        worksIn = newDepartment;
        worksIn.addEmployee(this);
    }

    private void removeWorksIn() {
        worksIn.removeEmployee(this);
        worksIn = null;
    }

    private void replaceWorksIn(Department newDepartment) {
        worksIn.removeEmployee(this);
        worksIn = newDepartment;
        newDepartment.addEmployee(this);
    }
}
