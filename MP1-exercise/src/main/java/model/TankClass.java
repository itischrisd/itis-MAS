package model;

public enum TankClass {
    LIGHT("Light"),
    MEDIUM("Medium"),
    HEAVY("Heavy"),
    TANK_DESTROYER("Tank Destroyer"),
    SELF_PROPELLED_GUN("Self Propelled Gun");

    private final String name;

    TankClass(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
