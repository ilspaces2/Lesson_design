package ru.job4j.ood.kiss;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MaxMinTest {
    private List<User> list = List.of(
            new User(43, "Anna"),
            new User(1, "Victoria"),
            new User(45, "Ivan"),
            new User(5, "Boris"),
            new User(0, "Max")
    );

    @Test
    public void whenMaxIs45() {
        int expected = new MaxMin().max(list, User::compareTo).getId();
        assertEquals(expected, 45);
    }

    @Test
    public void whenMinIs0() {
        int expected = new MaxMin().min(list, User::compareTo).getId();
        assertEquals(expected, 0);
    }
}