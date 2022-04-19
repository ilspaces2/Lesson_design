package ru.job4j.ood.kiss;

import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

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
        assertThat(45, is(expected));
    }

    @Test
    public void whenMinIs0() {
        int expected = new MaxMin().min(list, User::compareTo).getId();
        assertThat(0, is(expected));
    }
}