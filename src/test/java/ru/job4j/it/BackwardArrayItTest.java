package ru.job4j.it;

import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BackwardArrayItTest {

    @Test
    public void whenMultiCallhasNextThenTrue() {
        BackwardArrayIt it = new BackwardArrayIt(
                new int[]{1, 2, 3}
        );
        assertTrue(it.hasNext());
        assertTrue(it.hasNext());
    }

    @Test
    public void whenReadSequence() {
        BackwardArrayIt it = new BackwardArrayIt(
                new int[]{1, 2, 3}
        );
        assertEquals(it.next(), 3);
        assertEquals(it.next(), 2);
        assertEquals(it.next(), 1);
    }

    @Test()
    public void whenNextFromEmpty() {
        assertThrows(NoSuchElementException.class, () -> {
            BackwardArrayIt it = new BackwardArrayIt(
                    new int[]{}
            );
            it.next();
        });
    }
}