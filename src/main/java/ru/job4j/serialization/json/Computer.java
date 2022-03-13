package ru.job4j.serialization.json;

import java.util.Arrays;

public class Computer {
    private final String name;
    private final int price;
    private final boolean available;
    private final String[] equipment;
    private final User user;

    public Computer(String name, int price, boolean available, String[] equipment, User user) {
        this.name = name;
        this.price = price;
        this.available = available;
        this.equipment = equipment;
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public boolean isAvailable() {
        return available;
    }

    public String[] getEquipment() {
        return equipment;
    }

    public User getUser() {
        return user;
    }

    @Override
    public String toString() {
        return "Computer{"
                + "name='" + name + '\''
                + ", price=" + price
                + ", available=" + available
                + ", equipment=" + Arrays.toString(equipment)
                + ", user=" + user
                + '}';
    }
}
