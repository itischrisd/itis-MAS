package programming_tasks.zad6;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UniformPrism {

    private final double sidesCount;
    private final double sideLength;
    private final double height;

    public UniformPrism(double sidesCount, double sideLength, double height) {
        this.sidesCount = sidesCount;
        this.sideLength = sideLength;
        this.height = height;
    }

    public static void main(String[] args) {
        List<UniformPrism> prisms = new ArrayList<>(Arrays.asList(
                new UniformPrism(3, 2, 3),
                new UniformPrism(4, 2, 3),
                new UniformPrism(5, 2, 3),
                new UniformPrism(6, 2, 3),
                new UniformPrism(7, 2, 3),
                new UniformPrism(8, 2, 3),
                new UniformPrism(9, 2, 3),
                new UniformPrism(10, 2, 3)
        ));

        for (UniformPrism prism : prisms) {
            System.out.println(prism);
        }
    }

    public double volume() {
        return sidesCount * Math.pow(sideLength, 2) * height / (4 * Math.tan(Math.PI / sidesCount));
    }

    public double sideSurfaceArea() {
        return sidesCount * sideLength * height;
    }

    public double surfaceArea() {
        return sidesCount * Math.pow(sideLength, 2) / (2 * Math.tan(Math.PI / sidesCount)) + sideSurfaceArea();
    }

    @Override
    public String toString() {
        return "UniformPrism with " + sidesCount + " sides of length " + sideLength + " and height " + height + " has:\n"
                + "Volume: " + volume() + "\n"
                + "Surface area: " + surfaceArea() + "\n"
                + "Side surface area: " + sideSurfaceArea();
    }
}
