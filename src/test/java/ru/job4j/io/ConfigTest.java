package ru.job4j.io;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ConfigTest {

    @Test
    public void whenPairWithoutComment() {
        String path = "./data/pair_without_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name"),
                is("User"));
    }

    @Test
    public void whenPairWithCommentAndEmptyString() {
        String path = "./data/pair_with_comment_and_empty_string.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name"),
                is("User"));
        assertThat(config.value("password"),
                is("12345"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenSeveralEqualsInString() {
        String path = "./data/pair_have_several_equals.properties";
        Config config = new Config(path);
        config.load();
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenDoubleEquals() {
        String path = "./data/pair_double_equals.properties";
        Config config = new Config(path);
        config.load();
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenWithoutEquals() {
        String path = "./data/pair_without_equals.properties";
        Config config = new Config(path);
        config.load();
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenWithoutFirstValue() {
        String path = "./data/pair_without_first_value.properties";
        Config config = new Config(path);
        config.load();
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenWithoutSecondValue() {
        String path = "./data/pair_without_second_value.properties";
        Config config = new Config(path);
        config.load();
    }
}