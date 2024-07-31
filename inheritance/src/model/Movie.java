package model;

import util.ObjectPlusPlus;

public class Movie extends ObjectPlusPlus {
    private final String title;

    public Movie(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return "Movie with title: " + title;
    }
}
