package programming_tasks.zad18;

public class AverageTemperature {

    private final double[] temperatures;

    public AverageTemperature() {
        this.temperatures = new double[12];
    }

    public static void main(String[] args) {
        AverageTemperature averageTemperature = new AverageTemperature();
        averageTemperature.setAverageTemperature(1, -2.5);
        averageTemperature.setAverageTemperature(2, -1.5);
        averageTemperature.setAverageTemperature(3, 2.5);
        averageTemperature.setAverageTemperature(4, 8.5);
        averageTemperature.setAverageTemperature(5, 15.5);
        averageTemperature.setAverageTemperature(6, 20.5);
        averageTemperature.setAverageTemperature(7, 22.5);
        averageTemperature.setAverageTemperature(8, 22.5);
        averageTemperature.setAverageTemperature(9, 17.5);
        averageTemperature.setAverageTemperature(10, 10.5);
        averageTemperature.setAverageTemperature(11, 3.5);
        averageTemperature.setAverageTemperature(12, -1.5);

        for (int i = 1; i <= 12; i++) {
            System.out.println("Average temperature in month " + i + " is " + averageTemperature.getAverageTemperature(i));
        }
    }

    public void setAverageTemperature(int month, double temperature) {
        temperatures[month - 1] = temperature;
    }

    public double getAverageTemperature(int month) {
        return temperatures[month - 1];
    }
}
