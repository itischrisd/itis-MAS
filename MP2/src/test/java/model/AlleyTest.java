package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class AlleyTest {

    private Alley alley;
    private Section section;

    @BeforeEach
    void setUp() {
        ExtensiatedObject.clearExtents();
        section = new Section("Section");
        alley = Alley.createAlley(1, section);
    }

    @Test
    void cannotSetNegativeNumber() {
        assertThrows(IllegalArgumentException.class, () -> alley.setNumber(-1));
    }

    @Test
    void cannotSetZeroNumber() {
        assertThrows(IllegalArgumentException.class, () -> alley.setNumber(0));
    }

    @Test
    void cannotSetNumberThatAlreadyExists() {
        assertThrows(IllegalArgumentException.class, () -> Alley.createAlley(1, section));
    }
}
