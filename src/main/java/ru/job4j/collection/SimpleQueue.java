package ru.job4j.collection;

public class SimpleQueue<T> {
    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();
    private int countIn;
    private int countOut;

    public T poll() {
        while (countIn > countOut) {
            out.push(in.pop());
            countOut++;
        }
        countIn = 0;
        countOut--;
        return out.pop();
    }

    public void push(T value) {
        in.push(value);
        countIn++;
    }
}