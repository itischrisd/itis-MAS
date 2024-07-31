package util;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@SuppressWarnings("ALL")
public class ObjectPlusPlusPlus {

    private final Map<String, Object> attributes = new HashMap<>();
    private final Map<String, Function> behaviors = new HashMap<>();

    public <T, V> V behave(String behavior, T... args) {
        if (!behaviors.containsKey(behavior)) {
            throw new IllegalArgumentException("Object does not support this behavior");
        }
        return (V) behaviors.get(behavior).apply(args);
    }

    public <T> void set(String attribute, T value) {
        if (!attributes.containsKey(attribute)) {
            throw new IllegalArgumentException("Object does not have this attribute");
        }
        attributes.put(attribute, value);
    }

    public <T> T get(String attribute) {
        if (!attributes.containsKey(attribute)) {
            throw new IllegalArgumentException("Object does not have this attribute");
        }
        return (T) attributes.get(attribute);
    }

    public enum Behavior {
        PRACUJ, POBIERZ_EMERYTURE, ZDAJ_SESJE, SPIJ
    }

    public enum Attribute {
        IMIE, WIEK, PENSJA, NR_INDEKSU
    }

    private static final Map<String, EnumSet> allowedBehaviorCombinations = Map.of(
            "Pracownik", EnumSet.of(Behavior.PRACUJ, Behavior.SPIJ),
            "Student", EnumSet.of(Behavior.ZDAJ_SESJE, Behavior.SPIJ),
            "Emeryt", EnumSet.of(Behavior.POBIERZ_EMERYTURE, Behavior.SPIJ)
    );

    private static final Map<String, EnumSet> allowedAttributeCombinations = Map.of(
            "Pracownik", EnumSet.of(Attribute.IMIE, Attribute.WIEK, Attribute.PENSJA),
            "Student", EnumSet.of(Attribute.IMIE, Attribute.WIEK, Attribute.NR_INDEKSU),
            "Emeryt", EnumSet.of(Attribute.IMIE, Attribute.WIEK)
    );
}