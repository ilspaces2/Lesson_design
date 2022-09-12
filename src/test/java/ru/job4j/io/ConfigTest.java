package ru.job4j.io;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class ConfigTest {

    @Test
    public void whenPairWithoutComment() {
        String path = "./data/pair_without_comment.properties";
        Config config = new Config(path);
        config.load();
        assertEquals(config.value("name"), "User");
    }

    @Test
    public void whenPairWithCommentAndEmptyString() {
        String path = "./data/pair_with_comment_and_empty_string.properties";
        Config config = new Config(path);
        config.load();
        assertEquals(config.value("name"), "User");
        assertEquals(config.value("password"), "12345");
    }

    @Test
    public void whenSeveralEqualsInString() {
        String path = "./data/pair_have_several_equals.properties";
        Config config = new Config(path);
        config.load();
        assertEquals(config.value("pass"), "password=1234");
    }

    @Test
    public void whenDoubleEquals() {
        String path = "./data/pair_double_equals.properties";
        Config config = new Config(path);
        config.load();
        assertEquals(config.value("key"), "=value");
    }

    @Test()
    public void whenWithoutEquals() {
        assertThrows(IllegalArgumentException.class, () -> {
            String path = "./data/pair_without_equals.properties";
            Config config = new Config(path);
            config.load();
        });
    }

    @Test()
    public void whenWithoutFirstValue() {
        assertThrows(IllegalArgumentException.class, () -> {
            String path = "./data/pair_without_first_value.properties";
            Config config = new Config(path);
            config.load();
        });
    }

    @Test()
    public void whenWithoutSecondValue() {
        assertThrows(IllegalArgumentException.class, () -> {
            String path = "./data/pair_without_second_value.properties";
            Config config = new Config(path);
            config.load();
        });
    }
}