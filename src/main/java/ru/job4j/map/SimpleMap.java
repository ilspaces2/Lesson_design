package ru.job4j.map;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleMap<K, V> implements Map<K, V> {

    private static final float LOAD_FACTOR = 0.75f;

    private int capacity = 8;

    private int count = 0;

    private int modCount = 0;

    private MapEntry<K, V>[] table = new MapEntry[capacity];

    @Override
    public boolean put(K key, V value) {
        boolean rzl = false;
        expand();
        int index = indexFor(hash(key.hashCode()));
        if (table[index] == null) {
            table[index] = new MapEntry<>(key, value);
            count++;
            modCount++;
            rzl = true;
        } else {
            table[index].value = value;
            modCount++;
            rzl = true;
        }
        return rzl;
    }

    private int hash(int hashCode) {
        return hashCode ^ hashCode >>> 16;
    }

    private int indexFor(int hash) {
        return hash & (capacity - 1);
    }

    private void expand() {
        if (count >= capacity * LOAD_FACTOR) {
            capacity *= 2;
            MapEntry<K, V>[] newTable = new MapEntry[capacity];
            for (MapEntry<K, V> keys : table) {
                if (keys != null) {
                    newTable[indexFor(hash(keys.hashCode()))] = keys;
                }
            }
            table = newTable;
        }
    }

    @Override
    public V get(K key) {
        V value = null;
        int index = indexFor(hash(key.hashCode()));
        if (table[index] != null) {
            value = table[index].value;
        }
        return value;
    }

    @Override
    public boolean remove(K key) {
        boolean rzl = false;
        int index = indexFor(hash(key.hashCode()));
        if (table[index] != null) {
            table[index] = null;
            count--;
            modCount++;
            rzl = true;
        }
        return rzl;
    }

    @Override
    public Iterator<K> iterator() {
        return new Iterator<K>() {
            private final int expectedModCount = modCount;
            private int index;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return index < capacity && count > 0;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                MapEntry<K, V> item = table[index++];
                return item != null ? item.key : next();
            }
        };
    }

    private static class MapEntry<K, V> {

        K key;
        V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }

    }
}