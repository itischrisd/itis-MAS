package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CompositionTest {

    private Section section;
    private Alley alley;

    @BeforeEach
    void setUp() {
        ExtensiatedObject.clearExtents();
        section = new Section("Section");
        alley = Alley.createAlley(1, section);
    }

    @Test
    void alleyIsAddedToSectionUponCreation() {
        assertTrue(section.getAlleys().contains(alley));
        assertSame(section, alley.getSection());
    }

    @Test
    void canRemoveAlleyFromSection() {
        section.removeAlley(alley);
        assertFalse(section.getAlleys().contains(alley));
        assertNull(alley.getSection());
        assertThrows(ClassNotFoundException.class, () -> ExtensiatedObject.getExtent(Alley.class).contains(alley));
    }

    @Test
    void cannotAddNullAlleyToSection() {
        assertThrows(IllegalArgumentException.class, () -> section.addAlley(null));
    }

    @Test
    void cannotRemoveNullAlleyFromSection() {
        assertThrows(IllegalArgumentException.class, () -> section.removeAlley(null));
    }

    @Test
    void cannotCreateAlleyWithNullSection() {
        assertThrows(IllegalArgumentException.class, () -> Alley.createAlley(1, null));
    }

    @Test
    void removingAlleyFromSectionTwiceHasNoEffect() {
        Alley alley2 = Alley.createAlley(2, section);
        section.removeAlley(alley);
        section.removeAlley(alley);
        assertFalse(section.getAlleys().contains(alley));
        assertNull(alley.getSection());
        assertTrue(section.getAlleys().contains(alley2));
        assertSame(section, alley2.getSection());
        assertEquals(1, section.getAlleys().size());
    }

    @Test
    void cannotAddAlleyToTwoSections() {
        Section section2 = new Section("Section 2");
        assertThrows(IllegalArgumentException.class, () -> section2.addAlley(alley));
    }

    @Test
    void canRemoveAlley() {
        alley.removeAlley();
        assertFalse(section.getAlleys().contains(alley));
        assertNull(alley.getSection());
        assertThrows(ClassNotFoundException.class, () -> ExtensiatedObject.getExtent(Alley.class).contains(alley));
    }

    @Test
    void addingAlleyToSectionTwiceHasNoEffect() {
        section.addAlley(alley);
        assertEquals(1, section.getAlleys().size());
        assertSame(section, alley.getSection());
    }
}
