import java.util.List;

public  class Prism {

    private final double height;
    private final double baseArea;

    public Prism(double height, double baseArea) {
        this.height = height;
        this.baseArea = baseArea;
    }

    public double volume() {
        return height * baseArea;
    }

    @Override
    public String toString() {
        return "Prism has volume: " + volume();
    }

    public static void main(String[] args) {
        Prism[] fixedSizeStorage = new Prism[]{
                new Prism(1, 2),
                new Prism(2, 3),
                new Prism(3, 4),
                new Prism(4, 5),
                new Prism(5, 6)
        };

        for (Prism prism : fixedSizeStorage) {
            System.out.println(prism);
        }

        List<Prism> variableSizeStorage = List.of(
                new Prism(1, 2),
                new Prism(2, 3),
                new Prism(3, 4),
                new Prism(4, 5),
                new Prism(5, 6)
        );

        for (Prism prism : variableSizeStorage) {
            System.out.println(prism);
        }
    }
}
