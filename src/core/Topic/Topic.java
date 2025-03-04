package core.Topic;

public class Topic<T> {
    private T name;

    public Topic(T name) {
        this.name = name;
    }

    public T getName() {
        return name;
    }

    public void setName(T name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Topic{name='" + name + "}";
    }
}
