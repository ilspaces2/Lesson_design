package ru.job4j.it;

import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class FlatMapTest {
    @Test
    public void whenDiffNext() {
        Iterator<Iterator<Integer>> data = List.of(
                List.of(1).iterator(),
                List.of(2, 3).iterator()
        ).iterator();
        FlatMap<Integer> flat = new FlatMap<>(data);
        assertEquals(flat.next(), 1);
        assertEquals(flat.next(), 2);
        assertEquals(flat.next(), 3);
    }

    @Test
    public void whenSeqNext() {
        Iterator<Iterator<Integer>> data = List.of(
                List.of(1, 2, 3).iterator()
        ).iterator();
        FlatMap<Integer> flat = new FlatMap<>(data);
        assertEquals(flat.next(), 1);
        assertEquals(flat.next(), 2);
        assertEquals(flat.next(), 3);
    }

    @Test
    public void whenMultiHasNext() {
        Iterator<Iterator<Integer>> data = List.of(
                List.of(1).iterator()
        ).iterator();
        FlatMap<Integer> flat = new FlatMap<>(data);
        assertTrue(flat.hasNext());
        assertTrue(flat.hasNext());
    }

    @Test
    public void whenHasNextFalse() {
        Iterator<Iterator<Integer>> data = List.of(
                List.of(1).iterator()
        ).iterator();
        FlatMap<Integer> flat = new FlatMap<>(data);
        assertEquals(flat.next(), 1);
        assertFalse(flat.hasNext());
    }

    @Test()
    public void whenEmpty() {
        assertThrows(NoSuchElementException.class, () -> {
            Iterator<Iterator<Object>> data = List.of(
                    List.of().iterator()
            ).iterator();
            FlatMap<Object> flat = new FlatMap<>(data);
            flat.next();
        });
    }

    @Test
    public void whenSeveralEmptyAndNotEmpty() {
        Iterator<Iterator<?>> it = List.of(
                List.of().iterator(),
                List.of().iterator(),
                List.of().iterator(),
                List.of(1).iterator()
        ).iterator();
        FlatMap flat = new FlatMap(it);
        assertTrue(flat.hasNext());
        assertEquals(flat.next(), 1);
    }

    @Test
    public void whenSeveralEmptyThenReturnFalse() {
        Iterator<Iterator<Object>> it = List.of(
                List.of().iterator(),
                List.of().iterator(),
                List.of().iterator(),
                List.of().iterator()
        ).iterator();
        FlatMap flat = new FlatMap(it);
        assertFalse(flat.hasNext());
    }
}