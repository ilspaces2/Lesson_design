package ru.job4j.collection.list;

import org.junit.jupiter.api.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class SimpleLinkedListTest {

    @Test
    public void whenAddAndGet() {
        List<Integer> list = new SimpleLinkedList<>();
        list.add(1);
        list.add(2);
        assertEquals(list.get(0), 1);
        assertEquals(list.get(1), 2);
    }

    @Test()
    public void whenGetFromOutOfBoundThenExceptionThrown() {
        assertThrows(IndexOutOfBoundsException.class, () -> {
            List<Integer> list = new SimpleLinkedList<>();
            list.add(1);
            list.add(2);
            list.get(2);
        });
    }

    @Test
    public void whenAddIterHasNextTrue() {
        List<Integer> list = new SimpleLinkedList<>();
        list.add(1);
        Iterator<Integer> it = list.iterator();
        assertTrue(it.hasNext());
    }

    @Test
    public void whenAddIterNextOne() {
        List<Integer> list = new SimpleLinkedList<>();
        list.add(1);
        Iterator<Integer> it = list.iterator();
        assertEquals(it.next(), 1);
    }

    @Test
    public void whenEmptyIterHasNextFalse() {
        List<Integer> list = new SimpleLinkedList<>();
        Iterator<Integer> it = list.iterator();
        assertFalse(it.hasNext());
    }

    @Test
    public void whenAddIterMultiHasNextTrue() {
        List<Integer> list = new SimpleLinkedList<>();
        list.add(1);
        Iterator<Integer> it = list.iterator();
        assertTrue(it.hasNext());
        assertTrue(it.hasNext());
    }

    @Test
    public void whenAddIterNextOneNextTwo() {
        List<Integer> list = new SimpleLinkedList<>();
        list.add(1);
        list.add(2);
        Iterator<Integer> it = list.iterator();
        assertEquals(it.next(), 1);
        assertEquals(it.next(), 2);
    }

    @Test
    public void whenGetIteratorTwiceThenEveryFromBegin() {
        List<Integer> list = new SimpleLinkedList<>();
        list.add(1);
        list.add(2);
        Iterator<Integer> first = list.iterator();
        assertTrue(first.hasNext());
        assertEquals(first.next(), 1);
        assertTrue(first.hasNext());
        assertEquals(first.next(), 2);
        assertFalse(first.hasNext());
        Iterator<Integer> second = list.iterator();
        assertTrue(second.hasNext());
        assertEquals(second.next(), 1);
        assertTrue(second.hasNext());
        assertEquals(second.next(), 2);
        assertFalse(second.hasNext());
    }

    @Test()
    public void whenGetIterAddNextThenMustBeException() {
        assertThrows(ConcurrentModificationException.class, () -> {
            List<Integer> list = new SimpleLinkedList<>();
            Iterator<Integer> it = list.iterator();
            list.add(1);
            it.next();
        });
    }

    @Test()
    public void whenListEmptyGetIterNextThenMustBeException() {
        assertThrows(NoSuchElementException.class, () -> {
            List<Integer> list = new SimpleLinkedList<>();
            Iterator<Integer> it = list.iterator();
            it.next();
        });
    }
}