package model;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Employee extends ExtensiatedObject {

    private static int lastIdSuffix = 1;
    private final Set<Section> sections = new HashSet<>();
    private final String cardNumber;
    private String name;
    private String surname;

    public Employee(String name, String surname) {
        super();
        setName(name);
        setSurname(surname);
        this.cardNumber = "EMP" + String.format("%05d", lastIdSuffix++);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        if (surname == null || surname.isBlank()) {
            throw new IllegalArgumentException("Surname cannot be null or empty");
        }
        this.surname = surname;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public Set<Section> getSections() {
        return Collections.unmodifiableSet(sections);
    }

    public void addSection(Section section) {
        if (section == null) {
            throw new IllegalArgumentException("Cannot add null section");
        }
        if (!sections.contains(section)) {
            sections.add(section);
            section.addEmployee(this);
        }
    }

    public void removeSection(Section section) {
        if (section == null) {
            throw new IllegalArgumentException("Cannot remove null section");
        }
        if (sections.contains(section)) {
            sections.remove(section);
            section.removeEmployee(this);
        }
    }
}
