package ru.job4j.io;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class ArgsNameTest {

    @Test
    public void whenGetFirst() {
        ArgsName jvm = ArgsName.of(new String[]{"-Xmx=512", "-encoding=UTF-8"});
        assertEquals(jvm.get("Xmx"), "512");
    }

    @Test
    public void whenGetFirstReorder() {
        ArgsName jvm = ArgsName.of(new String[]{"-encoding=UTF-8", "-Xmx=512"});
        assertEquals(jvm.get("Xmx"), "512");
    }

    @Test
    public void whenMultipleEqualsSymbol() {
        ArgsName jvm = ArgsName.of(new String[]{"-request=?msg=Exit="});
        assertEquals(jvm.get("request"), "?msg=Exit=");
    }

    @Test()
    public void whenGetNotExist() {
        assertThrows(IllegalArgumentException.class, () -> {
            ArgsName jvm = ArgsName.of(new String[]{});
            jvm.get("Xmx");
        });
    }

    @Test()
    public void whenWrongSomeArgument() {
        assertThrows(IllegalArgumentException.class, () -> {
            ArgsName jvm = ArgsName.of(new String[]{"-enconding=UTF-8", "-Xmx="});
        });
    }
}