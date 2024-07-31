package model;

import java.io.Serializable;

public record Dimensions(double length, double width, double height) implements Serializable {

    public static Dimensions copyOf(Dimensions dimensions) {
        return new Dimensions(dimensions.length, dimensions.width, dimensions.height);
    }

    @Override
    public String toString() {
        return "dimensions=" + length + "m x " + width + "m" + (height == 0 ? "" : " x " + height + "m");
    }
}
