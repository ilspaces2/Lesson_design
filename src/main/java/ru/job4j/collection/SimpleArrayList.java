package ru.job4j.collection;

import java.util.*;

public class SimpleArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;

    private T[] container;

    private int size;

    private int modCount;

    public SimpleArrayList(int capacity) {
        this.container = (T[]) new Object[capacity];
    }

    @Override
    public void add(T value) {
        if (size == container.length) {
            grow();
        }
        modCount++;
        container[size++] = value;
    }

    private void grow() {
        if (container.length == 0) {
            container = (T[]) new Object[DEFAULT_CAPACITY];
        } else {
            container = Arrays.copyOf(container, container.length * 2);
        }
    }

    @Override
    public T set(int index, T newValue) {
        T oldValue = get(index);
        container[index] = newValue;
        return oldValue;
    }

    @Override
    public T remove(int index) {
        T oldValue = get(index);
        modCount++;
        if (index == size - 1) {
            container[index] = null;
        } else {
            System.arraycopy(container, index + 1,
                    container, index, container.length - 1 - index);
        }
        size--;
        return oldValue;
    }

    @Override
    public T get(int index) {
        return container[Objects.checkIndex(index, size)];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private final int expectedModCount = modCount;
            private int index;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return index < size;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return container[index++];
            }
        };
    }
}
