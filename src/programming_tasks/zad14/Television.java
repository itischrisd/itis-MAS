package programming_tasks.zad14;

public class Television extends Device {

    private final int screenSize;

    public Television(String name, String type, int screenSize) {
        super(name, type);
        this.screenSize = screenSize;
    }

    public int getScreenSize() {
        return screenSize;
    }

    @Override
    public String toString() {
        return "Television " + getName() + " is made by " + getProducer() + " and has " + getScreenSize() + " inches";
    }
}
