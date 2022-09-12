package ru.job4j.map;

import java.util.ConcurrentModificationException;
import java.util.Iterator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;

public class SimpleMapTest {

    @Test
    public void whenAdd() {
        SimpleMap<String, String> map = new SimpleMap<>();
        assertTrue(map.put("Hello", "Java"));
    }

    @Test
    public void whenAddAndGet() {
        SimpleMap<String, String> map = new SimpleMap<>();
        map.put("Hello1", "Java1");
        map.put("Hello2", "Java2");
        map.put("Hello3", "Java3");
        map.put("Hello4", "Java4");
        assertEquals(map.get("Hello1"), "Java1");
        assertEquals(map.get("Hello2"), "Java2");
        assertEquals(map.get("Hello3"), "Java3");
        assertEquals(map.get("Hello4"), "Java4");
    }

    @Test
    public void whenAddAndRemove() {
        SimpleMap<String, String> map = new SimpleMap<>();
        map.put("Hello", "Java");
        assertTrue(map.remove("Hello"));
        assertNull(map.get("Hello"));
    }


    @Test
    public void whenIteratorHasNext() {
        SimpleMap<String, String> map = new SimpleMap<>();
        map.put("Hello", "1");
        Iterator<String> iterator = map.iterator();
        assertTrue(iterator.hasNext());
    }

    @Test
    public void whenIteratorDoesNotHasNext() {
        SimpleMap<String, String> map = new SimpleMap<>();
        Iterator<String> iterator = map.iterator();
        assertFalse(iterator.hasNext());
    }

    @Test
    public void whenIteratorNext() {
        SimpleMap<String, String> map = new SimpleMap<>();
        map.put("Hello", "1");
        Iterator<String> iterator = map.iterator();
        assertEquals(iterator.next(), "Hello");
    }

    @Test()
    public void whenIteratorThenModificationMap() {
        assertThrows(ConcurrentModificationException.class, () -> {
            SimpleMap<String, String> map = new SimpleMap<>();
            map.put("Hello", "1");
            Iterator<String> iterator = map.iterator();
            map.put("Hello1", "2");
            iterator.next();
        });
    }
}