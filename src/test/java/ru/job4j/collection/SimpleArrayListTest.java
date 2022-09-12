package ru.job4j.collection;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class SimpleArrayListTest {

    List<Integer> list;

    @BeforeEach
    public void initData() {
        list = new SimpleArrayList<>(3);
        list.add(1);
        list.add(2);
        list.add(3);
    }

    @Test
    public void whenAddThenSizeIncrease() {
        assertEquals(3, list.size());
    }

    @Test
    public void whenAddAndGetByCorrectIndex() {
        assertEquals(Integer.valueOf(1), list.get(0));
    }

    @Test()
    public void whenAddAndGetByIncorrectIndexThenGetException() {
        assertThrows(IndexOutOfBoundsException.class, () -> {
            list.get(5);
        });
    }

    @Test
    public void whenRemoveThenGetValueAndSizeDecrease() {
        assertEquals(3, list.size());
        assertEquals(Integer.valueOf(2), list.remove(1));
        assertEquals(2, list.size());
    }

    @Test()
    public void whenRemoveByIncorrectIndexThenGetException() {
        assertThrows(IndexOutOfBoundsException.class, () -> {
            list.remove(5);
        });
    }

    @Test
    public void whenRemoveThenMustNotBeEmpty() {
        list.remove(1);
        assertEquals(Integer.valueOf(1), list.get(0));
        assertEquals(Integer.valueOf(3), list.get(1));
    }

    @Test
    public void whenAddNullThenMustBeSameBehavior() {
        list = new SimpleArrayList<>(3);
        list.add(null);
        list.add(null);
        assertEquals(2, list.size());
        assertNull(list.get(0));
        assertNull(list.get(1));
    }

    @Test
    public void whenSetThenGetOldValueAndSizeNotChanged() {
        assertEquals(Integer.valueOf(2), list.set(1, 22));
        assertEquals(3, list.size());
    }

    @Test()
    public void whenSetByIncorrectIndexThenGetException() {
        assertThrows(IndexOutOfBoundsException.class, () -> {
            list.set(5, 22);
        });
    }

    @Test
    public void whenGetIteratorFromEmptyListThenHasNextReturnFalse() {
        list = new SimpleArrayList<>(5);
        assertFalse(list.iterator().hasNext());
    }

    @Test()
    public void whenGetIteratorFromEmptyListThenNextThrowException() {
        assertThrows(NoSuchElementException.class, () -> {
            list = new SimpleArrayList<>(5);
            list.iterator().next();
        });

    }

    @Test
    public void whenGetIteratorTwiceThenStartAlwaysFromBeginning() {
        assertEquals(Integer.valueOf(1), list.iterator().next());
        assertEquals(Integer.valueOf(1), list.iterator().next());
    }

    @Test
    public void whenCheckIterator() {
        Iterator<Integer> iterator = list.iterator();
        assertTrue(iterator.hasNext());
        assertEquals(Integer.valueOf(1), iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals(Integer.valueOf(2), iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals(Integer.valueOf(3), iterator.next());
        assertFalse(iterator.hasNext());
    }

    @Test
    public void whenNoPlaceThenMustIncreaseCapacity() {
        IntStream.range(3, 10).forEach(v -> list.add(v));
    }

    @Test()
    public void whenAddAfterGetIteratorThenMustBeException() {
        assertThrows(ConcurrentModificationException.class, () -> {
            Iterator<Integer> iterator = list.iterator();
            list.add(4);
            iterator.next();
        });

    }

    @Test()
    public void whenRemoveAfterGetIteratorThenMustBeException() {
        assertThrows(ConcurrentModificationException.class, () -> {
            Iterator<Integer> iterator = list.iterator();
            list.add(0);
            iterator.next();
        });
    }
}