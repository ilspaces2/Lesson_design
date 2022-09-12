package ru.job4j.question;

import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AnalizeTest {

    @Test
    public void whenNotChanged() {
        User u1 = new User(1, "A");
        User u2 = new User(2, "B");
        User u3 = new User(3, "C");
        Set<User> previous = Set.of(u1, u2, u3);
        Set<User> current = Set.of(u1, u2, u3);
        assertEquals(
                Analize.diff(previous, current),
                new Info(0, 0, 0)
        );
    }

    @Test
    public void whenOneChanged() {
        User u1 = new User(1, "A");
        User u2 = new User(2, "B");
        User u3 = new User(3, "C");
        Set<User> previous = Set.of(u1, u2, u3);
        Set<User> current = Set.of(u1, new User(2, "BB"), u3);
        assertEquals(
                Analize.diff(previous, current),
                new Info(0, 1, 0)
        );
    }

    @Test
    public void whenOneDeleted() {
        User u1 = new User(1, "A");
        User u2 = new User(2, "B");
        User u3 = new User(3, "C");
        Set<User> previous = Set.of(u1, u2, u3);
        Set<User> current = Set.of(u1, u3);
        assertEquals(
                Analize.diff(previous, current),
                new Info(0, 0, 1)
        );
    }

    @Test
    public void whenOneAdded() {
        User u1 = new User(1, "A");
        User u2 = new User(2, "B");
        User u3 = new User(3, "C");
        Set<User> previous = Set.of(u1, u2, u3);
        Set<User> current = Set.of(u1, u2, u3, new User(4, "D"));
        assertEquals(
                Analize.diff(previous, current),
                new Info(1, 0, 0)
        );
    }

    @Test
    public void whenAllChanged() {
        User u1 = new User(1, "A");
        User u2 = new User(2, "B");
        User u3 = new User(3, "C");
        Set<User> previous = Set.of(u1, u2, u3);
        Set<User> current = Set.of(new User(1, "AA"), u2, new User(4, "D"));
        assertEquals(
                Analize.diff(previous, current),
                new Info(1, 1, 1)
        );
    }

    @Test
    public void when2Deleted2Added() {
        User u1 = new User(1, "A");
        User u2 = new User(2, "B");
        User u3 = new User(3, "C");
        User u4 = new User(4, "B4");
        User u5 = new User(5, "C5");
        Set<User> previous = Set.of(u1, u2, u3);
        Set<User> current = Set.of(u3, u4, u5);
        assertEquals(Analize.diff(previous, current),
                new Info(2, 0, 2)
        );
    }
}