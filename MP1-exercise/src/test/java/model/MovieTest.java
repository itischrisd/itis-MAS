package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MovieTest {

    private Movie movie;

    @BeforeEach
    void setUp() {
        movie = new Movie("The Godfather");
    }

    @Test
    void createSuccessfully() {
        assertEquals("The Godfather", movie.getTitle());
    }

    @Test
    void createWithNullTitle() {
        assertThrows(IllegalArgumentException.class, () -> new Movie(null));
    }

    @Test
    void createWithEmptyTitle() {
        assertThrows(IllegalArgumentException.class, () -> new Movie(" "));
    }

    @Test
    void setTitleSuccessfully() {

        movie.setTitle("The Godfather: Part II");

        assertEquals("The Godfather: Part II", movie.getTitle());
    }

    @Test
    void setTitleWithNull() {
        assertThrows(IllegalArgumentException.class, () -> movie.setTitle(null));
    }

    @Test
    void setTitleWithEmptyString() {
        assertThrows(IllegalArgumentException.class, () -> movie.setTitle(" "));
    }
}