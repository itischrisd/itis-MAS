package model;

public class Director extends Person {
    private final boolean isSpielberg;

    public Director(String name, boolean isSpielberg) {
        super(name);
        this.isSpielberg = isSpielberg;
    }

    public Director(Actor actor, boolean isSpielberg) {
        super(actor.getName());
        actor.transferLinks(this);
        this.isSpielberg = isSpielberg;
    }

    public boolean isSpielberg() {
        return isSpielberg;
    }

    @Override
    public String toString() {
        return super.toString() + " that is a Director" + (isSpielberg ? " and is Spielberg" : " and is not Spielberg");
    }
}
