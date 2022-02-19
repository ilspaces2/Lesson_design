package ru.job4j.set;

import ru.job4j.collection.SimpleArrayList;

import java.util.Iterator;
import java.util.Objects;

public class SimpleSet<T> implements Set<T> {

    private SimpleArrayList<T> set = new SimpleArrayList<>(16);

    @Override
    public boolean add(T value) {
        boolean rzl = !contains(value);
        if (rzl) {
            set.add(value);
        }
        return rzl;
    }

    @Override
    public boolean contains(T value) {
        boolean rzl = false;
        for (T el : set) {
            if (Objects.equals(el, value)) {
                rzl = true;
                break;
            }
        }
        return rzl;
    }

    @Override
    public Iterator<T> iterator() {
        return set.iterator();
    }
}