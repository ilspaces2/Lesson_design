package ru.job4j.collection;

public class SimpleQueue<T> {
    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();
    private int countIn;

    public T poll() {
        int count = countIn - 1;
        while (countIn > 0) {
            out.push(in.pop());
            countIn--;
        }
        T element = out.pop();
        while (countIn < count) {
            in.push(out.pop());
            countIn++;
        }
        return element;
    }

    public void push(T value) {
        in.push(value);
        countIn++;
    }
}