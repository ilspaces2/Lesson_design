package ru.job4j.generics.store;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class RoleStoreTest {
    @Test
    public void whenAddAndFindThenRoleIsManager() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Manager"));
        Role result = store.findById("1");
        assertEquals(result.getRole(), "Manager");
    }

    @Test
    public void whenAddAndFindThenRoleIsNull() {
        RoleStore store = new RoleStore();
        store.add(new Role("12", "Manager"));
        Role result = store.findById("10");
        assertNull(result);
    }

    @Test
    public void whenAddDuplicateAndFindRoleIsManager() {
        RoleStore store = new RoleStore();
        store.add(new Role("2", "Manager"));
        store.add(new Role("2", "Director"));
        Role result = store.findById("2");
        assertEquals(result.getRole(), "Manager");
    }

    @Test
    public void whenReplaceThenRoleIsDirector() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Manager"));
        store.replace("1", new Role("1", "Director"));
        Role result = store.findById("1");
        assertEquals(result.getRole(), "Director");

    }

    @Test
    public void whenNoReplaceRoleThenNoChangeRole() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Manager"));
        store.replace("10", new Role("10", "Director"));
        Role result = store.findById("1");
        assertEquals(result.getRole(), "Manager");
    }

    @Test
    public void whenDeleteRoleThenRoleIsNull() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Manager"));
        store.delete("1");
        Role result = store.findById("1");
        assertNull(result);
    }

    @Test
    public void whenNoDeleteRoleThenRoleIsDirector() {
        RoleStore store = new RoleStore();
        store.add(new Role("2", "Director"));
        store.delete("10");
        Role result = store.findById("2");
        assertEquals(result.getRole(), "Director");
    }
}