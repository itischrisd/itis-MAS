package model.employee;

import java.time.LocalDate;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Employee {

    private static final Set<Employee> extent = new HashSet<>();
    private static final HashMap<String, Employee> allEmployees = new HashMap<>();
    private static final Set<String> availablePositions = new HashSet<>();
    private static int nextCardNumber = 1;
    private String firstName;
    private String lastName;
    private String cardNumber;
    private String PESEL;
    private String position;
    private LocalDate hireDate;
    private String phoneNumber;
    private Worker worker;

    public Employee(String firstName, String lastName, String PESEL, String position, LocalDate hireDate, String phoneNumber, Boolean isEligibleForGlassesRefund) {
        setFirstName(firstName);
        setLastName(lastName);
        generateCardNumber();
        setPESEL(PESEL);
        setPosition(position);
        setHireDate(hireDate);
        setPhoneNumber(phoneNumber);

        if (isEligibleForGlassesRefund == null) {
            worker = ManualWorker.createManualWorker(this);
        } else {
            worker = OfficeWorker.createOfficeWorker(this, isEligibleForGlassesRefund);
        }

        allEmployees.put(cardNumber, this);
        extent.add(this);
    }

    public static void addAvailablePosition(String position) {
        if (position == null || position.isBlank()) {
            throw new IllegalArgumentException("Position cannot be null or empty");
        }
        availablePositions.add(position);
    }

    public static void removeAvailablePosition(String position) {
        if (position == null || position.isBlank()) {
            throw new IllegalArgumentException("Position cannot be null or empty");
        }
        if (!availablePositions.contains(position)) {
            throw new IllegalArgumentException("Position is not available");
        }
        if (allEmployees.values().stream().anyMatch(e -> e.getPosition().equals(position))) {
            throw new IllegalArgumentException("Cannot remove position that is currently assigned to an employee");
        }
        if (availablePositions.size() == 1) {
            throw new IllegalArgumentException("Cannot remove last available position");
        }
        availablePositions.remove(position);
    }

    public static Employee getEmployeeByCardNumber(String cardNumber) {
        return allEmployees.get(cardNumber);
    }

    public static Set<String> getAvailablePositions() {
        return Collections.unmodifiableSet(availablePositions);
    }

    public static Set<Employee> getExtent() {
        return Collections.unmodifiableSet(extent);
    }

    private void generateCardNumber() {
        this.cardNumber = "EMP" + String.format("%05d", nextCardNumber++);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        if (firstName == null || firstName.isBlank()) {
            throw new IllegalArgumentException("First name cannot be null or empty");
        }
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        if (lastName == null || lastName.isBlank()) {
            throw new IllegalArgumentException("Last name cannot be null or empty");
        }
        this.lastName = lastName;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getPESEL() {
        return PESEL;
    }

    public void setPESEL(String PESEL) {
        if (PESEL == null || PESEL.isBlank()) {
            throw new IllegalArgumentException("PESEL cannot be null or empty");
        }
        if (PESEL.length() != 11) {
            throw new IllegalArgumentException("PESEL must be 11 characters long");
        }
        this.PESEL = PESEL;
    }

    public LocalDate getBirthDate() {
        return LocalDate.of(Integer.parseInt(PESEL.substring(0, 2)) + 1900, Integer.parseInt(PESEL.substring(2, 4)), Integer.parseInt(PESEL.substring(4, 6)));
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        if (position == null || position.isBlank()) {
            throw new IllegalArgumentException("Position cannot be null or empty");
        }
        if (!availablePositions.contains(position)) {
            throw new IllegalArgumentException("Position is not available");
        }
        this.position = position;
    }

    public LocalDate getHireDate() {
        return hireDate;
    }

    public void setHireDate(LocalDate hireDate) {
        if (hireDate == null) {
            throw new IllegalArgumentException("Hire date cannot be null");
        }
        this.hireDate = hireDate;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        if (phoneNumber == null || phoneNumber.isBlank()) {
            throw new IllegalArgumentException("Phone number cannot be null or empty");
        }
        if (!phoneNumber.matches("[0-9]{9}")) {
            throw new IllegalArgumentException("Phone number must be at least 9 characters long");
        }
        this.phoneNumber = phoneNumber;
    }

    private void setWorker(Worker worker) {
        if (worker == null) {
            throw new IllegalArgumentException("Cannot set null worker");
        }
        this.worker = worker;
    }

    public OfficeWorker asOfficeWorker() {
        if (isOfficeWorker()) {
            return (OfficeWorker) worker;
        } else {
            throw new IllegalArgumentException("Employee is not an office worker");
        }
    }

    public ManualWorker asManualWorker() {
        if (isManualWorker()) {
            return (ManualWorker) worker;
        } else {
            throw new IllegalArgumentException("Employee is not a manual worker");
        }
    }

    public boolean isOfficeWorker() {
        return worker instanceof OfficeWorker;
    }

    public boolean isManualWorker() {
        return worker instanceof ManualWorker;
    }
}
