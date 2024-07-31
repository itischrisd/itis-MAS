package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SectionTest {

    private Section section;

    @BeforeEach
    void setUp() {
        section = new Section("Section");
    }

    @Test
    void canGetName() {
        assertEquals("Section", section.getName());
    }

    @Test
    void canSetName() {
        section.setName("New name");
        assertEquals("New name", section.getName());
    }

    @Test
    void cannotSetNullName() {
        assertThrows(IllegalArgumentException.class, () -> section.setName(null));
    }

    @Test
    void cannotSetBlankName() {
        assertThrows(IllegalArgumentException.class, () -> section.setName(" "));
    }
}
