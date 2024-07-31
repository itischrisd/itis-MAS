package model;

import util.ObjectPlusPlus;

abstract class Person extends ObjectPlusPlus {
    private final String name;

    public Person(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Person with name: " + name;
    }
}
