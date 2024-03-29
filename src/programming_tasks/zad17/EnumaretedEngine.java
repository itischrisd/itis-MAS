package programming_tasks.zad17;

public class EnumaretedEngine {

    private final EngineType engineType;

    public EnumaretedEngine(EngineType engineType) {
        this.engineType = engineType;
    }

    public static void main(String[] args) {
        EnumaretedEngine dieselEngine = new EnumaretedEngine(EngineType.DIESEL);
        dieselEngine.start();

        EnumaretedEngine petrolEngine = new EnumaretedEngine(EngineType.PETROL);
        petrolEngine.start();

        EnumaretedEngine electricEngine = new EnumaretedEngine(EngineType.ELECTRIC);
        electricEngine.start();
    }

    public void start() {
        switch (engineType) {
            case DIESEL:
                System.out.println("Starting diesel engine");
                break;
            case PETROL:
                System.out.println("Starting petrol engine");
                break;
            case ELECTRIC:
                System.out.println("Starting electric engine");
                break;
        }
    }

    public enum EngineType {
        DIESEL, PETROL, ELECTRIC
    }
}
