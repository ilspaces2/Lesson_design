package ru.job4j.iterator;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ListUtilsTest {

    @Test
    public void whenAddBefore() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addBefore(input, 1, 2);
        assertEquals(input, Arrays.asList(1, 2, 3));
    }

    @Test
    public void whenAddAfter() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addAfter(input, 0, 2);
        assertEquals(input, Arrays.asList(1, 2, 3));
    }

    @Test()
    public void whenAddBeforeWithInvalidIndex() {
        assertThrows(IndexOutOfBoundsException.class, () -> {
            List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
            ListUtils.addBefore(input, 3, 2);
        });
    }

    @Test
    public void whenAddAfterLast() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2));
        ListUtils.addAfter(input, 2, 3);
        assertEquals(input, Arrays.asList(0, 1, 2, 3));
    }

    @Test
    public void whenRemoveIf() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 5, 2, 6, 2));
        ListUtils.removeIf(input, el -> el < 4);
        assertEquals(input, Arrays.asList(5, 6));

    }

    @Test
    public void whenReplaceIf() {
        List<Integer> input = new ArrayList<>(Arrays.asList(5, 10, 6, 2, 10));
        ListUtils.replaceIf(input, el -> el == 10, 5);
        assertEquals(input, Arrays.asList(5, 5, 6, 2, 5));
    }

    @Test
    public void whenRemoveAll() {
        List<Integer> input = new ArrayList<>(Arrays.asList(5, 10, 2, 5, 7, 6, 2, 9));
        List<Integer> elements = new ArrayList<>(Arrays.asList(5, 10, 6));
        ListUtils.removeAll(input, elements);
        assertEquals(input, Arrays.asList(2, 7, 2, 9));
    }
}