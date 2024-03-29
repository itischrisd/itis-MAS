package programming_tasks.zad26;

public interface Example {

    static void main(String[] args) {
        Example example = new Example() {
            @Override
            public void exampleMethod() {
                System.out.println("Example method");
            }

            @Override
            public void anotherExampleMethod() {
                System.out.println("Another example method");
            }
        };
        example.exampleMethod();
        example.anotherExampleMethod();
    }

    void exampleMethod();

    void anotherExampleMethod();
}
