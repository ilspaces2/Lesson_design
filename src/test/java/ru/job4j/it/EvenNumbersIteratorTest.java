package ru.job4j.it;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class EvenNumbersIteratorTest {

    private Iterator<Integer> it;

    @BeforeEach
    public void setUp() {
        it = new EvenNumbersIterator(new int[]{1, 2, 3, 4, 5, 6, 7});
    }

    @Test()
    public void shouldReturnEvenNumbersSequentially() {
        assertThrows(NoSuchElementException.class, () -> {
            assertTrue(it.hasNext());
            assertEquals(it.next(), 2);
            assertTrue(it.hasNext());
            assertEquals(it.next(), 4);
            assertTrue(it.hasNext());
            assertEquals(it.next(), 6);
            assertFalse(it.hasNext());
            it.next();
        });
    }

    @Test
    public void sequentialHasNextInvocationDoesntAffectRetrievalOrder() {
        assertTrue(it.hasNext());
        assertTrue(it.hasNext());
        assertEquals(it.next(), 2);
        assertEquals(it.next(), 4);
        assertEquals(it.next(), 6);
    }

    @Test
    public void shouldReturnFalseIfNoAnyEvenNumbers() {
        it = new EvenNumbersIterator(new int[]{1});
        assertFalse(it.hasNext());
    }

    @Test
    public void allNumbersAreEven() {
        it = new EvenNumbersIterator(new int[]{2, 4, 6});
        assertTrue(it.hasNext());
        assertEquals(it.next(), 2);
        assertTrue(it.hasNext());
        assertEquals(it.next(), 4);
        assertTrue(it.hasNext());
        assertEquals(it.next(), 6);
        assertFalse(it.hasNext());
    }
}