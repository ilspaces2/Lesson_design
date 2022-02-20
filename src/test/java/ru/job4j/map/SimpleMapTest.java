package ru.job4j.map;

import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.*;

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
        assertThat(map.get("Hello1"), is("Java1"));
        assertThat(map.get("Hello2"), is("Java2"));
        assertThat(map.get("Hello3"), is("Java3"));
        assertThat(map.get("Hello4"), is("Java4"));
    }

    @Test
    public void whenMapContainKey() {
        SimpleMap<String, String> map = new SimpleMap<>();
        map.put("1", "11");
        map.put("1", "22");
        assertThat(map.get("1"), is("22"));
    }


    @Test
    public void whenAddAndRemove() {
        SimpleMap<String, String> map = new SimpleMap<>();
        map.put("Hello", "Java");
        assertTrue(map.remove("Hello"));
        assertThat(map.get("Hello"), is(nullValue()));
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
        assertThat(iterator.next(), is("Hello"));
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenIteratorThenModificationMap() {
        SimpleMap<String, String> map = new SimpleMap<>();
        map.put("Hello", "1");
        Iterator<String> iterator = map.iterator();
        map.put("Hello1", "2");
        iterator.next();
    }
}