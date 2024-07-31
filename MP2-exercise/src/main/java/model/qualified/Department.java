package model.qualified;

public class Department {
    private String name;
    private Company company;

    public Department(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company newCompany) {
        if (this.company == newCompany) {
            return;
        }
        if (this.company == null) {
            this.company = newCompany;
            newCompany.addDepartment(this);
        }
        if (newCompany == null) {
            this.company.removeDepartment(this);
            this.company = null;
        }
    }
}
