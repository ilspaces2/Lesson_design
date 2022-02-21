package ru.job4j.map;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

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
        int index = indexFor(hash(key));
        if (table[index] == null) {
            table[index] = new MapEntry<>(key, value);
            count++;
            modCount++;
            rzl = true;
        }
        return rzl;
    }

    private int hash(Object key) {
        return key == null ? 0 : key.hashCode() ^ key.hashCode() >>> 16;
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
                    newTable[indexFor(hash(keys.key))] = keys;
                }
            }
            table = newTable;
        }
    }

    @Override
    public V get(K key) {
        int index = indexFor(hash(key));
        return compareKeys(index, key) ? table[index].value : null;
    }

    @Override
    public boolean remove(K key) {
        int index = indexFor(hash(key));
        boolean rzl = compareKeys(index, key);
        if (rzl) {
            table[index] = null;
            count--;
            modCount++;
        }
        return rzl;
    }

    private boolean compareKeys(int index, K key) {
        boolean rzl = false;
        MapEntry<K, V> currentKey = table[index];
        if (currentKey != null && key != null && currentKey.key.hashCode() == key.hashCode()
                && Objects.equals(currentKey.key, key)) {
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