package ru.job4j.collection;

import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class ForwardLinkedTest {

    @Test()
    public void whenDeleteFirst() {
        assertThrows(NoSuchElementException.class, () -> {
            ForwardLinked<Integer> linked = new ForwardLinked<>();
            linked.add(1);
            linked.deleteFirst();
            linked.iterator().next();
        });
    }

    @Test()
    public void whenDeleteEmptyLinked() {
        assertThrows(NoSuchElementException.class, () -> {
            ForwardLinked<Integer> linked = new ForwardLinked<>();
            linked.deleteFirst();
        });
    }

    @Test
    public void whenMultiDelete() {
        ForwardLinked<Integer> linked = new ForwardLinked<>();
        linked.add(1);
        linked.add(2);
        assertEquals(linked.deleteFirst(), 1);
        Iterator<Integer> it = linked.iterator();
        assertEquals(it.next(), 2);
    }

    @Test
    public void whenAddAndAddFirst() {
        ForwardLinked<Integer> linked = new ForwardLinked<>();
        linked.add(1);
        linked.add(2);
        linked.addFirst(3);
        Iterator<Integer> it = linked.iterator();
        assertEquals(it.next(), 3);
        assertEquals(it.next(), 1);
        assertEquals(it.next(), 2);
    }

    @Test
    public void whenAddThenIter() {
        ForwardLinked<Integer> linked = new ForwardLinked<>();
        linked.add(1);
        linked.add(2);
        Iterator<Integer> it = linked.iterator();
        assertEquals(it.next(), 1);
        assertEquals(it.next(), 2);
    }

    @Test
    public void whenAddAndRevertThenIter() {
        ForwardLinked<Integer> linked = new ForwardLinked<>();
        linked.add(1);
        linked.add(2);
        linked.add(3);
        linked.add(4);
        linked.add(5);
        linked.revert();
        Iterator<Integer> it = linked.iterator();
        assertEquals(it.next(), 5);
        assertEquals(it.next(), 4);
        assertEquals(it.next(), 3);
        assertEquals(it.next(), 2);
        assertEquals(it.next(), 1);
    }

    @Test
    public void whenSize0ThenReturnFalse() {
        ForwardLinked<Integer> emptyList = new ForwardLinked<>();
        assertFalse(emptyList.revert());
    }

    @Test
    public void whenSize1ThenReturnFalse() {
        ForwardLinked<Integer> singleList = new ForwardLinked<>();
        singleList.add(1);
        assertFalse(singleList.revert());
    }
}