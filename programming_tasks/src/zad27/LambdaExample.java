package zad27;

public interface LambdaExample {

    void invoke();

    static void main(String[] args) {
        LambdaExample example = () -> System.out.println("Lambda example");

        example.invoke();
    }
}
