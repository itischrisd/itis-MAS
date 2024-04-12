package MPO1_exercise;

public enum Rating {
    AL("AL"),
    _7("7"),
    _12("12"),
    _15("15"),
    _18("18"),
    _21("21");

    private final String age;

    Rating(String age) {
        this.age = age;
    }

    public String getAge() {
        return this.age;
    }
}