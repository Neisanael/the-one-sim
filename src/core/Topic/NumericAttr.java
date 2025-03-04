package core.Topic;

public class NumericAttr<T extends Number> {
    private final T value1;
    private final T value2;

    public NumericAttr(T value1) {
        this.value1 = value1;
        this.value2 = null;
    }

    public NumericAttr(T value1, T value2) {
        this.value1 = value1;
        this.value2 = value2;
    }

    public T getValue1() {
        return value1;
    }

    public T getValue2() {
        return value2;
    }

    public boolean hasTwoValues() {
        return value2 != null;
    }

    @Override
    public String toString() {
        if (hasTwoValues()) {
            return "NumericAttr{value1=" + value1 + ", value2=" + value2 + "}";
        } else {
            return "NumericAttr{value1=" + value1 + "}";
        }
    }
}