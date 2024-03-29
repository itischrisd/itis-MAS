package programming_tasks.zad14;

public class MobilePhone extends Device {

    private final String operatingSystem;

    public MobilePhone(String name, String type, String operatingSystem) {
        super(name, type);
        this.operatingSystem = operatingSystem;
    }

    public String getOperatingSystem() {
        return operatingSystem;
    }

    @Override
    public String toString() {
        return "MobilePhone " + getName() + " is made by " + getProducer() + " and has " + getOperatingSystem() + " OS";
    }
}
