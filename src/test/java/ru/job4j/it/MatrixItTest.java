package ru.job4j.it;

import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MatrixItTest {
    @Test
    public void when4El() {
        int[][] in = {
                {1}
        };
        MatrixIt it = new MatrixIt(in);
        assertEquals(it.next(), 1);
    }

    @Test
    public void whenFirstEmptyThenNext() {
        int[][] in = {
                {}, {1}
        };
        MatrixIt it = new MatrixIt(in);
        assertEquals(it.next(), 1);
    }

    @Test
    public void whenFirstEmptyThenHashNext() {
        int[][] in = {
                {}, {1}
        };
        MatrixIt it = new MatrixIt(in);
        assertTrue(it.hasNext());
    }

    @Test
    public void whenRowHasDiffSize() {
        int[][] in = {
                {1}, {2, 3}
        };
        MatrixIt it = new MatrixIt(in);
        assertEquals(it.next(), 1);
        assertEquals(it.next(), 2);
        assertEquals(it.next(), 3);
    }

    @Test
    public void whenFewEmpty() {
        int[][] in = {
                {1}, {}, {}, {}, {2}
        };
        MatrixIt it = new MatrixIt(in);
        assertEquals(it.next(), 1);
        assertEquals(it.next(), 2);
    }

    @Test
    public void whenEmpty() {
        int[][] in = {
                {}
        };
        MatrixIt it = new MatrixIt(in);
        assertFalse(it.hasNext());
    }

    @Test()
    public void whenEmptyThenNext() {
        assertThrows(NoSuchElementException.class, () -> {
            int[][] in = {
                    {}
            };
            MatrixIt it = new MatrixIt(in);
            it.next();
        });
    }

    @Test
    public void whenMultiHashNext() {
        int[][] in = {
                {}, {1}
        };
        MatrixIt it = new MatrixIt(in);
        assertTrue(it.hasNext());
        assertTrue(it.hasNext());
    }

    @Test
    public void whenNoElements() {
        int[][] in = {{}, {}, {}};
        MatrixIt it = new MatrixIt(in);
        assertFalse(it.hasNext());
    }
}