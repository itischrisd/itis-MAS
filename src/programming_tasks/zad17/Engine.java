package programming_tasks.zad17;

public abstract class Engine {

    public abstract void start();

    public static void main(String[] args) {
        Engine dieselEngine = new DieselEngine();
        dieselEngine.start();

        Engine petrolEngine = new PetrolEngine();
        petrolEngine.start();

        Engine electricEngine = new ElectricEngine();
        electricEngine.start();
    }
}
